package com.bridgelabz.parkingsystem;

import com.bridgelabz.parkingsystem.observers.implementations.AirportSecurityPersonal;
import com.bridgelabz.parkingsystem.observers.implementations.ParkingLotOwner;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
public class ParkingLotTest {
    ParkingLot parkingLot;
    ParkingLotOwner parkingLotOwner;
    AirportSecurityPersonal airportSecurityPersonal;
    @Before
    public void setUp() {
        parkingLotOwner = new ParkingLotOwner();
        airportSecurityPersonal = new AirportSecurityPersonal();
        parkingLot = new ParkingLot(1);
        parkingLot.setParkingLotOwner(parkingLotOwner);
        parkingLot.addObserver(parkingLotOwner);
        parkingLot.addObserver(airportSecurityPersonal);
    }
    @Test
    public void testParkCar() {
        Driver driver1 = new Driver("Sumit" , new Car("DR2380" , "White" , "Toyota") , "1234567890");
        boolean isCarParkedForDriver1 = parkingLot.parkCar(driver1);
        assertTrue(isCarParkedForDriver1);
        Driver driver2 = new Driver("Amit" ,  new Car("KC1703" , "Blue" , "Toyota") , "7894561230");
        boolean isCarParkedForDriver2 = parkingLot.parkCar(driver2);
        assertFalse(isCarParkedForDriver2);
    }
    @Test
    public void testUnparkCar(){
        Driver driver1 = new Driver("Sumit" , new Car("DR2380" , "White" , "Toyota") , "1234567890");
        parkingLot.parkCar(driver1);
        boolean isCarUnparkedForDriver1 = parkingLot.unparkCar(driver1);
        assertTrue(isCarUnparkedForDriver1);
        isCarUnparkedForDriver1 = parkingLot.unparkCar(driver1);
        assertFalse(isCarUnparkedForDriver1);
    }
    @Test
    public void testBoardSignParkingOwnerAboutSpaceAvailability(){
        assertTrue(parkingLotOwner.getSpaceAvailableBoardSign());
        Driver driver1 = new Driver("Sumit" , new Car("DR2380" , "White" , "Toyota") , "1234567890");
        parkingLot.parkCar(driver1);
        assertFalse(parkingLotOwner.getSpaceAvailableBoardSign());
        // after unpark the car space available
        parkingLot.unparkCar(driver1);
        // after space available , so board sign is true
        assertTrue(parkingLotOwner.getSpaceAvailableBoardSign());
    }
    @Test
    public void testBoardSignAirportSecurityPersonalAboutSpaceAvailability(){
        assertTrue(airportSecurityPersonal.getSpaceAvailableBoardSign());
        Driver driver1 = new Driver("Sumit" , new Car("DR2380" , "White" , "Toyota") , "1234567890");
        parkingLot.parkCar(driver1);
        assertFalse(airportSecurityPersonal.getSpaceAvailableBoardSign());
        parkingLot.unparkCar(driver1);
        assertTrue(airportSecurityPersonal.getSpaceAvailableBoardSign());
    }

    @Test
    public void testFindParkingSpaceByAttendant() {
        parkingLot = new ParkingLot(2);
        parkingLot.setParkingLotOwner(parkingLotOwner);
        Driver driver1 = new Driver("Sumit" , new Car("DR2380" , "White" , "Toyota") , "1234567890");
        int parkingSlot = parkingLot.findParkingSpaceByAttendant(driver1.handiCap , driver1.largeCar);
        parkingLot.parkCar(driver1);
        assertEquals(1 , parkingSlot);
        Driver driver2 = new Driver("Amit" ,  new Car("KC1703" , "Blue" , "Toyota") , "7894561230");
        parkingSlot = parkingLot.findParkingSpaceByAttendant(driver2.handiCap , driver2.largeCar);
        parkingLot.parkCar(driver2);
        assertEquals(0 , parkingSlot);
        parkingSlot = parkingLot.findParkingSpaceByAttendant(driver1.handiCap , driver1.largeCar);
        assertEquals(-1 , parkingSlot);
    }

    @Test
    public void testFindParkedCarSlotByDriver() {
        Driver driver1 = new Driver("Sumit" , new Car("DR2380" , "White" , "Toyota") , "1234567890");
        parkingLot.parkCar(driver1);
        int carParkedSlot = parkingLot.findParkedCarSlotByDriver(driver1);
        assertEquals(0 , carParkedSlot);
        Driver driver2 = new Driver("Amit" ,  new Car("KC1703" , "Blue" , "Toyota") , "7894561230");
        carParkedSlot = parkingLot.findParkedCarSlotByDriver(driver2);
        assertEquals(-1 , carParkedSlot);
    }

