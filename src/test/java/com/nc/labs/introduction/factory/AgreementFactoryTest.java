package com.nc.labs.introduction.factory;

import com.nc.labs.introduction.agreements.Agreement;
import com.nc.labs.introduction.agreements.digitaltv.AgreementOfDigitalTv;
import com.nc.labs.introduction.agreements.digitaltv.Channel;
import com.nc.labs.introduction.agreements.mobileconnection.AgreementOfMobileConnection;
import com.nc.labs.introduction.agreements.wiredinternet.AgreementOfWiredInternet;
import com.nc.labs.introduction.agreements.wiredinternet.TypeOfSpeed;
import com.nc.labs.introduction.factory.AgreementFactory;
import com.nc.labs.introduction.people.Gender;
import com.nc.labs.introduction.people.Person;
import com.nc.labs.introduction.repositories.IRepository;
import com.nc.labs.introduction.repositories.RepositoryList;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.io.File;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


class AgreementFactoryTest {
    @Test
    void testCreate() throws Exception {
        LocalDate birthday = LocalDate.of(2018, 12, 27);
        Person person = new Person("Watson", "Emma", "Charlotte",
                birthday, Gender.FEMALE, 2002, 123456);
        List listOfChannel = new ArrayList();
        listOfChannel.add(new Channel("TestFirstChannel"));
        Agreement agreementOfDigitalTv  = new AgreementOfDigitalTv(LocalDate.of(2018,12, 27),
                LocalDate.of(2021, 12, 27), 880055538, person, listOfChannel);
        Agreement agreementOfMobileConnection  = new AgreementOfMobileConnection(LocalDate.of(2018,12, 27),
                LocalDate.of(2021, 12, 27), 880055539,
                person, 60, 1000, 2);
        Agreement agreementOfWiredInternet = new AgreementOfWiredInternet(LocalDate.of(2018,12, 27),
                LocalDate.of(2021, 12, 27), 880055539,
                new Person("Watson", "Emma", "Charlotte",
                        birthday, Gender.FEMALE, 2004, 789567), TypeOfSpeed.MBIT, 50.0);
        IRepository<Agreement> repositoryObj = new RepositoryList<>();
        repositoryObj.add(agreementOfDigitalTv);
        repositoryObj.add(agreementOfMobileConnection);
        repositoryObj.add(agreementOfWiredInternet);
        IRepository<Agreement> repositoryFromFile;
        repositoryFromFile = AgreementFactory.create(new RepositoryList<>(), new File("src/test/java/resources/Data.csv"));
        for (int i = 0; i < repositoryFromFile.length(); i++) {
            assertEquals(repositoryFromFile.getItemsByIndex(i),repositoryObj.getItemsByIndex(i));
        }
    }
}
