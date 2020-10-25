package com.nc.labs.people;



import java.util.Calendar;
import java.util.UUID;

public class Person {
    private UUID id;
    private String lastName;
    private String firstName;
    private String middleName;
    private Calendar birthdate;
    private Gender gender;
    private int age;
    private int seriesOfPassport;
    private int numberOfPassport;

    public Person(String lastName, String firstName, String middleName, Calendar birthdate,
                  Gender gender, int age, int seriesOfPassport, int numberOfPassport) {
        this.id = UUID.randomUUID();
        this.lastName = lastName;
        this.firstName = firstName;
        this.middleName = middleName;
        this.birthdate = birthdate;
        this.gender = gender;
        this.age = age;
        this.seriesOfPassport = seriesOfPassport;
        this.numberOfPassport = numberOfPassport;
    }

    public UUID getId() {
        return id;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public Calendar getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Calendar birthdate) {
        this.birthdate = birthdate;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
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
}
