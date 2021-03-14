package com.nc.labs.introduction.repositories;


import com.nc.labs.introduction.agreements.Agreement;
import com.nc.labs.introduction.agreements.digitaltv.AgreementOfDigitalTv;
import com.nc.labs.introduction.agreements.digitaltv.Channel;
import com.nc.labs.introduction.agreements.mobileconnection.AgreementOfMobileConnection;
import com.nc.labs.introduction.agreements.wiredinternet.AgreementOfWiredInternet;
import com.nc.labs.introduction.agreements.wiredinternet.TypeOfSpeed;
import com.nc.labs.introduction.people.Gender;
import com.nc.labs.introduction.people.Person;
import com.nc.labs.introduction.repositories.IRepository;
import com.nc.labs.introduction.repositories.RepositoryList;
import com.nc.labs.introduction.sorts.BubbleSort;
import com.nc.labs.introduction.sorts.SelectionSort;
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
        agreementOfMobileConnection = new AgreementOfMobileConnection(LocalDate.of(2020,11, 21),
                LocalDate.of(2020, 4, 8), 880055536,
                person, 500, 100, 5);
        agreementOfWiredInternet = new AgreementOfWiredInternet(LocalDate.of(2020,11, 22),
                LocalDate.of(2020, 4, 8), 880055537,
                person, TypeOfSpeed.MBIT, 5.0);
        agreementOfDigitalTv = new AgreementOfDigitalTv(LocalDate.of(2020,11, 23),
                LocalDate.of(2020, 4, 8), 880055538, person, listOfChannel);
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
        agreementOfWiredInternet = new AgreementOfWiredInternet(LocalDate.of(2020, 4, 22),
                LocalDate.of(2020, 12, 8), 880055537,
                person, TypeOfSpeed.GBIT, 0.5);
        repository.add(agreementOfWiredInternet);
        assertEquals(agreementOfWiredInternet, repository.getItemById(agreementOfWiredInternet.getId()));
        assertNull(repository.getItemById(UUID.randomUUID()));

    }

    @Test
    void testRemoveItemById() {
        repository = new RepositoryList<>();
        agreementOfDigitalTv = new AgreementOfDigitalTv(LocalDate.of(2020, 4, 23),
                LocalDate.of(2020, 12, 8), 880055538, person, listOfChannel);
        agreementOfWiredInternet = new AgreementOfWiredInternet(LocalDate.of(2020, 4, 22),
                LocalDate.of(2020, 12, 8), 880055537,
                person, TypeOfSpeed.GBIT, 0.5);
        agreementOfMobileConnection = new AgreementOfMobileConnection(LocalDate.of(2020, 11, 21),
                LocalDate.of(2020, 4, 8), 880055536,
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
        agreementOfDigitalTv = new AgreementOfDigitalTv(LocalDate.of(2020,4, 23),
                LocalDate.of(2020, 12, 8), 880055538, person, listOfChannel);
        repository.add(agreementOfDigitalTv);
        assertTrue(repository.remove());
        assertTrue(repository.isEmpty());
    }


    @Test
    void testLength() {
        repository = new RepositoryList<>();
        agreementOfWiredInternet = new AgreementOfWiredInternet(LocalDate.of(2020, 4, 22),
                LocalDate.of(2020, 12, 8), 880055537,
                person, TypeOfSpeed.KBIT, 100.55);
        repository.add(agreementOfWiredInternet);
        assertEquals(1, repository.length());
    }
    @Test
    void sort(){
        repository = new RepositoryList<>();
        agreementOfDigitalTv = new AgreementOfDigitalTv(LocalDate.of(2020, 4, 23),
                LocalDate.of(2020, 12, 8), 880055538, person, listOfChannel);
        agreementOfWiredInternet = new AgreementOfWiredInternet(LocalDate.of(2020, 4, 22),
                LocalDate.of(2020, 12, 8), 880055537,
                person, TypeOfSpeed.GBIT, 0.5);
        agreementOfMobileConnection = new AgreementOfMobileConnection(LocalDate.of(2020, 11, 21),
                LocalDate.of(2020, 4, 8), 880055536,
                person, 500, 100, 5);
        repository.add(agreementOfDigitalTv);
        repository.add(agreementOfMobileConnection);
        repository.add(agreementOfWiredInternet);
        Comparator<Agreement> comparator = (x,y) -> x.getNumber() - y.getNumber();
        IRepository<Agreement> repository1 = new RepositoryList<>(repository.toArray());
        repository.sort(comparator);
        if(repository1.getTypeOfSort().getClass() == BubbleSort.class) {
            repository1.setTypeOfSort(new SelectionSort<>());
            repository1.sort(comparator);
        }
        for (int i = 0; i < repository.length() - 1; i++) {
            assertTrue((comparator.compare(repository.getItemsByIndex(i), repository.getItemsByIndex(i + 1)) < 0));
            assertTrue((comparator.compare(repository1.getItemsByIndex(i), repository1.getItemsByIndex(i + 1)) < 0));
        }
    }
    @Test
    void  search(){
        repository = new RepositoryList<>();
        agreementOfDigitalTv = new AgreementOfDigitalTv(LocalDate.of(2020, 4, 23),
                LocalDate.of(2020, 12, 8), 880055538, person, listOfChannel);
        agreementOfWiredInternet = new AgreementOfWiredInternet(LocalDate.of(2020, 4, 22),
                LocalDate.of(2020, 12, 8), 880055537,
                person, TypeOfSpeed.GBIT, 0.5);
        agreementOfMobileConnection = new AgreementOfMobileConnection(LocalDate.of(2020, 11, 21),
                LocalDate.of(2020, 4, 8), 880055536,
                person, 500, 100, 5);
        repository.add(agreementOfDigitalTv);
        repository.add(agreementOfMobileConnection);
        repository.add(agreementOfWiredInternet);
        IRepository<Agreement> foundItems;
        foundItems = repository.search((x) -> x.getNumber() > 880055536);
        for (int i = 0; i < foundItems.length(); i++) {
            assertTrue(foundItems.getItemsByIndex(i).getNumber() > 880055536);
        }
    }
}
