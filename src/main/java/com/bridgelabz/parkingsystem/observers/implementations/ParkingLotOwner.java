package com.bridgelabz.parkingsystem.observers.implementations;

import com.bridgelabz.parkingsystem.observers.Observer;

import java.time.LocalDateTime;

public class ParkingLotOwner implements Observer {
    private boolean spaceAvailableBoarSign;
    public void setSpaceAvailableBoarSign(boolean value){
        this.spaceAvailableBoarSign = value;
    }
    public boolean getSpaceAvailableBoardSign() {
        return spaceAvailableBoarSign;
    }

    public void notifyCarParked(String carNumber , LocalDateTime carParkingTime){

    }
}