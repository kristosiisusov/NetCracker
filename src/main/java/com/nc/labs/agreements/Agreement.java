package com.nc.labs.agreements;

import com.nc.labs.people.Person;

import java.util.Calendar;
import java.util.Objects;
import java.util.UUID;

public abstract class Agreement {
    private UUID id;
    private Calendar beginning;
    private Calendar end;
    private int number;
    private Person owner;

    public Agreement(Calendar beginning, Calendar end, int number, Person owner) {
        this.id = UUID.randomUUID();
        this.beginning = beginning;
        this.end = end;
        this.number = number;
        this.owner = owner;
    }

    public Calendar getBeginning() {
        return beginning;
    }

    public void setBeginning(Calendar beginning) {
        this.beginning = beginning;
    }

    public UUID getId() {
        return id;
    }

    public Calendar getEnd() {
        return end;
    }

    public int getNumber() {
        return number;
    }

    public Person getOwner() {
        return owner;
    }

    public void setEnd(Calendar end) {
        this.end = end;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public void setOwner(Person owner) {
        this.owner = owner;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Agreement agreement = (Agreement) o;
        return number == agreement.number &&
                Objects.equals(id, agreement.id) &&
                Objects.equals(beginning, agreement.beginning) &&
                Objects.equals(end, agreement.end) &&
                Objects.equals(owner, agreement.owner);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, beginning, end, number, owner);
    }
}
