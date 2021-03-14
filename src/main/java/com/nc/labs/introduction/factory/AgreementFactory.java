package com.nc.labs.introduction.factory;

import com.nc.labs.introduction.agreements.Agreement;
import com.nc.labs.introduction.agreements.digitaltv.AgreementOfDigitalTv;
import com.nc.labs.introduction.agreements.digitaltv.Channel;
import com.nc.labs.introduction.agreements.mobileconnection.AgreementOfMobileConnection;
import com.nc.labs.introduction.agreements.wiredinternet.AgreementOfWiredInternet;
import com.nc.labs.introduction.agreements.wiredinternet.Speed;
import com.nc.labs.introduction.agreements.wiredinternet.TypeOfSpeed;
import com.nc.labs.introduction.people.Gender;
import com.nc.labs.introduction.people.Person;
import com.nc.labs.introduction.repositories.IRepository;
import com.opencsv.*;

import java.io.File;
import java.io.FileReader;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class AgreementFactory {
    private static List<Person> people = new ArrayList<>();

    /**
     * to create new repository with agreements, created from file
     * @param repository of agreement
     * @param file of data with agreements
     * @return new repository with agreements, created from file
     * @throws Exception
     */
    public static IRepository create(IRepository repository, File file) throws Exception {
        CSVReaderHeaderAwareBuilder csvReaderHeaderAwareBuilder = new CSVReaderHeaderAwareBuilder
                (new FileReader(file, StandardCharsets.UTF_8));
        csvReaderHeaderAwareBuilder.withCSVParser(new CSVParserBuilder().withSeparator(';').build());
        CSVReaderHeaderAware csvReaderHeaderAware = csvReaderHeaderAwareBuilder.build();
        Map<String, String> stringMap;
        while ((stringMap = csvReaderHeaderAware.readMap()) != null) {
            CSVParser csvParser = new CSVParserBuilder().withSeparator(',').build();
            String[] addInfo = csvParser.parseLine(stringMap.get("ДопИнформацияПоКонтракту"));
            Agreement agreement = null;
            switch (stringMap.get("ТипКонтракта")) {
                case "AgreementOfDigitalTv":
                    List<Channel> list = new ArrayList<>();
                    list.add(new Channel(addInfo[0]));
                    AgreementOfDigitalTv agreementOfDigitalTv = new AgreementOfDigitalTv();
                    agreementOfDigitalTv.setPackageOfChannel(list);
                    agreement = agreementOfDigitalTv;
                    break;
                case "AgreementOfMobileConnection":
                    AgreementOfMobileConnection agreementOfMobileConnection = new AgreementOfMobileConnection();
                    agreementOfMobileConnection.setCountOfMinutes(Integer.parseInt(addInfo[0]));
                    agreementOfMobileConnection.setCountOfSms(Integer.parseInt(addInfo[1]));
                    agreementOfMobileConnection.setCountOfGbTraffic(Integer.parseInt(addInfo[2]));
                    agreement = agreementOfMobileConnection;
                    break;
                case "AgreementOfWiredInternet":
                    AgreementOfWiredInternet agreementOfWiredInternet = new AgreementOfWiredInternet();
                    agreementOfWiredInternet.setConnectionSpeed(new Speed(TypeOfSpeed.valueOf(addInfo[0]), Double.parseDouble(addInfo[1])));
                    agreement = agreementOfWiredInternet;
                    break;
            }
            agreement.setBeginning(LocalDate.parse(stringMap.get("ДатаНачалаКонтракта")));
            agreement.setEnd(LocalDate.parse(stringMap.get("ДатаКонцаКонтракта")));
            agreement.setNumber(Integer.parseInt(stringMap.get("НомерКонтракта")));

            agreement.setOwner(getPerson(new Person(stringMap.get("Фамилия"), stringMap.get("Имя"), stringMap.get("Отчество"),
                    LocalDate.parse(stringMap.get("ДатаРождения")), Gender.valueOf(stringMap.get("ПолКлиента")), Integer.parseInt(stringMap.get("СерияПаспорта")),
                    Integer.parseInt(stringMap.get("НомерПаспорта")))));
            repository.add(agreement);
        }
        return repository;
    }

    /**
     * to return existing Person and to delete created Person or to to return created Person
     * @param tempPerson created Person
     * @return existing Person
     */
    private static Person getPerson(Person tempPerson) {
        int index = checkSimilarPerson(tempPerson.getPassport().getSeriesOfPassport(), tempPerson.getPassport().getNumberOfPassport());
        if (index == -1) {
            people.add(tempPerson);
            return tempPerson;
        }
        tempPerson = null;
        return people.get(index);
    }

    /**
     * checks the existence of an object with unique data
     * @param series Of Person's Passport
     * @param number Of Person's Passport
     * @return -1 if there isn't Person with agreement else return Person's index
     */
    private static int checkSimilarPerson(int series, int number) {
        int i = 0;
        if (people.size() != 0) {
            while (i < people.size()) {
                if ((number == people.get(i).getPassport().getNumberOfPassport()) &&
                        series == people.get(i).getPassport().getSeriesOfPassport()) {
                    return i;
                }
                i++;
            }
        }
        return -1;
    }
}
