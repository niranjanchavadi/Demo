package com.censusanalyser;

public class CensusDAO {
    public String state;
    public String areaInSqKm;
    public String densityPerSqKm;
    public String population;
    public String stateCode;
    public String srNo;
    public String tin;

    public CensusDAO(CSVStateCensus indiaCensusCSV) {
        state = indiaCensusCSV.getState();
        areaInSqKm = indiaCensusCSV.getAreaInSqKm();
        densityPerSqKm = indiaCensusCSV.getDensityPerSqKm();
        population = indiaCensusCSV.getPopulation();
    }


}
