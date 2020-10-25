package com.nc.labs.agreements.digitaltv;


import java.util.Calendar;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

import com.nc.labs.agreements.Agreement;
import com.nc.labs.people.Person;

public class AgreementOfDigitalTv extends Agreement {
    private List<Channel> packageOfChannel;

    public AgreementOfDigitalTv(Calendar beginning, Calendar end,
                                int number, Person owner, List<Channel> packageOfChannel) {
        super(beginning, end, number, owner);
        this.packageOfChannel = packageOfChannel;
    }

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
        AgreementOfDigitalTv that = (AgreementOfDigitalTv) o;
        return Objects.equals(packageOfChannel, that.packageOfChannel);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), packageOfChannel);
    }
}
