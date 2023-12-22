package com.bridgelabz.parkingsystem;

import com.bridgelabz.parkingsystem.observers.Observer;
import com.bridgelabz.parkingsystem.observers.implementations.AirportSecurityPersonal;
import com.bridgelabz.parkingsystem.observers.implementations.ParkingLotOwner;
import org.junit.Before;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ParkingLotTest {
    ParkingLot parkingLot;
    Observer parkingLotOwner;
    Observer airportSecurityPersonal;
    @Before
    public void setUp() {
        parkingLotOwner = new ParkingLotOwner();
        airportSecurityPersonal = new AirportSecurityPersonal();
        parkingLot = new ParkingLot(1);
        parkingLot.addObserver(parkingLotOwner);
        parkingLot.addObserver(airportSecurityPersonal);
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
    @Test
    public void testBoardSignAirportSecurityPersonalAboutSpaceAvailability(){
        assertTrue(airportSecurityPersonal.getSpaceAvailableBoardSign());
        Driver driver1 = new Driver("Sumit" , "DR2380" , "1234567890");
        parkingLot.parkCar(driver1);
        assertFalse(airportSecurityPersonal.getSpaceAvailableBoardSign());
        parkingLot.unparkCar(driver1);
        assertTrue(airportSecurityPersonal.getSpaceAvailableBoardSign());
    }
}