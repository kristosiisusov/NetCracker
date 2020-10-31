package com.nc.labs.agreements.wiredinternet;

public class Speed {
    TypeOfSpeed typeOfSpeed;
    double value;

    public Speed(TypeOfSpeed typeOfSpeed, double value) {
        this.typeOfSpeed = typeOfSpeed;
        this.value = value;
    }

    public TypeOfSpeed getTypeOfSpeed() {
        return typeOfSpeed;
    }

    public void setTypeOfSpeed(TypeOfSpeed typeOfSpeed) {
        this.typeOfSpeed = typeOfSpeed;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }
}
