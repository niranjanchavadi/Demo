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


    public String getStateWiseSortedCensusData(String csvFilePath) throws StateCensusAnalyserException{
        try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath));){
            ICSVBuilder csvBuilder = CSVBuilderFactory.createCSVBuilder();
            List censusCSVIList = csvBuilder.getCSVFileList(reader,CSVStateCensus.class);
            Comparator<CSVStateCensus> censusComparator = Comparator.comparing(cenus -> cenus.state);
            this.sort(censusCSVIList,censusComparator);
            String sortedStateCensusJson = new Gson().toJson(censusCSVIList);
            return sortedStateCensusJson;
        }catch (IOException | CSVBuilderException e) {
            throw new StateCensusAnalyserException(e.getMessage(), StateCensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM);
        }

    }

    private void sort(List<CSVStateCensus> censusCSVIList, Comparator<CSVStateCensus> censusComparator) {
        for (int i=0;i<censusCSVIList.size();i++ ) {
            for (int j=0;j<censusCSVIList.size()-i-1;j++) {
                CSVStateCensus census1 = (CSVStateCensus) censusCSVIList.get(j);
                CSVStateCensus census2 = (CSVStateCensus) censusCSVIList.get(j);
                if (censusComparator.compare(census1,census2)>0){
                     censusCSVIList.set(j,census2);
                     censusCSVIList.set(j+1,census1);
                }
            }

        }
    }
}
