package com.censusanalyser;

import com.google.gson.Gson;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;

import static com.censusanalyser.StateCensusAnalyser.COUNTRY.INDIA;
import static com.censusanalyser.StateCensusAnalyser.COUNTRY.US;

public class StateCensusAnalyserTest {

    String INDIA_STATE_CENSUS_CSV_FILE_PATH = "./src/test/resources/StateCensusData.csv";
    String INDIA_STATE_CODE_CSV_FILE_PATH = "./src/test/resources/StateCode.csv";
    String US_CENSUS_Csv_DATA = "./src/test/resources/USCensusData.csv";
    StateCensusAnalyser censusAnalyser = new StateCensusAnalyser();

    @Test
    public void givenStateCensusAnalyserFile_WhenTrue_NumberOfRecordShouldMatch() throws CSVBuilderException {
        int count = censusAnalyser.loadCensusData(INDIA, INDIA_STATE_CENSUS_CSV_FILE_PATH);
        Assert.assertEquals(29, count);
    }

    @Test
    public void givenStateCensusAnalyserFile_WhenImproperFileName_ReturnsException() {
        try {
            INDIA_STATE_CENSUS_CSV_FILE_PATH = "./src/test/resources/EstateCensusData.csv";
            censusAnalyser.loadCensusData(INDIA, INDIA_STATE_CENSUS_CSV_FILE_PATH);
        } catch (CSVBuilderException e) {
            Assert.assertEquals(CSVBuilderException.ExceptionType.INPUT_OUTPUT_OPERATION_FAILED, e.type);
        }
    }

    @Test
    public void givenStateCensusAnalyserFile_WhenIncorrectType_ReturnCustomException() {
        INDIA_STATE_CENSUS_CSV_FILE_PATH = "./src/test/resources/EstateCensusData.pdf";
        try {
            File fileExtension = new File(INDIA_STATE_CENSUS_CSV_FILE_PATH);
            censusAnalyser.getFileExtension(fileExtension);
        } catch (StateCensusAnalyserException e) {
            Assert.assertEquals(StateCensusAnalyserException.ExceptionType.WRONG_FILE_TYPE, e.type);
        }
    }

