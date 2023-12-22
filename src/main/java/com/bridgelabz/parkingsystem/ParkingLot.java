package com.bridgelabz.parkingsystem;

public class ParkingLot {
    public int totalCapacity;
    public int availableCapacity;
    ParkingLot(int totalCapacity){
        this.totalCapacity = totalCapacity;
    }
    public boolean parkCar(Driver driver){
        return true;
    }
    public int getTotalCapacity(){
        return this.totalCapacity;
    }
    public int getAvailableCapacity(){
        return this.availableCapacity;
    }
}
