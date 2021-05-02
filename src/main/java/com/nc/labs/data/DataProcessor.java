package com.nc.labs.data;

import com.nc.labs.introduction.agreements.Agreement;
import com.nc.labs.introduction.repositories.RepositoryList;

import java.sql.SQLException;

public interface DataProcessor {
    void storeTo(RepositoryList<Agreement> repositoryList) throws SQLException;

    RepositoryList<Agreement> restoreFrom() throws SQLException;
}
