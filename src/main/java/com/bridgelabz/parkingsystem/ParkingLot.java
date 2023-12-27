package com.bridgelabz.parkingsystem;

import com.bridgelabz.parkingsystem.observers.Observer;
import com.bridgelabz.parkingsystem.observers.implementations.ParkingLotOwner;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ParkingLot {
    public int totalCapacity;
    public int availableCapacity;
    private boolean[] freeSpaces;
    public List<Observer> observerList;
    public Map<String, Integer> carParkingMap;
    ParkingLotOwner parkingLotOwner;

    ParkingLot(int totalCapacity) {
        this.totalCapacity = totalCapacity;
        this.availableCapacity = totalCapacity;
        this.freeSpaces = new boolean[totalCapacity];
        this.carParkingMap = new HashMap<>();
        this.observerList = new ArrayList<>();
        for (int i = 0; i < totalCapacity; i++) {
            freeSpaces[i] = true;
        }
    }
    public void setParkingLotOwner(ParkingLotOwner parkingLotOwner){
        this.parkingLotOwner = parkingLotOwner;
    }
    public void addObserver(Observer observer){
        observerList.add(observer);
        if(availableCapacity > 0){
            observer.setSpaceAvailableBoarSign(true);
        }
    }
    public boolean parkCar(Driver driver) {
        if (availableCapacity > 0) {
            availableCapacity--;
            int parkingSlot = findParkingSpaceByAttendant(driver.handiCap);
            carParkingMap.put(driver.carNumber, parkingSlot);
            freeSpaces[parkingSlot] = false;
            parkingLotOwner.notifyCarParked(driver.carNumber, LocalDateTime.now());
            if (availableCapacity == 0) {
                notifyObserversSpaceAvailableBoardSign(false);
            }
            return true;
        }
        return false;
    }

    public int findParkingSpaceByAttendant(boolean handiCap) {
        if(handiCap){
            for(int i=0;i<freeSpaces.length;i++){
                if(freeSpaces[i]){
                    return i;
                }
            }
        }
        for(int i= freeSpaces.length-1;i>=0;i--){
            if(freeSpaces[i]){
                return i;
            }
        }
        return -1;
    }
    public int findParkedCarSlotByDriver(Driver driver) {
        String carNumber = driver.carNumber;
        if (carParkingMap.containsKey(carNumber)) {
            return carParkingMap.get(carNumber);
        }
        return -1;
    }

    public boolean unparkCar(Driver driver) {
        String carNumber = driver.carNumber;
        if (carParkingMap.containsKey(carNumber)) {
            Integer slotNumber = carParkingMap.get(carNumber);
            freeSpaces[slotNumber] = true;
            carParkingMap.remove(carNumber);
            availableCapacity++;
            if (availableCapacity > 0) {
                notifyObserversSpaceAvailableBoardSign(true);
            }
            return true;
        }
        return false;
    }

    private void notifyObserversSpaceAvailableBoardSign(boolean value) {
        for (Observer observer : observerList) {
            observer.setSpaceAvailableBoarSign(value);
        }
    }
}