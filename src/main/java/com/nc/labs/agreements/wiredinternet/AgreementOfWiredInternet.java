package com.nc.labs.agreements.wiredinternet;

import com.nc.labs.agreements.Agreement;
import com.nc.labs.people.Person;

import java.util.Calendar;

public class AgreementOfWiredInternet extends Agreement {
    private double connectionSpeed;

    public double getConnectionSpeed() {
        return connectionSpeed;
    }

    public void setConnectionSpeed(double connectionSpeed) {
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
    public long getId() {
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
    public void setId(long id) {
        super.setId(id);
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
}
