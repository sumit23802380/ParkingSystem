package com.bridgelabz.parkingsystem;

public class Driver {
    public String name;
    public Car car;
    public String contactNumber;

    public void setLargeCar(boolean largeCar) {
        this.largeCar = largeCar;
    }

    public boolean largeCar;
    public boolean handiCap;
    public Driver(String name, Car car, String contactNumber) {
        this.name = name;
        this.car = car;
        this.contactNumber = contactNumber;
        this.handiCap = false;
        this.largeCar = false;
    }

}
