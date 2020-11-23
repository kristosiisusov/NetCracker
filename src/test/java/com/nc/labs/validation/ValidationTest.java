package com.nc.labs.validation;


import com.nc.labs.agreements.Agreement;
import com.nc.labs.agreements.digitaltv.AgreementOfDigitalTv;
import com.nc.labs.agreements.digitaltv.Channel;
import com.nc.labs.factory.AgreementFactory;
import com.nc.labs.people.Gender;
import com.nc.labs.people.Person;
import com.nc.labs.repositories.IRepository;
import com.nc.labs.repositories.RepositoryList;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


class ValidationTest {
    @Test
    void validate() throws Exception {
        LocalDate birthday = LocalDate.of(2018, 12, 27);
        Person person = new Person("Watson", "Emma", "Charlotte",
                birthday, Gender.FEMALE, 2002, 123456);
        List<Channel> listOfChannel = new ArrayList<>();
        listOfChannel.add(new Channel("TestFirstChannel"));
        Agreement agreementOfDigitalTv  = new AgreementOfDigitalTv(LocalDate.of(2018,12, 27),
                LocalDate.of(2021, 12, 27), 880055538, person, listOfChannel);
        ValidationAgreementMessagesInfo<Agreement> validationAgreementMessagesInfo = new ValidationAgreementMessagesInfo<>();
        List<Condition<Agreement>> conditions = new ArrayList<>();
        List<String> criterion = new ArrayList<>();
        criterion.add("880055500");
        conditions.add(new Condition<>(criterion, (x) -> x.getNumber() < 880055539,"number"));
        validationAgreementMessagesInfo.lessThan(conditions);
        criterion.clear();
        criterion.add("FEMALE");
        conditions.clear();
        conditions.add(new Condition<>(criterion, (x) -> x.getOwner().getGender() == Gender.FEMALE,"Gender"));
        validationAgreementMessagesInfo.equal(conditions);
        IValidator<Agreement> validator = validationAgreementMessagesInfo.createValidator();
        AgreementFactory agreementFactory = new AgreementFactory(new RepositoryList<>(), new File("src/test/java/resources/Data.csv"), validator );
        IRepository<Agreement> repositoryFromFile = agreementFactory.create();
        IRepository<Agreement> validatedRepositoryFromFile = new RepositoryList<>();
        validatedRepositoryFromFile.add(agreementOfDigitalTv);
        for (int i = 0; i < repositoryFromFile.length(); i++) {
            assertEquals(repositoryFromFile.getItemsByIndex(i),validatedRepositoryFromFile.getItemsByIndex(i));
        }
    }
}
