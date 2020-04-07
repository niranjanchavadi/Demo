package com.parkinglot;

import java.util.ArrayList;
import java.util.List;

public class ParkingLot {

    protected ParkingLotOwner parkingOwner;
    private List parkedVehicles;
    private int parkingCapacity;
    private boolean parkingCapacityFull;

    public ParkingLot() {
        this.parkedVehicles = new ArrayList();
        this.parkingOwner = new ParkingLotOwner();
    }

    public void setParkingLotCapacity(int capacity) {
        this.parkingCapacity = capacity;
    }

    public void parkTheCar(Object vehicle) throws ParkingLotException {
        if (this.parkedVehicles.size() == this.parkingCapacity) {
            this.parkingCapacityFull = true;
            this.parkingOwner.setParkingCapacityFull();
            throw new ParkingLotException("No space available in the parking lot!",
                    ParkingLotException.ExceptionType.PARKING_CAPACITY_FULL);
        }
        if (this.isThisCarPresentInTheParkingLot(vehicle)) {
            throw new ParkingLotException("Car already present in parking lot!",
                    ParkingLotException.ExceptionType.CAR_ALREADY_PARKED);
        }
        this.parkedVehicles.add(vehicle);
    }

    public void unParkTheCar(Object vehicle) throws ParkingLotException {
        if (this.isThisCarPresentInTheParkingLot(vehicle)) {
            this.parkedVehicles.remove(vehicle);
            return;
        }
        throw new ParkingLotException("No such car present in parking lot!",
                ParkingLotException.ExceptionType.NO_SUCH_CAR_PARKED);
    }

    public boolean isThisCarPresentInTheParkingLot(Object vehicle) {
        if (this.parkedVehicles.contains(vehicle)) {
            return true;
        }
        return false;
    }}