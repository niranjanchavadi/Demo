package com.censusanalyser;

import java.util.Map;

public class USCensusAdapter extends CensusAdapter {
    @Override
    public Map<String, CensusDAO> loadCensusData(String... csvFilePath) throws CSVBuilderException {
        return super.loadCensusData(UsCSVData.class, csvFilePath[0]);
    }
}