    @Test
    public void givenStateCensusAnalyserFile_WhenIncorrectDelimiter_ReturnCustomException() {
        try {
            INDIA_STATE_CENSUS_CSV_FILE_PATH = "./src/test/resources/StateCensusDataCopy.csv";
            File delimiterCheck = new File(INDIA_STATE_CENSUS_CSV_FILE_PATH);
            censusAnalyser.checkDelimiter(delimiterCheck);
        } catch (StateCensusAnalyserException e) {
            Assert.assertEquals(StateCensusAnalyserException.ExceptionType.INVALID_DELIMITER, e.type);
        } catch (CSVBuilderException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenStateCensusAnalyserFile_WhenIncorrectHeader_ReturnCustomException() {
        try {
            INDIA_STATE_CENSUS_CSV_FILE_PATH = "./src/test/resources/StateCensusDataHeaderCopy.csv";
            censusAnalyser.loadCensusData(INDIA, INDIA_STATE_CENSUS_CSV_FILE_PATH);
        } catch (CSVBuilderException e) {
            Assert.assertEquals(e.getMessage(), "Number of data fields does not match number of headers.");
        }
    }

    @Test
    public void givenStateCodeCsvFile_WhenTrue_NumberOfRecordShouldMatch() throws CSVBuilderException {
        int count = censusAnalyser.loadCensusData(INDIA, INDIA_STATE_CENSUS_CSV_FILE_PATH, INDIA_STATE_CODE_CSV_FILE_PATH);
        Assert.assertEquals(37, count);
    }

    @Test
    public void givenStateCodeCsvFile_WhenImproperFileName_ReturnsException() {
        try {
            INDIA_STATE_CODE_CSV_FILE_PATH = "./src/test/resources/EstateCode.csv";
            censusAnalyser.loadCensusData(INDIA, INDIA_STATE_CENSUS_CSV_FILE_PATH, INDIA_STATE_CODE_CSV_FILE_PATH);
        } catch (CSVBuilderException e) {
            Assert.assertEquals(CSVBuilderException.ExceptionType.INPUT_OUTPUT_OPERATION_FAILED, e.type);
        }
    }

    @Test
    public void givenStateCodeCsvFile_WhenIncorrectType_ReturnCustomException() {
        INDIA_STATE_CODE_CSV_FILE_PATH = "./src/test/resources/StateCode.pdf";
        try {
            File fileExtension = new File(INDIA_STATE_CODE_CSV_FILE_PATH);
            censusAnalyser.getFileExtension(fileExtension);
        } catch (StateCensusAnalyserException e) {
            Assert.assertEquals(StateCensusAnalyserException.ExceptionType.WRONG_FILE_TYPE, e.type);
        }
    }

    @Test
    public void givenStateCodeCsvFile_WhenIncorrectDelimiter_ReturnCustomException() {
        try {
            INDIA_STATE_CODE_CSV_FILE_PATH = "./src/test/resources/StateCodeDelimiterCopy.csv";
            File delimiterCheck = new File(INDIA_STATE_CODE_CSV_FILE_PATH);
            censusAnalyser.checkDelimiter(delimiterCheck);
        } catch (StateCensusAnalyserException e) {
            Assert.assertEquals(StateCensusAnalyserException.ExceptionType.INVALID_DELIMITER, e.type);
        } catch (CSVBuilderException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenStateCodeCsvFile_WhenIncorrectHeader_ReturnCustomException() {
        try {

            censusAnalyser.loadCensusData(INDIA, INDIA_STATE_CODE_CSV_FILE_PATH);
        } catch (CSVBuilderException e) {
            Assert.assertEquals("Number of data fields does not match number of headers.", e.getMessage());
        }
    }

    @Test
    public void givenIndianCensusData_WhenSortedOnState_ShouldReturnSortedList() {
        try {
            censusAnalyser.loadCensusData(INDIA, INDIA_STATE_CENSUS_CSV_FILE_PATH);
            String sortedCensusData = censusAnalyser.getStateWiseSortedCensusData();
            CensusDAO[] censusCSV = new Gson().fromJson(sortedCensusData, CensusDAO[].class);
            Assert.assertEquals("Andhra Pradesh", censusCSV[0].state);
        } catch (CSVBuilderException e) {
            e.printStackTrace();
        } catch (StateCensusAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenIndianCensusData_WhenSortedOnState_ShouldReturnSortedList_2() {
        try {
            censusAnalyser.loadCensusData(INDIA, INDIA_STATE_CENSUS_CSV_FILE_PATH);
            String sortedCensusData = censusAnalyser.getStateWiseSortedCensusData();
            CensusDAO[] censusCSV = new Gson().fromJson(sortedCensusData, CensusDAO[].class);
            Assert.assertEquals("Assam", censusCSV[2].state);
        } catch (CSVBuilderException e) {
            e.printStackTrace();
        } catch (StateCensusAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenIndianCensusData_WhenSortedImproperlyOnState_ShouldNotReturnSortedList() {
        try {
            censusAnalyser.loadCensusData(INDIA, INDIA_STATE_CENSUS_CSV_FILE_PATH);
            String sortedCensusData = censusAnalyser.getStateWiseSortedCensusData();
            CensusDAO[] censusCSV = new Gson().fromJson(sortedCensusData, CensusDAO[].class);
            Assert.assertNotEquals("Gujarat", censusCSV[0].state);
        } catch (CSVBuilderException e) {
            e.printStackTrace();
        } catch (StateCensusAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenIndianStateCodeData_WhenSortedOnState_ShouldReturnSortedList() {
        try {
            censusAnalyser.loadCensusData(INDIA, INDIA_STATE_CENSUS_CSV_FILE_PATH, INDIA_STATE_CODE_CSV_FILE_PATH);
            String sortedCensusData = censusAnalyser.getStateWiseSortedStateCode();
            CensusDAO[] stateCensuses = new Gson().fromJson(sortedCensusData, CensusDAO[].class);
            Assert.assertEquals("AD", stateCensuses[0].stateCode);
        } catch (CSVBuilderException e) {
            e.printStackTrace();
        } catch (StateCensusAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenIndianStateCodeData_WhenSortedOnState_ShouldReturnSortedList_2() {
        try {
            censusAnalyser.loadCensusData(INDIA, INDIA_STATE_CENSUS_CSV_FILE_PATH, INDIA_STATE_CODE_CSV_FILE_PATH);
            String sortedCensusData = censusAnalyser.getStateWiseSortedStateCode();
            CensusDAO[] stateCensuses = new Gson().fromJson(sortedCensusData, CensusDAO[].class);
            Assert.assertEquals("WB", stateCensuses[36].stateCode);
        } catch (CSVBuilderException e) {
            e.printStackTrace();
        } catch (StateCensusAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenIndianStateCodeData_WhenNotSortedOnState_ShouldNotReturnSortedList() {
        try {
            censusAnalyser.loadCensusData(INDIA, INDIA_STATE_CENSUS_CSV_FILE_PATH, INDIA_STATE_CODE_CSV_FILE_PATH);
            String sortedCensusData = censusAnalyser.getStateWiseSortedStateCode();
            CensusDAO[] stateCensuses = new Gson().fromJson(sortedCensusData, CensusDAO[].class);
            Assert.assertNotEquals("GJ", stateCensuses[36].stateCode);
        } catch (CSVBuilderException e) {
            e.printStackTrace();
        } catch (StateCensusAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenIndianStateCensusData_WhenSortedOnPopulation_ShouldReturnSortedList() {
        try {
            censusAnalyser.loadCensusData(INDIA, INDIA_STATE_CENSUS_CSV_FILE_PATH);
            String sortedCensusData = censusAnalyser.getStateWiseSortedSPopulation();
            CensusDAO[] stateCensuses = new Gson().fromJson(sortedCensusData, CensusDAO[].class);
            Assert.assertEquals("Uttar Pradesh", stateCensuses[0].state);
        } catch (CSVBuilderException e) {
            e.printStackTrace();
        } catch (StateCensusAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenIndianStateCensusData_WhenSortedOnPopulation_ShouldReturnSortedList_2() {
        try {
            censusAnalyser.loadCensusData(INDIA, INDIA_STATE_CENSUS_CSV_FILE_PATH);
            String sortedCensusData = censusAnalyser.getStateWiseSortedSPopulation();
            CensusDAO[] stateCensuses = new Gson().fromJson(sortedCensusData, CensusDAO[].class);
            Assert.assertEquals("Sikkim", stateCensuses[28].state);
        } catch (CSVBuilderException e) {
            e.printStackTrace();
        } catch (StateCensusAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenIndianStateCensusData_WhenReturnsNull_ShouldThrowException() {
        try {
            String sortedCensusData = censusAnalyser.getStateWiseSortedSPopulation();
            CensusDAO[] stateCensuses = new Gson().fromJson(sortedCensusData, CensusDAO[].class);
        } catch (StateCensusAnalyserException e) {
            Assert.assertEquals("No Population State Data", e.getMessage());
        }
    }

    @Test
    public void givenIndianStateCensusData_WhenSortedOnDensityPerSquareKM_ShouldReturnSortedList() {
        try {
            censusAnalyser.loadCensusData(INDIA, INDIA_STATE_CENSUS_CSV_FILE_PATH);
            String sortedCensusData = censusAnalyser.getStateWiseSortedSPopulationDensity();
            CensusDAO[] stateCensuses = new Gson().fromJson(sortedCensusData, CensusDAO[].class);
            Assert.assertEquals("Bihar", stateCensuses[0].state);
        } catch (CSVBuilderException e) {
            e.printStackTrace();
        } catch (StateCensusAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenIndianStateCensusData_WhenSortedOnDensityPerSquareKM_ShouldReturnSortedList_2() {
        try {
            censusAnalyser.loadCensusData(INDIA, INDIA_STATE_CENSUS_CSV_FILE_PATH);
            String sortedCensusData = censusAnalyser.getStateWiseSortedSPopulationDensity();
            CensusDAO[] stateCensuses = new Gson().fromJson(sortedCensusData, CensusDAO[].class);
            Assert.assertEquals("Arunachal Pradesh", stateCensuses[28].state);
        } catch (CSVBuilderException e) {
            e.printStackTrace();
        } catch (StateCensusAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenIndianStateCensusData_WhenSortedOnDensityPerSquareKM_ShouldThrowException() {
        try {
            String sortedCensusData = censusAnalyser.getStateWiseSortedSPopulationDensity();
            CensusDAO[] stateCensuses = new Gson().fromJson(sortedCensusData, CensusDAO[].class);
        } catch (StateCensusAnalyserException e) {
            Assert.assertEquals("No DensityPerSquareKM State Data", e.getMessage());
        }
    }

    @Test
    public void givenIndianStateCensusData_WhenSortedStateAreaList_ShouldReturnSortedList() {
        try {
            censusAnalyser.loadCensusData(INDIA, INDIA_STATE_CENSUS_CSV_FILE_PATH);
            String sortedCensusData = censusAnalyser.getStateWiseSortedStateArea();
            CensusDAO[] stateCensuses = new Gson().fromJson(sortedCensusData, CensusDAO[].class);
            Assert.assertEquals("Rajasthan", stateCensuses[0].state);
        } catch (CSVBuilderException e) {
            e.printStackTrace();
        } catch (StateCensusAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenIndianStateCensusData_WhenSortedStateAreaList_ShouldReturnSortedList_2() {
        try {
            censusAnalyser.loadCensusData(INDIA, INDIA_STATE_CENSUS_CSV_FILE_PATH);
            String sortedCensusData = censusAnalyser.getStateWiseSortedStateArea();
            CensusDAO[] stateCensuses = new Gson().fromJson(sortedCensusData, CensusDAO[].class);
            Assert.assertEquals("Arunachal Pradesh", stateCensuses[28].state);
        } catch (CSVBuilderException e) {
            e.printStackTrace();
        } catch (StateCensusAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenIndianStateCensusData_WhenSortedStateAreaList_ShouldThrowException() {
        try {
            String sortedCensusData = censusAnalyser.getStateWiseSortedStateArea();
            CensusDAO[] stateCensuses = new Gson().fromJson(sortedCensusData, CensusDAO[].class);
        } catch (StateCensusAnalyserException e) {
            Assert.assertEquals("No Area of State Data", e.getMessage());
        }
    }

    @Test
    public void givenUsCensusData_WhenLoadedCorrect_ShouldReturnExactCountOfList() {
        try{
            int count = censusAnalyser.loadCensusData(US, US_CENSUS_Csv_DATA);
            Assert.assertEquals(51,count);
        } catch (CSVBuilderException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenUsCensusData_WhenLoadedIncorrect_ShouldReturnWrongCountOfList() {
        try{
            int count = censusAnalyser.loadCensusData(US, US_CENSUS_Csv_DATA);
            Assert.assertNotEquals(52,count);
        } catch (CSVBuilderException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenUsCensusData_WhenLoadedIncorrect_ShouldReturnWrongCountOfList_2() {
        try{
            int count = censusAnalyser.loadCensusData(US, US_CENSUS_Csv_DATA);
            Assert.assertNotEquals(50,count);
        } catch (CSVBuilderException e) {
            e.printStackTrace();
        }
    }
}


