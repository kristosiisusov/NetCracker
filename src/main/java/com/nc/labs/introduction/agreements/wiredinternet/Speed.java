package com.nc.labs.introduction.agreements.wiredinternet;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Speed speed = (Speed) o;
        return Double.compare(speed.value, value) == 0 &&
                typeOfSpeed == speed.typeOfSpeed;
    }

    @Override
    public int hashCode() {
        return Objects.hash(typeOfSpeed, value);
    }
}
