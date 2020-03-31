package com.censusanalyserudl;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.Iterator;

public class StateCensusAnalyser {



    public StateCensusAnalyser() {
    }

    public static <T> int openCsvBuilder(String csvFilePath, Object myClass) throws CensusAnalyserException {
        int counter = 0;
        try {
            Iterator<Object> myIterator = getIterator(csvFilePath, myClass);
            while (myIterator.hasNext( )) {
                counter++;
                Object myObj = myIterator.next( );

            }
        } catch (CensusAnalyserException e) {
            throw e;
        } catch (RuntimeException e) {
            throw new CensusAnalyserException(CensusAnalyserException.CensusExceptionType.DELIMITER_ISSUE,
                    "DELIMITER_ISSUE " + (counter + 1));
        }
        return counter;
    }

    public static Iterator<Object> getIterator(String csvFilePath, Object myClass) throws CensusAnalyserException {
        Reader reader = null;
        CsvToBean<Object> csvToBean;
        try {
            reader = Files.newBufferedReader(Paths.get(csvFilePath));
            csvToBean = new CsvToBeanBuilder(reader)
                    .withType((Class) myClass)
                    .withIgnoreLeadingWhiteSpace(true)
                    .build( );
            Iterator<Object> myIterator = csvToBean.iterator( );
            return myIterator;
        } catch (NoSuchFileException e) {
            throw new CensusAnalyserException(CensusAnalyserException.CensusExceptionType.NO_SUCH_FILE,
                    "NO_SUCH_FILE");
        } catch (RuntimeException e) {
            throw new CensusAnalyserException(CensusAnalyserException.CensusExceptionType.INCORRECT_DATA_ISSUE,
                    "INCORRECT_DATA_ISSUE ");
        } catch (IOException e) {
            throw new CensusAnalyserException(CensusAnalyserException.CensusExceptionType.SOME_OTHER_IO_EXCEPTION,
                    "SOME_OTHER_IO_EXCEPTION");
        }
    }
}
