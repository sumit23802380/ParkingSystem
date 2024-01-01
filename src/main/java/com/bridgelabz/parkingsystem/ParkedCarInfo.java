package com.bridgelabz.parkingsystem;

public class ParkedCarInfo {
    private int parkingSlot;
    private String plateNumber;
    private String attendantName;
    public ParkedCarInfo(int parkingSlot, String plateNumber, String attendantName) {
        this.parkingSlot = parkingSlot;
        this.plateNumber = plateNumber;
        this.attendantName = attendantName;
    }
}
