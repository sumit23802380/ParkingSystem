package com.bridgelabz.parkingsystem;

import java.util.Objects;

public class ParkedCarInfo {
    private int parkingSlot;
    private String plateNumber;
    private String attendantName;
    public ParkedCarInfo(int parkingSlot, String plateNumber, String attendantName) {
        this.parkingSlot = parkingSlot;
        this.plateNumber = plateNumber;
        this.attendantName = attendantName;
    }

    @Override
    public String toString() {
        return "ParkedCarInfo{" +
                "parkingSlot=" + parkingSlot +
                ", plateNumber='" + plateNumber + '\'' +
                ", attendantName='" + attendantName + '\'' +
                '}';
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        ParkedCarInfo other = (ParkedCarInfo) obj;
        return parkingSlot == other.parkingSlot &&
                plateNumber.equals(other.plateNumber) &&
                attendantName.equals(other.attendantName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(parkingSlot, plateNumber, attendantName);
    }
}
