package com.nc.labs.data;

import com.nc.labs.introduction.agreements.Agreement;
import com.nc.labs.introduction.agreements.digitaltv.AgreementOfDigitalTv;
import com.nc.labs.introduction.agreements.digitaltv.Channel;
import com.nc.labs.introduction.agreements.mobileconnection.AgreementOfMobileConnection;
import com.nc.labs.introduction.agreements.wiredinternet.AgreementOfWiredInternet;
import com.nc.labs.introduction.agreements.wiredinternet.TypeOfSpeed;
import com.nc.labs.introduction.people.Gender;
import com.nc.labs.introduction.people.Person;
import com.nc.labs.introduction.repositories.RepositoryList;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


class JaxbProcessorTest {

    static Person person;
    static List<Channel> listOfChannel;
    Agreement agreementOfDigitalTv;
    Agreement agreementOfMobileConnection;
    Agreement agreementOfWiredInternet;
    RepositoryList<Agreement> repositoryTo;

    @BeforeAll
    static void setUp() {
        LocalDate birthday = LocalDate.of(1990, 5, 15);
        person = new Person("Watson", "Emma", "Charlotte",
                birthday, Gender.FEMALE, 2002, 123456);
        listOfChannel = new ArrayList<>();
        listOfChannel.add(new Channel("TestFirstChannel"));
    }


    @Test
    void storeAndRestoreXmlTest() {
        String path = "src/test/java/resources/JaxbToXmlTest.xml";
        repositoryTo = new RepositoryList<>();
        agreementOfMobileConnection = new AgreementOfMobileConnection(LocalDate.of(2020, 11, 21),
                LocalDate.of(2020, 4, 8), 880055536,
                person, 500, 100, 5);
        agreementOfWiredInternet = new AgreementOfWiredInternet(LocalDate.of(2020, 11, 22),
                LocalDate.of(2020, 4, 8), 880055537,
                person, TypeOfSpeed.MBIT, 5.0);
        agreementOfDigitalTv = new AgreementOfDigitalTv(LocalDate.of(2020, 11, 23),
                LocalDate.of(2020, 4, 8), 880055538, person, listOfChannel);
        repositoryTo.add(agreementOfMobileConnection);
        repositoryTo.add(agreementOfWiredInternet);
        repositoryTo.add(agreementOfDigitalTv);
        JaxbProcessor jaxbProcessor = new JaxbProcessor(path, path);
        jaxbProcessor.storeTo(repositoryTo);
        RepositoryList<Agreement> repositoryFrom = jaxbProcessor.restoreFrom();
        for (int i = 0; i < repositoryFrom.length(); i++) {
            assertEquals(repositoryFrom.getItemsByIndex(i), repositoryTo.getItemsByIndex(i));
        }
    }

}