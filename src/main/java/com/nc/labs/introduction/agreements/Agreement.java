package com.nc.labs.introduction.agreements;

import com.nc.labs.introduction.people.Person;

import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;

public abstract class Agreement {
    private UUID id;
    private LocalDate beginning;
    private LocalDate end;
    private int number;
    private Person owner;

    public Agreement() {
    }

    public Agreement(LocalDate beginning, LocalDate end, int number, Person owner) {
        this.id = UUID.randomUUID();
        this.beginning = beginning;
        this.end = end;
        this.number = number;
        this.owner = owner;
    }

    public LocalDate getBeginning() {
        return beginning;
    }

    public void setBeginning(LocalDate beginning) {
        this.beginning = beginning;
    }

    public UUID getId() {
        return id;
    }

    public LocalDate getEnd() {
        return end;
    }

    public int getNumber() {
        return number;
    }

    public Person getOwner() {
        return owner;
    }

    public void setEnd(LocalDate end) {
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
                Objects.equals(beginning, agreement.beginning) &&
                Objects.equals(end, agreement.end) &&
                Objects.equals(owner, agreement.owner);
    }

    @Override
    public int hashCode() {
        return Objects.hash(beginning, end, number, owner);
    }
}
