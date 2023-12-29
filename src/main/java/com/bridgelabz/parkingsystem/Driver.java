package com.bridgelabz.parkingsystem;

public class Driver {
    public String name;
    public String carNumber;
    public String contactNumber;

    public void setLargeCar(boolean largeCar) {
        this.largeCar = largeCar;
    }

    public boolean largeCar;
    public boolean handiCap;
    public Driver(String name, String carNumber, String contactNumber) {
        this.name = name;
        this.carNumber = carNumber;
        this.contactNumber = contactNumber;
        this.handiCap = false;
        this.largeCar = false;
    }

}
