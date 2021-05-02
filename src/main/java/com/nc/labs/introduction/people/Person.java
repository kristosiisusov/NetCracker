package com.nc.labs.introduction.people;


import com.nc.labs.introduction.Passport;
import io.github.threetenjaxb.core.LocalDateXmlAdapter;

import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.time.LocalDate;
import java.time.Period;
import java.util.Objects;
import java.util.UUID;

public class Person {
    /**
     * Personal information about individual
     */
    private UUID id;
    private String lastName;
    private String firstName;
    private String middleName;
    private LocalDate birthdate;
    private Gender gender;
    private int age;
    Passport passport;

    public Person() {
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Person(String lastName, String firstName, String middleName, LocalDate birthdate,
                  Gender gender, int seriesOfPassport, int numberOfPassport) {
        this.id = UUID.randomUUID();
        this.lastName = lastName;
        this.firstName = firstName;
        this.middleName = middleName;
        this.birthdate = birthdate;
        this.gender = gender;
        this.age = calculateAge(birthdate);
        this.passport = new Passport(seriesOfPassport, numberOfPassport);
    }


    public Passport getPassport() {
        return passport;
    }

    public void setPassport(Passport passport) {
        this.passport = passport;
    }

    /**
     * calculating age of person.
     *
     * @return years of person. can be modify in the long run so
     * that would return month and days too
     */
    public Integer calculateAge(LocalDate birthdate) {
        Period period = Period.between(birthdate, LocalDate.now());
        return period.getYears();
    }

    public void setAge(int age) {
        this.age = age;
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

    @XmlJavaTypeAdapter(value = LocalDateXmlAdapter.class)
    public LocalDate getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(LocalDate birthdate) {
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

    public void setAge(LocalDate birthdate) {
        this.age = calculateAge(birthdate);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return age == person.age &&
                Objects.equals(lastName, person.lastName) &&
                Objects.equals(firstName, person.firstName) &&
                Objects.equals(middleName, person.middleName) &&
                Objects.equals(birthdate, person.birthdate) &&
                gender == person.gender &&
                Objects.equals(passport, person.passport);
    }

    @Override
    public int hashCode() {
        return Objects.hash(lastName, firstName, middleName, birthdate, gender, age, passport);
    }
}
