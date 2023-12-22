package com.bridgelabz.parkingsystem;

import org.junit.Before;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ParkingLotTest {
    ParkingLot parkingLot;
    @Before
    public void setUp() {
        parkingLot = new ParkingLot(1);
    }

    @Test
    public void testParkCar() {
        Driver driver1 = new Driver("Sumit" , "DR2380" , "1234567890");
        boolean isCarParkedForDriver1 = parkingLot.parkCar(driver1);
        assertTrue(isCarParkedForDriver1);
        Driver driver2 = new Driver("Amit" , "KC1703" , "7894561230");
        boolean isCarParkedForDriver2 = parkingLot.parkCar(driver2);
        assertFalse(isCarParkedForDriver2);
    }
}