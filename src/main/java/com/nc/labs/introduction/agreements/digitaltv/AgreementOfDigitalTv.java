package com.nc.labs.introduction.agreements.digitaltv;


import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

import com.nc.labs.introduction.agreements.Agreement;
import com.nc.labs.introduction.people.Person;

public class AgreementOfDigitalTv extends Agreement {
    private List<Channel> packageOfChannel;

    public AgreementOfDigitalTv() {

    }

    public AgreementOfDigitalTv(LocalDate beginning, LocalDate end,
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
    public LocalDate getBeginning() {
        return super.getBeginning();
    }

    @Override
    public void setBeginning(LocalDate beginning) {
        super.setBeginning(beginning);
    }

    @Override
    public UUID getId() {
        return super.getId();
    }

    @Override
    public LocalDate getEnd() {
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
    public void setEnd(LocalDate end) {
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
