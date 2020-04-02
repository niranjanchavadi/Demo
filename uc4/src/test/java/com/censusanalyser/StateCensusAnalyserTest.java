package com.censusanalyser;

import com.google.gson.Gson;
import org.junit.Assert;
import org.junit.Test;

public class StateCensusAnalyserTest {
    final String INDIA_STATE_CENSUS_CSV_FILE_PATH = "./src/test/resources/IndiaStateCensusData.csv";
    final String INDIA_STATE_CODE_CSV_FILE_PATH = "./src/test/resources/StateCodeData.csv";
    final String WRONG_CSV_FILE_PATH = "./src/main/resources/StateCodeData.csv";
    final String WRONG_CSV_FILE_TYPE = "./src/test/resources/StateCodeData.pdf";
    final String CSV_FILE_WITH_WRONG_DELIMITER = "./src/test/resources/WStateCodeData.csv";
    final String CSV_FILE_WITH_WRONG_HEADER = "./src/test/resources/WStateCodeData.csv";

    StateCensusAnalyser stateCensusAnalyser = new StateCensusAnalyser();
    @Test
    public void givenIndiaStateCensusCSVFileReturnsCorrectRecords() {
        try {
            int numOfRecords = stateCensusAnalyser.loadIndiaCensusData(INDIA_STATE_CENSUS_CSV_FILE_PATH);
            Assert.assertEquals(29, numOfRecords);
        }catch (StateCensusAnalyserException e){
            e.getStackTrace();
        }
    }
    @Test
    public void givenIndiaCensusCSVFileReturnsCorrectRecords() {
        try {
            int numOfRecords = stateCensusAnalyser.loadIndiaStateCodeData(INDIA_STATE_CODE_CSV_FILE_PATH);
            Assert.assertEquals(37, numOfRecords);
        }catch (StateCensusAnalyserException e){
            e.getStackTrace();
        }
    }
    @Test
    public void givenIndiaCensusCSVWithWrongFilePathShouldThrowException() {
        try {
            stateCensusAnalyser.loadIndiaStateCodeData(WRONG_CSV_FILE_PATH);
        }catch (StateCensusAnalyserException e){
            Assert.assertEquals(StateCensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM, e.type);
        }
    }
    @Test
    public void givenIndiaCensusCSVWithWrongFileTypeShouldThrowException() {
        try {
            stateCensusAnalyser.loadIndiaStateCodeData(WRONG_CSV_FILE_TYPE);
        }catch (StateCensusAnalyserException e){
            Assert.assertEquals(StateCensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM, e.type);
        }
    }
    @Test
    public void givenIndiaCensusCSVWithCorrectFileButWrongDelimiterShouldThrowException() {
        try {
            stateCensusAnalyser.loadIndiaStateCodeData(CSV_FILE_WITH_WRONG_DELIMITER);
        }catch (StateCensusAnalyserException e){
            Assert.assertEquals(StateCensusAnalyserException.ExceptionType.UNABLE_TO_PARSE, e.type);
        }
    }
    @Test
    public void givenIndiaCensusCSVWithCorrectFileButWrongHeaderShouldThrowException() {
        try {
            stateCensusAnalyser.loadIndiaStateCodeData(CSV_FILE_WITH_WRONG_HEADER);
        }catch (StateCensusAnalyserException e){
            Assert.assertEquals(StateCensusAnalyserException.ExceptionType.UNABLE_TO_PARSE, e.type);
        }
    }
    @Test
    public void givenIndianCensusData_WhenSortedOnState_ShouldReturnSortedList() {
        try {
            stateCensusAnalyser.loadIndiaCensusData(INDIA_STATE_CENSUS_CSV_FILE_PATH);
            String sortedCensusData = stateCensusAnalyser.getStateWiseSortedCensusData(INDIA_STATE_CENSUS_CSV_FILE_PATH);
            CSVStateCensus[] censusCSV = new Gson().fromJson(sortedCensusData, CSVStateCensus[].class);
            Assert.assertEquals("Andhra Pradesh", censusCSV[0].state);
        } catch (StateCensusAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenIndianCensusData_WhenSortedOnState_ShouldReturnSortedList_2() {
        try {
            stateCensusAnalyser.loadIndiaCensusData(INDIA_STATE_CENSUS_CSV_FILE_PATH);
            String sortedCensusData = stateCensusAnalyser.getStateWiseSortedCensusData(INDIA_STATE_CENSUS_CSV_FILE_PATH);
            CSVStateCensus[] censusCSV = new Gson().fromJson(sortedCensusData, CSVStateCensus[].class);
            Assert.assertEquals("Assam", censusCSV[2].state);
        } catch (StateCensusAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenIndianCensusData_WhenSortedImproperlyOnState_ShouldNotReturnSortedList() {
        try {
            stateCensusAnalyser.loadIndiaCensusData(INDIA_STATE_CENSUS_CSV_FILE_PATH);
            String sortedCensusData = stateCensusAnalyser.getStateWiseSortedCensusData(INDIA_STATE_CENSUS_CSV_FILE_PATH);
            CSVStateCensus[] censusCSV = new Gson().fromJson(sortedCensusData, CSVStateCensus[].class);
            Assert.assertNotEquals("Uttar Pradesh", censusCSV[29].state);
        } catch (StateCensusAnalyserException e) {
            e.printStackTrace();
        }
    }
}



