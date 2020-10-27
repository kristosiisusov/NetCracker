package com.nc.labs.people;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

class PersonTest {

    @Test
    void testGetAge() {
        LocalDate birthday = LocalDate.of(1990, 5, 15);
        Person person = new Person("Watson", "Emma", "Charlotte",
                birthday, Gender.FEMALE, 2002, 123456);
        Assertions.assertEquals(30, person.getAge());
    }
}
