package com.censusanalyser;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

import java.io.Reader;
import java.util.Iterator;
import java.util.List;

public class OpenCSVBuilder<E> implements ICSVBuilder {
    @Override
    public <E> Iterator<E> getCSVFileIterator(Reader reader, Class csvClass) throws CSVBuilderException {
        try{
            CsvToBeanBuilder<E> csvToBeanBuilder = new CsvToBeanBuilder<>(reader);
            csvToBeanBuilder.withType(csvClass);
            csvToBeanBuilder.withIgnoreLeadingWhiteSpace(true);
            CsvToBean<E> csvToBean = csvToBeanBuilder.build();
            return csvToBean.iterator();
        } catch (RuntimeException e) {
            throw new CSVBuilderException(e.getMessage(),CSVBuilderException.ExceptionType.UNABLE_TO_PARSE);
        }
    }
    @Override
    public List getCSVFileList(Reader reader, Class csvClass) throws CSVBuilderException {
        try{
            return this.getCSVBean(reader,csvClass).parse();
        }catch (RuntimeException e) {
            throw new CSVBuilderException(e.getMessage(),CSVBuilderException.ExceptionType.UNABLE_TO_PARSE);
        }
    }
    private CsvToBean<E> getCSVBean(Reader reader,Class csvClass) {
        CsvToBeanBuilder<E> csvToBeanBuilder = new CsvToBeanBuilder<>(reader);
        csvToBeanBuilder.withType(csvClass);
        csvToBeanBuilder.withIgnoreLeadingWhiteSpace(true);
        return csvToBeanBuilder.build();
    }
}
