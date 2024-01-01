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
    public Map<Car, ParkedCarInfo> carParkingMap;
    ParkingLotOwner parkingLotOwner;
    ParkingAttendant parkingAttendant;
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
        this.parkingLotOwner = parkingLotOwner;
    }
    public void setParkingAttendant(ParkingAttendant parkingAttendant){
        this.parkingAttendant = parkingAttendant;
    }
    public void addObserver(Observer observer) {
        observerList.add(observer);
        if (availableCapacity > 0) {
            observer.setSpaceAvailableBoarSign(true);
        }
    }

    public boolean parkCar(Driver driver) {
        if (availableCapacity > 0) {
            availableCapacity--;
            int parkingSlot = findParkingSpaceByAttendant(driver.handiCap, driver.largeCar);
            carParkingMap.put(driver.car, new ParkedCarInfo(parkingSlot , driver.car.number , parkingAttendant.name , LocalDateTime.now()));
            freeSpaces[parkingSlot] = false;
            parkingLotOwner.notifyCarParked(driver.car.number, LocalDateTime.now());
            if (availableCapacity == 0) {
                notifyObserversSpaceAvailableBoardSign(false);
            }
            return true;
        }
        return false;
    }
    public int findParkingSpaceByAttendant(boolean handiCap, boolean largeCar) {
        if (handiCap) {
            for (int i = 0; i < freeSpaces.length; i++) {
                if (freeSpaces[i]) {
                    return i;
                }
            }
        }
        for (int i = freeSpaces.length - ((largeCar) ? 2 : 1); i >= 0; i--) {
            if(!largeCar && freeSpaces[i]){
                return i;
            }
            if (largeCar && freeSpaces[i] && freeSpaces[i + 1]) {
                return i;
            }
        }
        return -1;
    }

    public int findParkedCarSlotByDriver(Driver driver) {
        Car car = driver.car;
        if (carParkingMap.containsKey(car)) {
            return carParkingMap.get(car).parkingSlot;
        }
        return -1;
    }

    public boolean unparkCar(Driver driver) {
        if (carParkingMap.containsKey(driver.car)) {
            Integer slotNumber = carParkingMap.get(driver.car).parkingSlot;
            freeSpaces[slotNumber] = true;
            carParkingMap.remove(driver.car);
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

    public List<Integer> getLocationsOfParkedWhiteCars() {
        List<Integer> locations = new ArrayList<>();
        for (Map.Entry<Car, ParkedCarInfo> entry : carParkingMap.entrySet()) {
            Car car = entry.getKey();
            int parkingSlot = entry.getValue().parkingSlot;
            if (isWhiteCar(car)) {
                locations.add(parkingSlot);
            }
        }
        return locations;
    }

    private boolean isWhiteCar(Car car) {
        return car.color.equalsIgnoreCase("white");
    }

    public List<ParkedCarInfo> getParkedCarInfo(String color, String company) {
        List<ParkedCarInfo> parkedCarInfos = new ArrayList<>();
        for (Map.Entry<Car, ParkedCarInfo> entry : carParkingMap.entrySet()) {
            Car car = entry.getKey();
            int parkingSlot = entry.getValue().parkingSlot;
            if ((car.color.equalsIgnoreCase(color) || color.equalsIgnoreCase("Anycolor")) && car.company.equalsIgnoreCase(company)) {
                parkedCarInfos.add(new ParkedCarInfo(parkingSlot , car.number , parkingAttendant.name, entry.getValue().parkingTime));
            }
        }
        return parkedCarInfos;
    }

    public List<ParkedCarInfo> getParkedCarInfoParkedBefore(Integer minutes) {
        List<ParkedCarInfo> parkedCarInfos = new ArrayList<>();
        LocalDateTime currentTime = LocalDateTime.now();
        for (Map.Entry<Car, ParkedCarInfo> entry : carParkingMap.entrySet()) {
            ParkedCarInfo parkedCarInfo = entry.getValue();
            if (currentTime.minusMinutes(minutes).isBefore(parkedCarInfo.parkingTime)) {
                parkedCarInfos.add(parkedCarInfo);
            }
        }
        return parkedCarInfos;
    }
}