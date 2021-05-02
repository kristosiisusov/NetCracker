package com.nc.labs.introduction;

import java.util.Objects;

/**
 * class to store the data from person's passport
 */
public class Passport {
    private int seriesOfPassport;
    private int numberOfPassport;

    public Passport() {
    }

    public Passport(int seriesOfPassport, int numberOfPassport) {
        this.seriesOfPassport = seriesOfPassport;
        this.numberOfPassport = numberOfPassport;

    }

    public int getSeriesOfPassport() {
        return seriesOfPassport;
    }

    public void setSeriesOfPassport(int seriesOfPassport) {
        this.seriesOfPassport = seriesOfPassport;
    }

    public int getNumberOfPassport() {
        return numberOfPassport;
    }

    public void setNumberOfPassport(int numberOfPassport) {
        this.numberOfPassport = numberOfPassport;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Passport passport = (Passport) o;
        return seriesOfPassport == passport.seriesOfPassport && numberOfPassport == passport.numberOfPassport;
    }

    @Override
    public int hashCode() {
        return Objects.hash(seriesOfPassport, numberOfPassport);
    }
}
