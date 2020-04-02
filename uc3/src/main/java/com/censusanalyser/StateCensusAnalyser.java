package com.censusanalyser;

import com.google.gson.Gson;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.stream.StreamSupport;

public class StateCensusAnalyser {
    List<CSVStateCensus> censusCSVIList;

    public int loadIndiaCensusData(String csvFilePath) throws StateCensusAnalyserException {
        try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath));){
            ICSVBuilder csvBuilder = CSVBuilderFactory.createCSVBuilder();
            List censusCSVIList = csvBuilder.getCSVFileList(reader,CSVStateCensus.class);
            return censusCSVIList.size();
        }catch (IOException | CSVBuilderException e){
            throw new StateCensusAnalyserException(e.getMessage(),StateCensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM);
        }
    }
    public int loadIndiaStateCodeData(String csvFilePath) throws StateCensusAnalyserException {
        try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath));){
            ICSVBuilder csvBuilder = CSVBuilderFactory.createCSVBuilder();
            List stateCSVList = csvBuilder.getCSVFileList(reader,CSVStates.class);
            return ((List) stateCSVList).size();
        }catch (IOException | CSVBuilderException e) {
            throw new StateCensusAnalyserException(e.getMessage(), StateCensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM);
        }
    }
    private <E> int getCount(Iterator<E> iterator){
        Iterable<E> csvIterable = () -> iterator;
        int numOfEntries = (int) StreamSupport.stream(csvIterable.spliterator(), false).count();
        return numOfEntries;
    }


    public String getStateWiseSortedCensusData() throws StateCensusAnalyserException{
        if (censusCSVIList == null || censusCSVIList.size() == 0)
            throw new StateCensusAnalyserException("No Census Data", StateCensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM);
        Comparator<CSVStateCensus> censusCSVComparator = Comparator.comparing(census -> census.state);
        this.sort(censusCSVComparator);
        String sortedStateCensusJson = new Gson().toJson(censusCSVIList);
        return sortedStateCensusJson;
    }

    private void sort(Comparator<CSVStateCensus> censusComparator) {
        for (int iterate = 0; iterate < censusCSVIList.size() - 1; iterate++) {
            for (int Inneriterate = 0; Inneriterate < censusCSVIList.size() - iterate - 1; Inneriterate++) {
                CSVStateCensus census1 = censusCSVIList.get(Inneriterate);
                CSVStateCensus census2 =censusCSVIList.get(Inneriterate + 1);
                if (censusComparator.compare(census1, census2) > 0) {
                    censusCSVIList.set(Inneriterate, census2);
                    censusCSVIList.set(Inneriterate + 1, census1);
                }
            }

        }
    }
}
