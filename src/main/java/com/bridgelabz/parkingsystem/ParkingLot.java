package com.bridgelabz.parkingsystem;

import com.bridgelabz.parkingsystem.observers.Observer;
import com.bridgelabz.parkingsystem.observers.implementations.AirportSecurityPersonal;
import com.bridgelabz.parkingsystem.observers.implementations.ParkingLotOwner;

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

    public void setParkingLotOwner(ParkingLotOwner parkingLotOwner) {
        observerList.add(parkingLotOwner);
        if (availableCapacity > 0) {
            parkingLotOwner.setSpaceAvailableBoarSign(true);
        }
    }

    public void setAirportSecurityPersonal(AirportSecurityPersonal airportSecurityPersonal){
        observerList.add(airportSecurityPersonal);
        if (availableCapacity > 0) {
            airportSecurityPersonal.setSpaceAvailableBoarSign(true);
        }
    }

    public boolean parkCar(Driver driver) {
        if (availableCapacity > 0) {
            availableCapacity--;
            for (int i = 0; i < freeSpaces.length; i++) {
                if (freeSpaces[i]) {
                    freeSpaces[i] = false;
                    carParkingMap.put(driver.carNumber, i);
                    if (availableCapacity == 0) {
                        notifyObserversSpaceAvailableBoardSign(false);
                    }
                    return true;
                }
            }
        }
        return false;
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
        for(Observer observer : observerList){
            observer.setSpaceAvailableBoarSign(value);
        }
    }
}