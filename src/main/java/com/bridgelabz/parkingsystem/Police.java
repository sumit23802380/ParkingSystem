package com.bridgelabz.parkingsystem;

import java.util.List;

public class Police {
    List<Integer> getLocationOfParkedWhiteCars(ParkingLot parkingLot){
        return parkingLot.getLocationsOfParkedWhiteCars();
    }
    List<ParkedCarInfo> getParkedCarInfo(ParkingLot parkingLot){
        return parkingLot.getParkedCarInfo("Blue" , "Toyota");
    }
}
