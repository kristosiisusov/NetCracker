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

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class JdbcProcessorTest {
    static Person personFirst;
    static Person personSecond;
    static List<Channel> listOfChannel;
    Agreement agreementOfDigitalTv;
    Agreement agreementOfMobileConnection;
    Agreement agreementOfWiredInternet;
    RepositoryList<Agreement> repositoryTo;

    @BeforeAll
    static void setUp() {
        LocalDate birthday = LocalDate.of(1990, 5, 15);
        personFirst = new Person("Watson", "Emma", "Charlotte",
                birthday, Gender.FEMALE, 2002, 123456);
        personSecond = new Person("Adrien", "Brody", "",
                birthday, Gender.MALE, 2004, 123486);
        listOfChannel = new ArrayList<>();
        listOfChannel.add(new Channel("TestFirstChannel"));
    }

    @Test
    void storeAndRestoreDBTest() throws SQLException, IOException, ClassNotFoundException {
        JdbcProcessor jdbcProcessor = new JdbcProcessor();
        jdbcProcessor.cleanDB("person");
        repositoryTo = new RepositoryList<>();
        agreementOfMobileConnection = new AgreementOfMobileConnection(LocalDate.of(2020, 11, 21),
                LocalDate.of(2020, 4, 8), 880055536,
                personFirst, 500, 100, 5);
        agreementOfWiredInternet = new AgreementOfWiredInternet(LocalDate.of(2020, 11, 22),
                LocalDate.of(2020, 4, 8), 880055537,
                personSecond, TypeOfSpeed.MBIT, 5.0);
        agreementOfDigitalTv = new AgreementOfDigitalTv(LocalDate.of(2020, 11, 23),
                LocalDate.of(2020, 4, 8), 880055538, personSecond, listOfChannel);
        repositoryTo.add(agreementOfMobileConnection);
        repositoryTo.add(agreementOfWiredInternet);
        repositoryTo.add(agreementOfDigitalTv);
        jdbcProcessor.storeTo(repositoryTo);
        RepositoryList<Agreement> repositoryFrom = jdbcProcessor.restoreFrom();
        for (int i = 0; i < repositoryFrom.length(); i++) {
            assertEquals(repositoryFrom.getItemsByIndex(i), repositoryTo.getItemsByIndex(i));
        }
    }
}