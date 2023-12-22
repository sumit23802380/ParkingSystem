package com.bridgelabz.parkingsystem;

import java.util.HashMap;
import java.util.Map;

public class ParkingLot {
    public int totalCapacity;
    public int availableCapacity;
    private boolean[] freeSpaces;
    public Map<String , Integer> carParkingMap;
    ParkingLot(int totalCapacity){
        this.totalCapacity = totalCapacity;
        this.availableCapacity = totalCapacity;
        this.freeSpaces = new boolean[totalCapacity];
        this.carParkingMap = new HashMap<>();
        for(int i=0;i<totalCapacity;i++){
            freeSpaces[i] = true;
        }
    }
    public boolean parkCar(Driver driver){
        if(availableCapacity > 0){
            availableCapacity--;
            for(int i=0;i<freeSpaces.length;i++){
                if(freeSpaces[i]){
                    freeSpaces[i] = false;
                    carParkingMap.put(driver.carNumber , i);
                    return true;
                }
            }
        }
        return false;
    }

    public boolean unparkCar(Driver driver){
        String carNumber = driver.carNumber;
        if(carParkingMap.containsKey(carNumber)){
            Integer slotNumber = carParkingMap.get(carNumber);
            freeSpaces[slotNumber] = true;
            carParkingMap.remove(carNumber);
            availableCapacity++;
            return true;
        }
        return false;
    }
}