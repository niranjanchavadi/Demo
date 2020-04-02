package com.censusanalyser;


import com.google.gson.Gson;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.regex.Pattern;

public class StateCensusAnalyser {
    int count = 0;
    HashMap<String, CensusDAO> censusDAOMap = new HashMap<String, CensusDAO>();
    private Set set;

    //READING AND PRINTING DATA FROM STATE CENSUS CSV FILE
    public int loadCensusCSVData(String getPath) throws CSVBuilderException {
        try (Reader reader = Files.newBufferedReader(Paths.get(getPath))
        ) {
            ICSVBuilder csvBuilder = CSVBuilderFactory.createCSVBuilder();
            Iterator<CSVStateCensus> csvFileIterator = csvBuilder.getCSVFileIterator(reader, CSVStateCensus.class);
            while (csvFileIterator.hasNext()) {
                CensusDAO indianCensusDAO = new CensusDAO(csvFileIterator.next());
                this.censusDAOMap.put(indianCensusDAO.state, indianCensusDAO);
            }
            return this.censusDAOMap.size();
        } catch (IOException e) {
            throw new CSVBuilderException(e.getMessage(),
                    CSVBuilderException.ExceptionType.CENSUS_FILE_PROBLEM);
        } catch (RuntimeException e) {
            throw new CSVBuilderException("UNABLE_TO_PARSE",
                    CSVBuilderException.ExceptionType.UNABLE_TO_PARSE);
        }
    }

    //READING AND PRINTING DATA FROM STATE CODE CSV FILE
    public int loadStateCodeData(String getPath) throws CSVBuilderException {
        try (Reader reader = Files.newBufferedReader(Paths.get(getPath))
        ) {
            ICSVBuilder csvBuilder = CSVBuilderFactory.createCSVBuilder();
            Iterator<CSVStates> csvFileIterator = csvBuilder.getCSVFileIterator(reader, CSVStates.class);
            while (csvFileIterator.hasNext()) {
                CensusDAO indianCensusDAO = new CensusDAO(csvFileIterator.next());
                this.censusDAOMap.put(indianCensusDAO.state, indianCensusDAO);
            }
            return this.censusDAOMap.size();
        } catch (IOException e) {
            throw new CSVBuilderException(e.getMessage(),
                    CSVBuilderException.ExceptionType.UNABLE_TO_PARSE);
        } catch (RuntimeException e) {
            throw new CSVBuilderException("UNABLE_TO_PARSE",
                    CSVBuilderException.ExceptionType.CENSUS_FILE_PROBLEM);
        }

    }

    //METHOD TO CHECK FILE EXTENSION
    public void getFileExtension(File file) throws StateCensusAnalyserException {
        boolean result = false;
        String fileName = file.getName();
        if (fileName.lastIndexOf(".") != -1 && fileName.lastIndexOf(".") != 0) {
            result = fileName.substring(fileName.lastIndexOf(".") + 1).equals("csv");
        }
        if (!result)
            throw new StateCensusAnalyserException("Wrong file type",
                    StateCensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM);
    }

    //METHOD TO CHECK DELIMITER IN CSV FILE
    public void checkDelimiter(File file) throws StateCensusAnalyserException, CSVBuilderException {
        Pattern pattern = Pattern.compile("^[\\w ]*,[\\w ]*,[\\w ]*,[\\w ]*");
        BufferedReader br = null;
        boolean delimiterResult = true;
        try {
            br = new BufferedReader(new FileReader(file));
            String line;
            while ((line = br.readLine()) != null) {
                delimiterResult = pattern.matcher(line).matches();
                if (!delimiterResult) {
                    throw new StateCensusAnalyserException("Invalid Delimiter found",
                            StateCensusAnalyserException.ExceptionType.UNABLE_TO_PARSE);
                }
            }
        } catch (FileNotFoundException e) {
            throw new CSVBuilderException(e.getMessage(), CSVBuilderException.ExceptionType.UNABLE_TO_PARSE);
        } catch (IOException e) {
            throw new CSVBuilderException(e.getMessage(), CSVBuilderException.ExceptionType.CENSUS_FILE_PROBLEM);
        }
    }

    //METHOD TO SORT STATE NAME
    public String getStateWiseSortedCensusData() throws StateCensusAnalyserException {
        if (censusDAOMap == null || censusDAOMap.size() == 0)
            throw new StateCensusAnalyserException("No Census Data", StateCensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM);
        Comparator<Map.Entry<String, CensusDAO>> stateCodeComparator = Comparator.comparing(census -> census.getValue().state);
        LinkedHashMap<String, CensusDAO> sortedByValue = this.sort(stateCodeComparator);
        Collection<CensusDAO> list1 = sortedByValue.values();
        String sortedStateCensusJson = new Gson().toJson(list1);
        return sortedStateCensusJson;
    }

    //METHOD TO SORT STATE CODE
    public String getStateWiseSortedStateCode() throws StateCensusAnalyserException {
        if (censusDAOMap == null || censusDAOMap.size() == 0)
            throw new StateCensusAnalyserException("No State Data", StateCensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM);
        Comparator<Map.Entry<String, CensusDAO>> stateCodeComparator = Comparator.comparing(census -> census.getValue().state);
        LinkedHashMap<String, CensusDAO> sortedByValue = this.sort(stateCodeComparator);
        Collection<CensusDAO> list2 = sortedByValue.values();
        String sortedStateCensusJson = new Gson().toJson(list2);
        return sortedStateCensusJson;
    }

    //METHOD TO SORT THE LIST IN ALPHABETICAL ORDER
    private <E extends CensusDAO> LinkedHashMap<String, CensusDAO> sort(Comparator censusComparator) {
        Set<Map.Entry<String, CensusDAO>> entries = censusDAOMap.entrySet();
        List<Map.Entry<String, CensusDAO>> listOfEntries = new ArrayList<Map.Entry<String, CensusDAO>>(entries);
        Collections.sort(listOfEntries, censusComparator);
        LinkedHashMap<String, CensusDAO> sortedByValue = new LinkedHashMap<String, CensusDAO>(listOfEntries.size());

        // copying entries from List to Map
        for (Map.Entry<String, CensusDAO> entry : listOfEntries) {
            sortedByValue.put(entry.getKey(), entry.getValue());
        }
        return sortedByValue;
    }
}