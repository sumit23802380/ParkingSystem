package com.bridgelabz.parkingsystem;

import java.util.List;

public class Police {
    List<Integer> getLocationOfParkedWhiteCars(ParkingLot parkingLot){
        return parkingLot.getLocationsOfParkedWhiteCars();
    }
    List<ParkedCarInfo> getParkedCarInfoBlueToyotaCars(ParkingLot parkingLot){
        return parkingLot.getParkedCarInfo("Blue" , "Toyota");
    }
    List<ParkedCarInfo> getParkedCarInfoBMWCars(ParkingLot parkingLot){
        return parkingLot.getParkedCarInfo("Anycolor" , "BMW");
    }

    public List<ParkedCarInfo> getParkedCarInfoBefore30Mins(ParkingLot parkingLot) {
        return parkingLot.getParkedCarInfoParkedBefore(30);
    }

    public List<Integer> getParkedCarInfoDriverIsHandicap(ParkingLot parkingLot) {
        return parkingLot.getParkedCarInfoDriverIsHandicap();
    }

    public List<ParkedCarInfo> getAllParkedCarInfo(ParkingLot parkingLot) {
        return parkingLot.getAllParkedCarInfo();
    }
}