    @Test
    public void testNotifyCarParked() {
        ParkingLotOwner mockParkingLotOwner = mock(ParkingLotOwner.class);
        parkingLot = new ParkingLot(1);
        parkingLot.setParkingLotOwner(mockParkingLotOwner);
        Driver driver1 = new Driver("Sumit" , new Car("DR2380" , "White" , "Toyota") , "1234567890");
        parkingLot.parkCar(driver1);
        verify(mockParkingLotOwner, times(1)).notifyCarParked(eq(driver1.car.number), any(LocalDateTime.class));
    }

    @Test
    public void testHandiCapDriverParkedNear(){
        parkingLot = new ParkingLot(3);
        parkingLot.setParkingLotOwner(parkingLotOwner);
        Driver driver1 = new Driver("Sumit" , new Car("DR2380" , "White" , "Toyota") , "1234567890");
        parkingLot.parkCar(driver1);
        int carParkedSlot = parkingLot.findParkedCarSlotByDriver(driver1);
        assertEquals(2 , carParkedSlot);
        Driver driver2 = new Driver("Amit" ,  new Car("KC1703" , "Blue" , "Toyota") , "7894561230");
        driver2.handiCap = true;
        parkingLot.parkCar(driver2);
        carParkedSlot = parkingLot.findParkedCarSlotByDriver(driver2);
        assertEquals(0 , carParkedSlot);
    }

    @Test
    public void testFindParkingSpaceByAttendantWithLargeCar(){
        parkingLot = new ParkingLot(3);
        parkingLot.setParkingLotOwner(parkingLotOwner);
        Driver driver1 = new Driver("Sumit" , new Car("DR2380" , "White" , "Toyota") , "1234567890");
        driver1.setLargeCar(true);
        parkingLot.parkCar(driver1);
        int carParkedSlot = parkingLot.findParkedCarSlotByDriver(driver1);
        assertEquals(1 , carParkedSlot);
    }

    @Test
    public void testLocationOfParkedWhiteCars(){
        parkingLot = new ParkingLot(3);
        parkingLot.setParkingLotOwner(parkingLotOwner);
        Driver driver1 = new Driver("Sumit" , new Car("DR2380" , "White" , "Toyota") , "1234567890");
        parkingLot.parkCar(driver1);
        Driver driver2 = new Driver("Amit" ,  new Car("KC1703" , "Blue" , "BMW") , "7894561230");
        driver2.handiCap = true;
        parkingLot.parkCar(driver2);
        Police police = new Police();
        List<Integer> locationOfParkedWhiteCars = police.getLocationOfParkedWhiteCars(parkingLot);
        List<Integer> expectedLocationOfParkedWhiteCars = new ArrayList<>();
        expectedLocationOfParkedWhiteCars.add(2);
        assertIterableEquals(expectedLocationOfParkedWhiteCars, locationOfParkedWhiteCars);
    }

    @Test
    public void testGetParkedCarInfoBlueToyotaCars(){
        parkingLot = new ParkingLot(3);
        parkingLot.setParkingLotOwner(parkingLotOwner);
        parkingLot.setParkingAttendant(new ParkingAttendant("Karan"));
        Driver driver1 = new Driver("Sumit" , new Car("DR2380" , "Blue" , "Toyota") , "1234567890");
        parkingLot.parkCar(driver1);
        Driver driver2 = new Driver("Amit" ,  new Car("KC1703" , "Blue" , "BMW") , "7894561230");
        driver2.handiCap = true;
        parkingLot.parkCar(driver2);
        Police police = new Police();
        List<ParkedCarInfo> parkedCarInfos = police.getParkedCarInfoBlueToyotaCars(parkingLot);
        List<ParkedCarInfo> expectedParkedCarInfos = new ArrayList<>();
        expectedParkedCarInfos.add(new ParkedCarInfo(2 , "DR2380" , "Karan"));
        assertIterableEquals(expectedParkedCarInfos, parkedCarInfos);
    }

    @Test
    public void testGetParkedCarInfoBMWCars(){
        parkingLot = new ParkingLot(3);
        parkingLot.setParkingLotOwner(parkingLotOwner);
        parkingLot.setParkingAttendant(new ParkingAttendant("Karan"));
        Driver driver1 = new Driver("Sumit" , new Car("DR2380" , "Blue" , "Toyota") , "1234567890");
        parkingLot.parkCar(driver1);
        Driver driver2 = new Driver("Amit" ,  new Car("KC1703" , "Blue" , "BMW") , "7894561230");
        driver2.handiCap = true;
        parkingLot.parkCar(driver2);
        Police police = new Police();
        List<ParkedCarInfo> parkedCarInfos = police.getParkedCarInfoBMWCars(parkingLot);
        List<ParkedCarInfo> expectedParkedCarInfos = new ArrayList<>();
        expectedParkedCarInfos.add(new ParkedCarInfo(0 , "KC1703" , "Karan"));
        assertIterableEquals(expectedParkedCarInfos, parkedCarInfos);
    }
}