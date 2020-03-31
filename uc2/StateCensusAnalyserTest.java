package com.censusanalyserudl;

import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

public class StateCensusAnalyserTest {

    public static final String STATECODES_CSVFILE = "D:\\Generics\\src\\main\\resources\\StateCode.csv";
    public static final String STATECENSUSDATA_CSVFILE = "D:\\Generics\\src\\main\\resources\\StateCensus.csv";
    public static final String WRONG_FILE = "\\useless.txt";

    @Test
    public void GivenTheStateCodesCsvFile_IfHasCorrectNumberOfRecords_ShouldReturnTrue() throws IOException {
        try {
            int count = StateCensusAnalyser.openCsvBuilder(STATECODES_CSVFILE, StateCensus.class);
            System.out.println(count);
            Assert.assertEquals(37, count);
        } catch (CensusAnalyserException e) {
            e.printStackTrace( );
        }
    }

    @Test
    public void GivenTheStateCensusCsvFile_If_DoesntExist_ShouldThrowCensusAnalyserException() throws IOException {
        try {
            int count = StateCensusAnalyser.openCsvBuilder(WRONG_FILE, StateCensus.class);
        } catch (CensusAnalyserException e) {
            e.printStackTrace( );
            Assert.assertEquals(CensusAnalyserException.CensusExceptionType.NO_SUCH_FILE, e.type);
        }
    }

    @Test
    public void GivenTheStateCensusCodesCsvFile_WhenCorrect_ButFileExtensionIncorrect_ShouldThrowCensusAnalyserException() throws IOException {
        try {
            int count = StateCensusAnalyser.openCsvBuilder(STATECODES_CSVFILE, StateCensus.class);
        } catch (CensusAnalyserException e) {
            e.printStackTrace( );
            Assert.assertEquals(CensusAnalyserException.CensusExceptionType.INCORRECT_DATA_ISSUE, e.type);
        }
    }

    @Test
    public void GivenTheStateCensusCodeCSVFile_WhenCorrect_ButDelimiterIncorrect_ReturnsCensusAnalyserException() throws IOException {
        try {
            int count = StateCensusAnalyser.openCsvBuilder(STATECENSUSDATA_CSVFILE, StateCensus.class);
        } catch (CensusAnalyserException e) {
            e.printStackTrace( );
            Assert.assertEquals(CensusAnalyserException.CensusExceptionType.INCORRECT_DATA_ISSUE, e.type);

        }
    }

    @Test
    public void whenCorrectCensusCSVFile_ButHeaderIncorrect_ShouldReturnFalse() throws IOException {
        try {
            int count = StateCensusAnalyser.openCsvBuilder(STATECENSUSDATA_CSVFILE, StateCensus.class);
        } catch (CensusAnalyserException e) {
            e.printStackTrace( );
            Assert.assertEquals(CensusAnalyserException.CensusExceptionType.INCORRECT_DATA_ISSUE, e.type);
        }
    }

}