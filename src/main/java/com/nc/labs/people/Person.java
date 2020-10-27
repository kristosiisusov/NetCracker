package com.nc.labs.people;



import java.time.LocalDate;
import java.time.Period;
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

    public Person(String lastName, String firstName, String middleName, LocalDate birthdate,
                  Gender gender, int seriesOfPassport, int numberOfPassport) {
        this.id = UUID.randomUUID();
        this.lastName = lastName;
        this.firstName = firstName;
        this.middleName = middleName;
        this.birthdate = birthdate;
        this.gender = gender;
        this.age = calculateAge(birthdate);
        this.passport = new Passport(seriesOfPassport,numberOfPassport);
    }

    /**
     * class to store the data from person's passport
     */
    private class Passport{
        private int seriesOfPassport;
        private int numberOfPassport;

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
    }

    /**
     * calculating age of person.
     * @return years of person. can be modify in the long run so
     * that would return month and days too
     */
    private Integer calculateAge(LocalDate birthdate){
        Period period = Period.between(birthdate, LocalDate.now());
        return period.getYears();
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
}
