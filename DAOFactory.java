package com.ex.dao;

import com.ex.dao.interfaces.UserDAO;

public abstract class DAOFactory {
    // List of DAO types supported by the factory
    public static final int POSTGRESQL = 1;
    public static final int ORACLE = 2;

    // There will be a method for each DAO that can be 
    // created. The concrete factories will have to 
    // implement these methods.
    public abstract UserDAO getUserDAO();

    public static DAOFactory getDAOFactory(int intWhichFactory) {
        switch (intWhichFactory) 
        {
            case POSTGRESQL : 
                return new PostgreSQLDAOFactory();
            case ORACLE : 
                return new OracleDAOFactory();
            default : 
                return null;
        }
    }
}