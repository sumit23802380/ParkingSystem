package com.bridgelabz.parkingsystem;

import org.junit.Before;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ParkingLotTest {
    ParkingLot parkingLot;
    ParkingLotOwner parkingLotOwner;
    @Before
    public void setUp() {
        parkingLotOwner = new ParkingLotOwner();
        parkingLot = new ParkingLot(1);
        parkingLot.setParkingLotOwner(parkingLotOwner);
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

    @Test
    public void testUnparkCar(){

        Driver driver1 = new Driver("Sumit" , "DR2380" , "1234567890");
        parkingLot.parkCar(driver1);
        boolean isCarUnparkedForDriver1 = parkingLot.unparkCar(driver1);
        assertTrue(isCarUnparkedForDriver1);
        isCarUnparkedForDriver1 = parkingLot.unparkCar(driver1);
        assertFalse(isCarUnparkedForDriver1);
    }

    @Test
    public void testBoardSignParkingOwnerAboutSpaceAvailability(){
        assertTrue(parkingLotOwner.getSpaceAvailableBoardSign());
        Driver driver1 = new Driver("Sumit" , "DR2380" , "1234567890");
        parkingLot.parkCar(driver1);
        assertFalse(parkingLotOwner.getSpaceAvailableBoardSign());
        parkingLot.unparkCar(driver1);
        assertTrue(parkingLotOwner.getSpaceAvailableBoardSign());
    }
}