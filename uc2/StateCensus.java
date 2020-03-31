package com.censusanalyserudl;

import com.opencsv.bean.CsvBindByName;

public class StateCensus {

    @CsvBindByName(column = "SrNo")
    private int slNo;

    @CsvBindByName(column = "StateName", required = true)
    private String StateName;

    @CsvBindByName(column = "TIN")
    private int tin;

    @CsvBindByName(column = "StateCode", required = true)
    private String stateCode;



    public StateCensus() {

    }

    public int getSlNo() {
        return slNo;
    }

    public void setSlNo(int slNo) {
        this.slNo = slNo;
    }

    public String getStateName() {
        return StateName;
    }

    public void setStateName(String stateName) {
        StateName = stateName;
    }

    public int getTin() {
        return tin;
    }

    public void setTin(int tin) {
        this.tin = tin;
    }

    public String getStateCode() {
        return stateCode;
    }

    public void setStateCode(String stateCode) {
        this.stateCode = stateCode;
    }


    @Override
    public String toString() {
        return "StateCensus{" +
                "slNo=" + slNo +
                ", StateName='" + StateName + '\'' +
                ", tin=" + tin +
                ", stateCode='" + stateCode + '\'' +
                '}' + "\n";
    }



}