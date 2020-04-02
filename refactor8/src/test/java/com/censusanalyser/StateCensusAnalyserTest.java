package com.censusanalyser;

import com.google.gson.Gson;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;

public class StateCensusAnalyserTest {
    String INDIA_STATE_CENSUS_CSV_FILE_PATH = "\"D:\\Generics\\src\\main\\resources\\StateCode.csv";
    String INDIA_STATE_CODE_CSV_FILE_PATH = "D:\\Generics\\src\\main\\resources\\StateCensusDataPojo.csv";
    StateCensusAnalyser statecensusAnalyser = new StateCensusAnalyser( );



    @Test
    public void givenStateCensusAnalyserFile_WhenIncorrectType_ReturnCustomException() {
        INDIA_STATE_CENSUS_CSV_FILE_PATH = "./src/test/resources/EstateCensusData.pdf";
        try {
            File fileExtension = new File(INDIA_STATE_CENSUS_CSV_FILE_PATH);
            statecensusAnalyser.getFileExtension(fileExtension);
        } catch (StateCensusAnalyserException e) {
            Assert.assertEquals(StateCensusAnalyserException.ExceptionType.UNABLE_TO_PARSE, e.type);
        }
    }

    @Test
    public void givenStateCensusAnalyserFile_WhenIncorrectDelimiter_ReturnCustomException() {
        try {
            INDIA_STATE_CENSUS_CSV_FILE_PATH = "./src/test/resources/StateCensusDataCopy.csv";
            File delimiterCheck = new File(INDIA_STATE_CENSUS_CSV_FILE_PATH);
            statecensusAnalyser.checkDelimiter(delimiterCheck);
        } catch (StateCensusAnalyserException e) {
            Assert.assertEquals(StateCensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM, e.type);
        } catch (CSVBuilderException e) {
            e.printStackTrace( );
        }
    }



    @Test
    public void givenStateCodeCsvFile_WhenTrue_NumberOfRecordShouldMatch() throws CSVBuilderException {
        int count = statecensusAnalyser.loadStateCodeData(INDIA_STATE_CODE_CSV_FILE_PATH);
        Assert.assertEquals(37, count);
    }



    @Test
    public void givenStateCodeCsvFile_WhenIncorrectType_ReturnCustomException() {
        INDIA_STATE_CODE_CSV_FILE_PATH = "./src/test/resources/StateCode.pdf";
        try {
            File fileExtension = new File(INDIA_STATE_CODE_CSV_FILE_PATH);
            statecensusAnalyser.getFileExtension(fileExtension);
        } catch (StateCensusAnalyserException e) {
            Assert.assertEquals(StateCensusAnalyserException.ExceptionType.UNABLE_TO_PARSE, e.type);
        }
    }

    @Test
    public void givenStateCodeCsvFile_WhenIncorrectDelimiter_ReturnCustomException() {
        try {
            INDIA_STATE_CODE_CSV_FILE_PATH = "./src/test/resources/StateCodeDelimiterCopy.csv";
            File delimiterCheck = new File(INDIA_STATE_CODE_CSV_FILE_PATH);
            statecensusAnalyser.checkDelimiter(delimiterCheck);
        } catch (StateCensusAnalyserException e) {
            Assert.assertEquals(StateCensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM, e.type);
        } catch (CSVBuilderException e) {
            e.printStackTrace( );
        }
    }






    @Test
    public void givenIndianStateCodeData_WhenSortedOnState_ShouldReturnSortedList() {
        try {
            statecensusAnalyser.loadStateCodeData(INDIA_STATE_CODE_CSV_FILE_PATH);
            String sortedCensusData = statecensusAnalyser.getStateWiseSortedStateCode( );
            CensusDAO[] stateCensuses = new Gson( ).fromJson(sortedCensusData, CensusDAO[].class);
            Assert.assertEquals("AD", stateCensuses[0].stateCode);
        } catch (CSVBuilderException e) {
            e.printStackTrace( );
        } catch (StateCensusAnalyserException e) {
            e.printStackTrace( );
        }
    }

    @Test
    public void givenIndianStateCodeData_WhenSortedOnState_ShouldReturnSortedList_2() {
        try {
            statecensusAnalyser.loadStateCodeData(INDIA_STATE_CODE_CSV_FILE_PATH);
            String sortedCensusData = statecensusAnalyser.getStateWiseSortedStateCode( );
            CensusDAO[] stateCensuses = new Gson( ).fromJson(sortedCensusData, CensusDAO[].class);
            Assert.assertEquals("WB", stateCensuses[36].stateCode);
        } catch (CSVBuilderException e) {
            e.printStackTrace( );
        } catch (StateCensusAnalyserException e) {
            e.printStackTrace( );
        }
    }

    @Test
    public void givenIndianStateCodeData_WhenNotSortedOnState_ShouldNotReturnSortedList() {
        try {
            statecensusAnalyser.loadStateCodeData(INDIA_STATE_CODE_CSV_FILE_PATH);
            String sortedCensusData = statecensusAnalyser.getStateWiseSortedStateCode( );
            CensusDAO[] stateCensuses = new Gson( ).fromJson(sortedCensusData, CensusDAO[].class);
            Assert.assertNotEquals("GJ", stateCensuses[36].stateCode);
        } catch (CSVBuilderException e) {
            e.printStackTrace( );
        } catch (StateCensusAnalyserException e) {
            e.printStackTrace( );
        }
    }
}



