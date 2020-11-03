package com.nc.labs.repositories;


import com.nc.labs.agreements.Agreement;
import com.nc.labs.agreements.digitaltv.AgreementOfDigitalTv;
import com.nc.labs.agreements.digitaltv.Channel;
import com.nc.labs.agreements.mobileconnection.AgreementOfMobileConnection;
import com.nc.labs.agreements.wiredinternet.AgreementOfWiredInternet;
import com.nc.labs.agreements.wiredinternet.TypeOfSpeed;
import com.nc.labs.people.Gender;
import com.nc.labs.people.Person;
import com.nc.labs.sorts.BubbleSort;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class RepositoryListTest {
    static Person person;
    static List<Channel> listOfChannel;
    Agreement agreementOfDigitalTv;
    Agreement agreementOfMobileConnection;
    Agreement agreementOfWiredInternet;
    IRepository<Agreement> repository;

    @BeforeAll
    static void setUp() {
        LocalDate birthday = LocalDate.of(1990, 5, 15);
        person = new Person("Watson", "Emma", "Charlotte",
                birthday, Gender.FEMALE, 2002, 123456);
        listOfChannel = new ArrayList<>();
        listOfChannel.add(new Channel("TestFirstChannel"));
    }

    @Test
    void testAdd() {
        repository = new RepositoryList<>();
        agreementOfMobileConnection = new AgreementOfMobileConnection(new GregorianCalendar(2020, Calendar.NOVEMBER, 21),
                new GregorianCalendar(2020, Calendar.APRIL, 8), 880055536,
                person, 500, 100, 5);
        agreementOfWiredInternet = new AgreementOfWiredInternet(new GregorianCalendar(2020, Calendar.NOVEMBER, 22),
                new GregorianCalendar(2020, Calendar.APRIL, 8), 880055537,
                person, TypeOfSpeed.MBIT, 5.0);
        agreementOfDigitalTv = new AgreementOfDigitalTv(new GregorianCalendar(2020, Calendar.NOVEMBER, 23),
                new GregorianCalendar(2020, Calendar.APRIL, 8), 880055538, person, listOfChannel);
        repository.add(agreementOfMobileConnection);
        repository.add(agreementOfWiredInternet);
        repository.add(agreementOfDigitalTv);
        assertEquals(agreementOfWiredInternet, repository.getItemById(agreementOfWiredInternet.getId()));
        assertEquals(agreementOfDigitalTv, repository.getItemById(agreementOfDigitalTv.getId()));
        assertEquals(agreementOfMobileConnection, repository.getItemById(agreementOfMobileConnection.getId()));
    }

    @Test
    void testGetItemById() {
        repository = new RepositoryList<>();
        agreementOfWiredInternet = new AgreementOfWiredInternet(new GregorianCalendar(2020, Calendar.APRIL, 22),
                new GregorianCalendar(2020, Calendar.DECEMBER, 8), 880055537,
                person, TypeOfSpeed.GBIT, 0.5);
        repository.add(agreementOfWiredInternet);
        assertEquals(agreementOfWiredInternet, repository.getItemById(agreementOfWiredInternet.getId()));
        assertNull(repository.getItemById(UUID.randomUUID()));

    }

    @Test
    void testRemoveItemById() {
        repository = new RepositoryList<>();
        agreementOfDigitalTv = new AgreementOfDigitalTv(new GregorianCalendar(2020, Calendar.APRIL, 23),
                new GregorianCalendar(2020, Calendar.DECEMBER, 8), 880055538, person, listOfChannel);
        agreementOfWiredInternet = new AgreementOfWiredInternet(new GregorianCalendar(2020, Calendar.APRIL, 22),
                new GregorianCalendar(2020, Calendar.DECEMBER, 8), 880055537,
                person, TypeOfSpeed.GBIT, 0.5);
        agreementOfMobileConnection = new AgreementOfMobileConnection(new GregorianCalendar(2020, Calendar.NOVEMBER, 21),
                new GregorianCalendar(2020, Calendar.APRIL, 8), 880055536,
                person, 500, 100, 5);
        repository.add(agreementOfWiredInternet);
        repository.add(agreementOfDigitalTv);
        repository.add(agreementOfMobileConnection);
        assertTrue(repository.removeItemById(agreementOfDigitalTv.getId()));
        assertTrue(repository.removeItemById(agreementOfWiredInternet.getId()));
        assertTrue(repository.removeItemById(agreementOfMobileConnection.getId()));
        assertNull(repository.getItemById(agreementOfDigitalTv.getId()));
        assertNull(repository.getItemById(agreementOfMobileConnection.getId()));
        assertNull(repository.getItemById(agreementOfWiredInternet.getId()));
        assertTrue(repository.isEmpty());
    }

    @Test
    void testRemove() {
        repository = new RepositoryList<>();
        assertFalse(repository.remove());
        agreementOfDigitalTv = new AgreementOfDigitalTv(new GregorianCalendar(2020, Calendar.APRIL, 23),
                new GregorianCalendar(2020, Calendar.DECEMBER, 8), 880055538, person, listOfChannel);
        repository.add(agreementOfDigitalTv);
        assertTrue(repository.remove());
        assertTrue(repository.isEmpty());
    }


    @Test
    void testLength() {
        repository = new RepositoryList<>();
        agreementOfWiredInternet = new AgreementOfWiredInternet(new GregorianCalendar(2020, Calendar.APRIL, 22),
                new GregorianCalendar(2020, Calendar.DECEMBER, 8), 880055537,
                person, TypeOfSpeed.KBIT, 100.55);
        repository.add(agreementOfWiredInternet);
        assertEquals(1, repository.length());
    }
    @Test
    void sort(){
        repository = new RepositoryList<>();
        agreementOfDigitalTv = new AgreementOfDigitalTv(new GregorianCalendar(2020, Calendar.APRIL, 23),
                new GregorianCalendar(2020, Calendar.DECEMBER, 8), 880055538, person, listOfChannel);
        agreementOfWiredInternet = new AgreementOfWiredInternet(new GregorianCalendar(2020, Calendar.APRIL, 22),
                new GregorianCalendar(2020, Calendar.DECEMBER, 8), 880055537,
                person, TypeOfSpeed.GBIT, 0.5);
        agreementOfMobileConnection = new AgreementOfMobileConnection(new GregorianCalendar(2020, Calendar.NOVEMBER, 21),
                new GregorianCalendar(2020, Calendar.APRIL, 8), 880055536,
                person, 500, 100, 5);
        repository.add(agreementOfDigitalTv);
        repository.add(agreementOfMobileConnection);
        repository.add(agreementOfWiredInternet);
        repository.sort((x,y) -> x.getNumber() - y.getNumber(), new BubbleSort());
        assertEquals(agreementOfMobileConnection, repository.getArray()[0]);
        assertEquals(agreementOfWiredInternet, repository.getArray()[1]);
        assertEquals(agreementOfDigitalTv, repository.getArray()[2]);
    }
    @Test
    void  search(){
        repository = new RepositoryList<>();
        agreementOfDigitalTv = new AgreementOfDigitalTv(new GregorianCalendar(2020, Calendar.APRIL, 23),
                new GregorianCalendar(2020, Calendar.DECEMBER, 8), 880055538, person, listOfChannel);
        agreementOfWiredInternet = new AgreementOfWiredInternet(new GregorianCalendar(2020, Calendar.APRIL, 22),
                new GregorianCalendar(2020, Calendar.DECEMBER, 8), 880055537,
                person, TypeOfSpeed.GBIT, 0.5);
        agreementOfMobileConnection = new AgreementOfMobileConnection(new GregorianCalendar(2020, Calendar.NOVEMBER, 21),
                new GregorianCalendar(2020, Calendar.APRIL, 8), 880055536,
                person, 500, 100, 5);
        repository.add(agreementOfDigitalTv);
        repository.add(agreementOfMobileConnection);
        repository.add(agreementOfWiredInternet);
        assertEquals(repository.search((x) -> x.getNumber() > 880055537), agreementOfDigitalTv);
    }
}
