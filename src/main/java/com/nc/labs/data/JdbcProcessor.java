package com.nc.labs.data;

import com.nc.labs.introduction.Passport;
import com.nc.labs.introduction.agreements.Agreement;
import com.nc.labs.introduction.agreements.digitaltv.AgreementOfDigitalTv;
import com.nc.labs.introduction.agreements.digitaltv.Channel;
import com.nc.labs.introduction.agreements.mobileconnection.AgreementOfMobileConnection;
import com.nc.labs.introduction.agreements.wiredinternet.AgreementOfWiredInternet;
import com.nc.labs.introduction.agreements.wiredinternet.Speed;
import com.nc.labs.introduction.agreements.wiredinternet.TypeOfSpeed;
import com.nc.labs.introduction.people.Gender;
import com.nc.labs.introduction.people.Person;
import com.nc.labs.introduction.repositories.RepositoryList;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.UUID;

public class JdbcProcessor implements DataProcessor {
    private final String url;
    private final String user;
    private final String pass;

    public JdbcProcessor() throws ClassNotFoundException, IOException {
        FileInputStream fileInputStream = new FileInputStream("src/main/resources/DBConnection.properties");
        Properties properties = new Properties();
        properties.load(fileInputStream);
        this.url = properties.getProperty("Database.DataURL");
        this.user = properties.getProperty("Database.User");
        this.pass = properties.getProperty("Database.Password");
        Class.forName("org.postgresql.Driver");
    }

