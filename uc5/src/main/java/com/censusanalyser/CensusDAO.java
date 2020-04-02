package com.censusanalyser;

public class CensusDAO {
    public String state;
    public Double areaInSqKm;
    public Double densityPerSqKm;
    public Integer population;
    public String stateCode;
    public Integer srNo;
    public Integer tin;

    public CensusDAO(CSVStateCensus indiaCensusCSV) {
        state = indiaCensusCSV.getState();
        areaInSqKm = indiaCensusCSV.getAreaInSqKm();
        densityPerSqKm = indiaCensusCSV.getDensityPerSqKm();
        population = indiaCensusCSV.getPopulation();
    }

    public CensusDAO(CSVStates csvStateCode) {
        stateCode = csvStateCode.getStateCode();
        srNo = csvStateCode.getSrNo();
        state = csvStateCode.getStateName();
        tin = csvStateCode.getTIN();
    }


}
