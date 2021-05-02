package com.nc.labs.data;

import com.nc.labs.introduction.agreements.Agreement;
import com.nc.labs.introduction.repositories.RepositoryList;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;

public class JaxbProcessor implements DataProcessor {

    private final String pathFrom;
    private final String pathTo;

    public JaxbProcessor(String pathFrom, String pathTo) {
        this.pathFrom = pathFrom;
        this.pathTo = pathTo;
    }

    public RepositoryList<Agreement> restoreFrom() {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(RepositoryList.class);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            RepositoryList<Agreement> repositoryListTemp = (RepositoryList) unmarshaller.unmarshal(new File(pathFrom));
            Agreement[] agreements = repositoryListTemp.getArray();
            RepositoryList<Agreement> repositoryList = new RepositoryList<>(agreements.length);
            for (Agreement agreement : agreements) {
                repositoryList.add(agreement);
            }
            return repositoryList;

        } catch (JAXBException exp) {
            exp.printStackTrace();
        }
        return null;
    }

    public void storeTo(RepositoryList<Agreement> repositoryList) {
        try {
            JAXBContext context = JAXBContext.newInstance(RepositoryList.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            marshaller.marshal(repositoryList, new File(pathTo));
        } catch (JAXBException exp) {
            exp.printStackTrace();
        }
    }
}
