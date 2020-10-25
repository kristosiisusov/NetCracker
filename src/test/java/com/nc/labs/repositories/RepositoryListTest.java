package com.nc.labs.repositories;


import com.nc.labs.agreements.Agreement;
import com.nc.labs.agreements.digitaltv.AgreementOfDigitalTv;
import com.nc.labs.agreements.digitaltv.Channel;
import com.nc.labs.agreements.mobileconnection.AgreementOfMobileConnection;
import com.nc.labs.agreements.wiredinternet.AgreementOfWiredInternet;
import com.nc.labs.exceptions.EmptyRepositoryException;
import com.nc.labs.exceptions.NotFoundElement;
import com.nc.labs.people.Gender;
import com.nc.labs.people.Person;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class RepositoryListTest {
    static Person person;
    static List<Channel> listOfChannel;
    Agreement agreementOfDigitalTv;
    Agreement agreementOfMobileConnection;
    Agreement agreementOfWiredInternet;
    Repository<Agreement> repository;

    @BeforeAll
    static void setUp() {
        person = new Person("Watson", "Emma", "Charlotte",
                new GregorianCalendar(1990, Calendar.MAY, 15), Gender.FEMALE,
                30, 2002, 123456);
        listOfChannel = new ArrayList<>();
        listOfChannel.add(new Channel("TestFirstChannel"));
    }

    @Test
    void testAdd() throws NotFoundElement, EmptyRepositoryException {
        repository = new RepositoryList();
        agreementOfMobileConnection = new AgreementOfMobileConnection(new GregorianCalendar(2020, Calendar.NOVEMBER, 21),
                new GregorianCalendar(2020, Calendar.APRIL, 8), 880055536,
                person, 500, 100, 5);
        agreementOfWiredInternet = new AgreementOfWiredInternet(new GregorianCalendar(2020, Calendar.NOVEMBER, 22),
                new GregorianCalendar(2020, Calendar.APRIL, 8), 880055537,
                person, 100);
        agreementOfDigitalTv = new AgreementOfDigitalTv(new GregorianCalendar(2020, Calendar.NOVEMBER, 23),
                new GregorianCalendar(2020, Calendar.APRIL, 8), 880055538, person, listOfChannel);
        repository.add(agreementOfMobileConnection);
        repository.add(agreementOfWiredInternet);
        repository.add(agreementOfDigitalTv);
        Agreement testAgreement = repository.getItemById(agreementOfWiredInternet.getId());
        Assertions.assertEquals(agreementOfWiredInternet, testAgreement);
        testAgreement = repository.getItemById(agreementOfWiredInternet.getId());
        Assertions.assertEquals(agreementOfWiredInternet, testAgreement);
        testAgreement = repository.getItemById(agreementOfWiredInternet.getId());
        Assertions.assertEquals(agreementOfWiredInternet, testAgreement);
    }

    @Test
    void testGetNonexistentItemFromRepository() {
        repository = new RepositoryList();
        agreementOfMobileConnection = new AgreementOfMobileConnection(new GregorianCalendar(2020, Calendar.NOVEMBER, 21),
                new GregorianCalendar(2020, Calendar.APRIL, 8), 880055536,
                person, 500, 100, 5);
        repository.add(agreementOfMobileConnection);
        Exception exception = assertThrows(NotFoundElement.class,
                () -> repository.getItemById(UUID.randomUUID()));

        String expectedMessage = "Item not found in the repository by ID";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void testGetItemById() throws NotFoundElement, EmptyRepositoryException {
        repository = new RepositoryList();
        agreementOfWiredInternet = new AgreementOfWiredInternet(new GregorianCalendar(2020, Calendar.APRIL, 22),
                new GregorianCalendar(2020, Calendar.DECEMBER, 8), 880055537,
                person, 100);
        repository.add(agreementOfWiredInternet);
        Agreement testAgreementOfWiredInternet = repository.getItemById(agreementOfWiredInternet.getId());
        Assertions.assertEquals(agreementOfWiredInternet, testAgreementOfWiredInternet);
    }

    @Test
    void testRemoveItemById() throws NotFoundElement, EmptyRepositoryException {
        repository = new RepositoryList();
        agreementOfDigitalTv = new AgreementOfDigitalTv(new GregorianCalendar(2020, Calendar.APRIL, 23),
                new GregorianCalendar(2020, Calendar.DECEMBER, 8), 880055538, person, listOfChannel);
        agreementOfWiredInternet = new AgreementOfWiredInternet(new GregorianCalendar(2020, Calendar.APRIL, 22),
                new GregorianCalendar(2020, Calendar.DECEMBER, 8), 880055537,
                person, 100);
        repository.add(agreementOfWiredInternet);
        repository.add(agreementOfDigitalTv);
        repository.removeItemById(agreementOfDigitalTv.getId());
        Exception exception = assertThrows(NotFoundElement.class,
                () -> repository.getItemById(agreementOfDigitalTv.getId()));
        String expectedMessage = "Item not found in the repository by ID";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void testRemove() throws EmptyRepositoryException {
        repository = new RepositoryList();
        agreementOfDigitalTv = new AgreementOfDigitalTv(new GregorianCalendar(2020, Calendar.APRIL, 23),
                new GregorianCalendar(2020, Calendar.DECEMBER, 8), 880055538, person, listOfChannel);
        repository.add(agreementOfDigitalTv);
        repository.remove();
        Exception exception = assertThrows(EmptyRepositoryException.class,
                () -> repository.remove());
        String expectedMessage = "Repository is empty";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void testGetItemFromEmptyRepository() {
        repository = new RepositoryList();
        Exception exception = assertThrows(EmptyRepositoryException.class,
                () -> repository.getItemById(UUID.randomUUID()));
        String expectedMessage = "Repository is empty";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void testLength() {
        repository = new RepositoryList();
        agreementOfWiredInternet = new AgreementOfWiredInternet(new GregorianCalendar(2020, Calendar.APRIL, 22),
                new GregorianCalendar(2020, Calendar.DECEMBER, 8), 880055537,
                person, 100);
        repository.add(agreementOfWiredInternet);
        Assertions.assertEquals(repository.length(), 1);
    }
}
