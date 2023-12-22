package com.bridgelabz.parkingsystem.observers.implementations;

import com.bridgelabz.parkingsystem.observers.Observer;

public class AirportSecurityPersonal implements Observer {
    private boolean spaceAvailableBoarSign;
    public void setSpaceAvailableBoarSign(boolean value){
        this.spaceAvailableBoarSign = value;
    }
    public boolean getSpaceAvailableBoardSign() {
        return spaceAvailableBoarSign;
    }
}