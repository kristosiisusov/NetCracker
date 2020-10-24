package com.nc.labs.agreements.digitaltv;


import java.util.Calendar;
import java.util.List;

import com.nc.labs.agreements.Agreement;
import com.nc.labs.people.Person;

public class AgreementOfDigitalTv extends Agreement {
    private List<Channel> packageOfChannel;

    public List<Channel> getPackageOfChannel() {
        return packageOfChannel;
    }

    public void setPackageOfChannel(List<Channel> packageOfChannel) {
        this.packageOfChannel = packageOfChannel;
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
