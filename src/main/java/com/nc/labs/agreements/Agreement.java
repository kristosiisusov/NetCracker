package com.nc.labs.agreements;

import com.nc.labs.people.Person;

import java.util.Calendar;

public abstract class Agreement {
    private long id;
    private Calendar beginning;
    private Calendar end;
    private int number;
    private Person owner;

    public Calendar getBeginning() {
        return beginning;
    }

    public void setBeginning(Calendar beginning) {
        this.beginning = beginning;
    }

    public long getId() {
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

    public void setId(long id) {
        this.id = id;
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
}
