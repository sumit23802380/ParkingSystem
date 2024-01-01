package com.bridgelabz.parkingsystem;

import java.time.LocalDateTime;
import java.util.Objects;

public class ParkedCarInfo {
    public int parkingSlot;
    public String plateNumber;
    public String attendantName;
    public LocalDateTime parkingTime;
    public Driver driver;
    public ParkedCarInfo(int parkingSlot, String plateNumber, String attendantName , LocalDateTime parkingTime , Driver driver) {
        this.parkingSlot = parkingSlot;
        this.plateNumber = plateNumber;
        this.attendantName = attendantName;
        this.parkingTime = parkingTime;
        this.driver = driver;
    }

    @Override
    public String toString() {
        return "ParkedCarInfo{" +
                "parkingSlot=" + parkingSlot +
                ", plateNumber='" + plateNumber + '\'' +
                ", attendantName='" + attendantName + '\'' +
                ", parkingTime=" + parkingTime +
                ", driver=" + driver +
                '}';
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ParkedCarInfo that = (ParkedCarInfo) o;
        return parkingSlot == that.parkingSlot && Objects.equals(plateNumber, that.plateNumber) && Objects.equals(attendantName, that.attendantName) && Objects.equals(driver, that.driver);
    }

    @Override
    public int hashCode() {
        return Objects.hash(parkingSlot, plateNumber, attendantName, driver);
    }
}
