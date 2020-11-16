package com.nc.labs.agreements.mobileconnection;



import com.nc.labs.agreements.Agreement;
import com.nc.labs.people.Person;

import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;

public class AgreementOfMobileConnection extends Agreement {
    private int countOfMinutes;
    private int countOfSms;
    private int countOfGbTraffic;

    public AgreementOfMobileConnection() {

    }

    public AgreementOfMobileConnection(LocalDate beginning, LocalDate end, int number, Person owner,
                                       int countOfMinutes, int countOfSms, int countOfGbTraffic) {
        super(beginning, end, number, owner);
        this.countOfMinutes = countOfMinutes;
        this.countOfSms = countOfSms;
        this.countOfGbTraffic = countOfGbTraffic;
    }

    public int getCountOfMinutes() {
        return countOfMinutes;
    }

    public int getCountOfSms() {
        return countOfSms;
    }

    public int getCountOfGbTraffic() {
        return countOfGbTraffic;
    }

    public void setCountOfMinutes(int countOfMinutes) {
        this.countOfMinutes = countOfMinutes;
    }

    public void setCountOfSms(int countOfSms) {
        this.countOfSms = countOfSms;
    }

    public void setCountOfGbTraffic(int countOfGbTraffic) {
        this.countOfGbTraffic = countOfGbTraffic;
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
        AgreementOfMobileConnection that = (AgreementOfMobileConnection) o;
        return countOfMinutes == that.countOfMinutes &&
                countOfSms == that.countOfSms &&
                countOfGbTraffic == that.countOfGbTraffic;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), countOfMinutes, countOfSms, countOfGbTraffic);
    }
}
