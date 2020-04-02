package com.censusanalyser;

import com.google.gson.Gson;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;

public class StateCensusAnalyserTest {
    StateCensusAnalyser statecensusAnalyser = new StateCensusAnalyser( );
   public static  final String INDIA_STATE_CENSUS_CSV_FILE_PATH = "D:\\Generics\\src\\main\\resources\\StateCode.csv";
    public static  final String INDIA_STATE_CODE_CSV_FILE_PATH = "D:\\Generics\\src\\main\\resources\\StateCensusDataPojo.csv";
    StateCensusAnalyser censusAnalyser = new StateCensusAnalyser();

        @Test
        public void givenStateCensusAnalyserFile_WhenTrue_NumberOfRecordShouldMatch() throws CSVBuilderException {
            int count = censusAnalyser.loadCensusCSVData(INDIA_STATE_CENSUS_CSV_FILE_PATH);
            Assert.assertEquals(29, count);
        }

        @Test
        public void givenStateCensusAnalyserFile_WhenImproperFileName_ReturnsException() {
            try {
                censusAnalyser.loadCensusCSVData(INDIA_STATE_CENSUS_CSV_FILE_PATH);
            } catch (CSVBuilderException e) {
                Assert.assertEquals(CSVBuilderException.ExceptionType.CENSUS_FILE_PROBLEM, e.type);
            }
        }

        @Test
        public void givenStateCensusAnalyserFile_WhenIncorrectType_ReturnCustomException() {
            try {
                File fileExtension = new File(INDIA_STATE_CENSUS_CSV_FILE_PATH);
                censusAnalyser.getFileExtension(fileExtension);
            } catch (StateCensusAnalyserException e) {
                Assert.assertEquals(StateCensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM, e.type);
            }
        }

        @Test
        public void givenStateCensusAnalyserFile_WhenIncorrectDelimiter_ReturnCustomException() {
            try {
                File delimiterCheck = new File(INDIA_STATE_CENSUS_CSV_FILE_PATH);
                censusAnalyser.checkDelimiter(delimiterCheck);
            } catch (StateCensusAnalyserException e) {
                Assert.assertEquals(StateCensusAnalyserException.ExceptionType.UNABLE_TO_PARSE, e.type);
            } catch (CSVBuilderException e) {
                e.printStackTrace();
            }
        }

        @Test
        public void givenStateCensusAnalyserFile_WhenIncorrectHeader_ReturnCustomException() {
            try {
                censusAnalyser.loadCensusCSVData(INDIA_STATE_CENSUS_CSV_FILE_PATH);
            } catch (CSVBuilderException e) {
                Assert.assertEquals(e.getMessage(), "Number of data fields does not match number of headers.");
            }
        }

        @Test
        public void givenStateCodeCsvFile_WhenTrue_NumberOfRecordShouldMatch() throws CSVBuilderException {
            int count = censusAnalyser.loadStateCodeData(INDIA_STATE_CODE_CSV_FILE_PATH);
            Assert.assertEquals(37, count);
        }

        @Test
        public void givenStateCodeCsvFile_WhenImproperFileName_ReturnsException() {
            try {
                censusAnalyser.loadCensusCSVData(INDIA_STATE_CODE_CSV_FILE_PATH);
            } catch (CSVBuilderException e) {
                Assert.assertEquals(CSVBuilderException.ExceptionType.UNABLE_TO_PARSE, e.type);
            }
        }

        @Test
        public void givenStateCodeCsvFile_WhenIncorrectType_ReturnCustomException() {
            try {
                File fileExtension = new File(INDIA_STATE_CODE_CSV_FILE_PATH);
                censusAnalyser.getFileExtension(fileExtension);
            } catch (StateCensusAnalyserException e) {
                Assert.assertEquals(StateCensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM, e.type);
            }
        }

        @Test
        public void givenStateCodeCsvFile_WhenIncorrectDelimiter_ReturnCustomException() {
            try {
                File delimiterCheck = new File(INDIA_STATE_CODE_CSV_FILE_PATH);
                censusAnalyser.checkDelimiter(delimiterCheck);
            } catch (StateCensusAnalyserException e) {
                Assert.assertEquals(StateCensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM, e.type);
            } catch (CSVBuilderException e) {
                e.printStackTrace();
            }
        }

        @Test
        public void givenStateCodeCsvFile_WhenIncorrectHeader_ReturnCustomException() {
            try {
                censusAnalyser.loadCensusCSVData(INDIA_STATE_CODE_CSV_FILE_PATH);
            } catch (CSVBuilderException e) {
                Assert.assertEquals("Number of data fields does not match number of headers.", e.getMessage());
            }
        }

        @Test
        public void givenIndianCensusData_WhenSortedOnState_ShouldReturnSortedList() {
            try {
                censusAnalyser.loadCensusCSVData(INDIA_STATE_CENSUS_CSV_FILE_PATH);
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
                censusAnalyser.loadCensusCSVData(INDIA_STATE_CENSUS_CSV_FILE_PATH);
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
                censusAnalyser.loadCensusCSVData(INDIA_STATE_CENSUS_CSV_FILE_PATH);
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
                censusAnalyser.loadStateCodeData(INDIA_STATE_CODE_CSV_FILE_PATH);
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
                censusAnalyser.loadStateCodeData(INDIA_STATE_CODE_CSV_FILE_PATH);
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
                censusAnalyser.loadStateCodeData(INDIA_STATE_CODE_CSV_FILE_PATH);
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
        public void givenIndianStateCencusData_WhenSortedOnPopulation_ShouldReturnSortedList() {
            try {
                censusAnalyser.loadCensusCSVData(INDIA_STATE_CENSUS_CSV_FILE_PATH);
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
        public void givenIndianStateCencusData_WhenSortedOnPopulation_ShouldReturnSortedList_2() {
            try {
                censusAnalyser.loadCensusCSVData(INDIA_STATE_CENSUS_CSV_FILE_PATH);
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
            censusAnalyser.loadCensusCSVData(INDIA_STATE_CENSUS_CSV_FILE_PATH);
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
            censusAnalyser.loadCensusCSVData(INDIA_STATE_CENSUS_CSV_FILE_PATH);
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
}

