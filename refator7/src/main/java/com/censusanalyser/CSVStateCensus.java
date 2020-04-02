package com.censusanalyser;
import com.opencsv.bean.CsvBindByName;

public class CSVStateCensus {
    //BINDING THE COLUMN NAME IN CsvBindByName CLASS
    @CsvBindByName(column = "State")
    private String State;

    @CsvBindByName(column = "population")
    private String Population;

    @CsvBindByName
    private String AreaInSqKm;

    @CsvBindByName
    private String DensityPerSqKm;

    public void setState(String state) {
        State = state;
    }

    //GETTER AND SETTER METHOD TO ENCAPSULATE AND PROVIDE WAY TO USER TO USE PRIVATE DATA
    public String getState() {
        return State;
    }

    public void setPopulation(String population) {
        Population = population;
    }

    public String getPopulation() {
        return Population;
    }

    public void setAreaInSqKm(String areaInSqKm) {
        AreaInSqKm = areaInSqKm;
    }

    public String getAreaInSqKm() {
        return AreaInSqKm;
    }

    public void setDensityPerSqKm(String densityPerSqKm) {
        DensityPerSqKm = densityPerSqKm;
    }

    public String getDensityPerSqKm() {
        return DensityPerSqKm;
    }
}