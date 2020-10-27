package com.nc.labs.agreements.wiredinternet;

import com.nc.labs.agreements.Agreement;
import com.nc.labs.people.Person;

import java.util.*;

public class AgreementOfWiredInternet extends Agreement {
    private Map<TypeOfSpeed, Double> connectionSpeed;

    public AgreementOfWiredInternet(Calendar beginning, Calendar end,
                                    int number, Person owner, TypeOfSpeed typeOfSpeed, Double speed) {
        super(beginning, end, number, owner);
        this.connectionSpeed = new HashMap<>(1);
        this.connectionSpeed.put(typeOfSpeed,speed);
    }
    public Map<TypeOfSpeed, Double> getConnectionSpeed() {
        return connectionSpeed;
    }

    public void setConnectionSpeed(Map<TypeOfSpeed, Double> connectionSpeed) {
        this.connectionSpeed = connectionSpeed;
    }

    @Override
    public Calendar getBeginning() {
        return super.getBeginning();
    }

    @Override
    public void setBeginning(Calendar beginning) {
        super.setBeginning(beginning);
    }

    @Override
    public UUID getId() {
        return super.getId();
    }

    @Override
    public Calendar getEnd() {
        return super.getEnd();
    }

    @Override
    public int getNumber() {
        return super.getNumber();
    }

    @Override
    public Person getOwner() {
        return super.getOwner();
    }

    @Override
    public void setEnd(Calendar end) {
        super.setEnd(end);
    }

    @Override
    public void setNumber(int number) {
        super.setNumber(number);
    }

    @Override
    public void setOwner(Person owner) {
        super.setOwner(owner);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        AgreementOfWiredInternet that = (AgreementOfWiredInternet) o;
        return connectionSpeed.equals(that.connectionSpeed);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), connectionSpeed);
    }
}