    public void storeTo(RepositoryList<Agreement> repositoryList) throws SQLException {
        List<Person> persons = findUniqPerson(repositoryList);
        try (Connection connection = DriverManager.getConnection(url, user, pass)) {
            String insertPersons = "INSERT INTO person (id, lastName, firstName, middleName, birthdate, gender, " +
                    "seriesOfPassport, numberOfPassport) VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(insertPersons)) {
                for (int i = 0; i < persons.size(); i++) {
                    Person person = persons.get(i);
                    preparedStatement.setObject(1, person.getId());
                    preparedStatement.setString(2, person.getLastName());
                    preparedStatement.setString(3, person.getFirstName());
                    preparedStatement.setString(4, person.getMiddleName());
                    preparedStatement.setObject(5, person.getBirthdate());
                    preparedStatement.setObject(6, person.getGender(), Types.OTHER);
                    preparedStatement.setInt(7, person.getPassport().getSeriesOfPassport());
                    preparedStatement.setInt(8, person.getPassport().getNumberOfPassport());
                    preparedStatement.addBatch();
                }
                preparedStatement.executeBatch();
            }

            String insertAgreements = "INSERT INTO agreement (id, beginning, \"end\", number, personId, type, countOfMinutes, " +
                    "countOfSms, countOfGbTraffic, channel, typeOfSpeed, value)" +
                    " VALUES(?, ?, ?, ?, ?, ?, ?, ?, ? , ?, ?, ?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(insertAgreements)) {
                for (int i = 0; i < repositoryList.length(); i++) {
                    Agreement agreement = repositoryList.getItemsByIndex(i);
                    preparedStatement.setObject(1, agreement.getId());
                    preparedStatement.setObject(2, agreement.getBeginning());
                    preparedStatement.setObject(3, agreement.getEnd());
                    preparedStatement.setInt(4, agreement.getNumber());
                    String name = agreement.getClass().getSimpleName();
                    switch (name) {
                        case "AgreementOfWiredInternet":
                            AgreementOfWiredInternet agreementOfWiredInternet = (AgreementOfWiredInternet) agreement;
                            preparedStatement.setObject(5, agreementOfWiredInternet.getOwner().getId());
                            preparedStatement.setString(6, name);
                            preparedStatement.setNull(7, 0);
                            preparedStatement.setNull(8, 0);
                            preparedStatement.setNull(9, 0);
                            preparedStatement.setNull(10, 0);
                            preparedStatement.setObject(11, agreementOfWiredInternet.getConnectionSpeed().getTypeOfSpeed(), Types.OTHER);
                            preparedStatement.setDouble(12, agreementOfWiredInternet.getConnectionSpeed().getValue());
                            preparedStatement.addBatch();
                            break;
                        case "AgreementOfDigitalTv":
                            AgreementOfDigitalTv agreementOfDigitalTv = (AgreementOfDigitalTv) agreement;
                            List<Channel> channelList = agreementOfDigitalTv.getPackageOfChannel();
                            String[] channels = new String[channelList.size()];
                            for (int j = 0; j < channelList.size(); j++) {
                                channels[j] = channelList.get(j).getName();
                            }
                            Array array = connection.createArrayOf("VARCHAR", channels);
                            preparedStatement.setObject(5, agreementOfDigitalTv.getOwner().getId());
                            preparedStatement.setString(6, name);
                            preparedStatement.setNull(7, 0);
                            preparedStatement.setNull(8, 0);
                            preparedStatement.setNull(9, 0);
                            preparedStatement.setArray(10, array);
                            preparedStatement.setNull(11, 0);
                            preparedStatement.setNull(12, 0);
                            preparedStatement.addBatch();
                            break;
                        case "AgreementOfMobileConnection":
                            AgreementOfMobileConnection agreementOfMobileConnection = (AgreementOfMobileConnection) agreement;
                            preparedStatement.setObject(5, agreementOfMobileConnection.getOwner().getId());
                            preparedStatement.setString(6, name);
                            preparedStatement.setInt(7, agreementOfMobileConnection.getCountOfMinutes());
                            preparedStatement.setInt(8, agreementOfMobileConnection.getCountOfSms());
                            preparedStatement.setInt(9, agreementOfMobileConnection.getCountOfGbTraffic());
                            preparedStatement.setNull(10, 0);
                            preparedStatement.setNull(11, 0);
                            preparedStatement.setNull(12, 0);
                            preparedStatement.addBatch();
                            break;
                    }
                }
                preparedStatement.executeBatch();
            }

        }
    }

    private List<Person> findUniqPerson(RepositoryList<Agreement> repositoryList) {
        List<Person> persons = new ArrayList<>();
        for (int i = 0; i < repositoryList.length(); i++) {
            persons.add(repositoryList.getItemsByIndex(i).getOwner());
        }
        for (int i = 0; i < persons.size(); i++) {
            for (int j = 0; j < persons.size(); j++) {
                if (i != j && persons.get(i) == persons.get(j)) {
                    persons.remove(i);
                }
            }

        }
        return persons;
    }

    public RepositoryList<Agreement> restoreFrom() throws SQLException {
        ResultSet resultSet = null;
        List<Person> persons = new ArrayList<>();
        String selectPersons = "SELECT * FROM person";
        try (Connection connection = DriverManager.getConnection(url, user, pass)) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(selectPersons)) {
                resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    Person person = new Person();
                    person.setId(resultSet.getObject(1, UUID.class));
                    person.setLastName(resultSet.getString(2));
                    person.setFirstName(resultSet.getString(3));
                    person.setMiddleName(resultSet.getString(4));
                    person.setBirthdate(resultSet.getObject(5, LocalDate.class));
                    person.setGender(Gender.valueOf(resultSet.getString(6)));
                    Passport passport = new Passport();
                    passport.setSeriesOfPassport(resultSet.getInt(7));
                    passport.setNumberOfPassport(resultSet.getInt(8));
                    person.setPassport(passport);
                    person.setAge(person.getBirthdate());
                    persons.add(person);
                }
            }

        }
        String selectAgreements = "SELECT * FROM agreement";
        RepositoryList<Agreement> repositoryList = new RepositoryList<>();
        try (Connection connection = DriverManager.getConnection(url, user, pass)) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(selectAgreements)) {
                resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    String name = resultSet.getString(6);
                    switch (name) {
                        case "AgreementOfWiredInternet":
                            AgreementOfWiredInternet agreementOfWiredInternet = new AgreementOfWiredInternet();
                            agreementOfWiredInternet.setId(resultSet.getObject(1, UUID.class));
                            agreementOfWiredInternet.setBeginning(resultSet.getObject(2, LocalDate.class));
                            agreementOfWiredInternet.setEnd(resultSet.getObject(3, LocalDate.class));
                            agreementOfWiredInternet.setNumber(resultSet.getInt(4));
                            agreementOfWiredInternet.setOwner(findPersonById(persons, resultSet.getObject(5, UUID.class)));
                            agreementOfWiredInternet.setConnectionSpeed(new Speed(TypeOfSpeed.valueOf(resultSet.getString(11)), resultSet.getDouble(12)));
                            repositoryList.add(agreementOfWiredInternet);
                            break;
                        case "AgreementOfDigitalTv":
                            AgreementOfDigitalTv agreementOfDigitalTv = new AgreementOfDigitalTv();
                            agreementOfDigitalTv.setId(resultSet.getObject(1, UUID.class));
                            agreementOfDigitalTv.setBeginning(resultSet.getObject(2, LocalDate.class));
                            agreementOfDigitalTv.setEnd(resultSet.getObject(3, LocalDate.class));
                            agreementOfDigitalTv.setNumber(resultSet.getInt(4));
                            agreementOfDigitalTv.setOwner(findPersonById(persons, resultSet.getObject(5, UUID.class)));
                            String[] channels = (String[]) resultSet.getArray(10).getArray();
                            List<Channel> channelList = new ArrayList<>();
                            for (String channel : channels) {
                                channelList.add(new Channel(channel));
                            }
                            agreementOfDigitalTv.setPackageOfChannel(channelList);
                            repositoryList.add(agreementOfDigitalTv);
                            break;
                        case "AgreementOfMobileConnection":
                            AgreementOfMobileConnection agreementOfMobileConnection = new AgreementOfMobileConnection();
                            agreementOfMobileConnection.setId(resultSet.getObject(1, UUID.class));
                            agreementOfMobileConnection.setBeginning(resultSet.getObject(2, LocalDate.class));
                            agreementOfMobileConnection.setEnd(resultSet.getObject(3, LocalDate.class));
                            agreementOfMobileConnection.setNumber(resultSet.getInt(4));
                            agreementOfMobileConnection.setOwner(findPersonById(persons, resultSet.getObject(5, UUID.class)));
                            agreementOfMobileConnection.setCountOfMinutes(resultSet.getInt(7));
                            agreementOfMobileConnection.setCountOfSms(resultSet.getInt(8));
                            agreementOfMobileConnection.setCountOfGbTraffic(resultSet.getInt(9));
                            repositoryList.add(agreementOfMobileConnection);
                            break;
                    }
                }
            }

        }
        return repositoryList;
    }

    private Person findPersonById(List<Person> persons, UUID id) {
        return persons.stream().filter(x -> x.getId().equals(id)).findAny().get();
    }

    public void cleanDB(String name) throws SQLException {
        try (Connection connection = DriverManager.getConnection(url, user, pass)) {
            try (Statement statement = connection.createStatement()) {
                statement.executeUpdate("TRUNCATE " + name + " CASCADE");
            }
        }
    }
}
