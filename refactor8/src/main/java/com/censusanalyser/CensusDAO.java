package com.censusanalyser;

public class CensusDAO {
    public String state;
    public String stateCode;
    public String srNo;
    public String tin;
    public CensusDAO(CSVStates csvStateCode) {
        stateCode = csvStateCode.getStateCode();
        srNo = csvStateCode.getSrNo();
        state = csvStateCode.getStateName();
        tin = csvStateCode.getTIN();
    }


}
