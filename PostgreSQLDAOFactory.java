package com.ex.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.ex.dao.exceptions.DAOConfigurationException;
import com.ex.dao.impl.PostgreSQLUserDAOImpl;
import com.ex.dao.interfaces.UserDAO;

public class PostgreSQLDAOFactory extends DAOFactory {
    @Override
    public UserDAO getUserDAO() {
        // PostgreSQLUserDAOImpl implements UserDAO
        return new PostgreSQLUserDAOImpl();
    }

    // method to create POSTGRESQL DB connections
    public static Connection createConnection() {
        // Use DRIVER and DBURL to create a connection
        // Recommend connection pool implementation/usage
        Connection connection = null;
        String strDatasourceName="jdbc/sampleDS";
        try
        {
            Context initContext = new InitialContext();
            Context envContext = (Context) initContext.lookup("java:/comp/env");
            DataSource dataSource = (DataSource) envContext.lookup(strDatasourceName);
            connection = dataSource.getConnection();
        }
        catch(SQLException sqle){
            throw new DAOConfigurationException("Error while creating connection to the database server", sqle);
        }
        catch(NamingException ne){
            throw new DAOConfigurationException("DataSource '" + strDatasourceName + "' is missing in JNDI", ne);
        }

        return connection;
    }

    public static Collection<List<Object>> getData(String strQuery) {
        List<List<Object>> aLstQryResultData = new ArrayList<List<Object>>();
        Connection conn = null;
        PreparedStatement pStmt = null;
        ResultSet resultSet = null;
        try {
            conn = createConnection();
            pStmt = conn.prepareStatement(strQuery);
            resultSet = pStmt.executeQuery();

            if (resultSet != null) {
                while (resultSet.next()) {
                    List<Object> lstRowData = new ArrayList<Object>();
                    for (int i = 1; i <= resultSet.getMetaData().getColumnCount(); i++) {
                        lstRowData.add(resultSet.getObject(i));
                    }
                    aLstQryResultData.add(lstRowData);
                }
            }
        }
        catch (Exception e) {
            // Handle the error
        }
        finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
            } catch (Exception ex) {}

            try {
                if (pStmt != null) {
                    pStmt.close();
                }
            } catch (Exception ex) {}

            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception ex) {}
        }

        return aLstQryResultData;
    }

    public static Collection<List<Object>> getData(String strQuery, Object[] objArrOfQryParams) {
        List<List<Object>> aLstQryResultData = new ArrayList<List<Object>>();
        Connection conn = null;
        PreparedStatement pStmt = null;
        ResultSet resultSet = null;
        try {
            conn = createConnection();
            pStmt = conn.prepareStatement(strQuery);
            for (int i = 0; i < objArrOfQryParams.length; i++) {
                pStmt.setObject(i + 1, objArrOfQryParams[i]);
            }
            resultSet = pStmt.executeQuery();

            if (resultSet != null) {
                while (resultSet.next()) {
                    List<Object> lstRowData = new ArrayList<Object>();
                    for (int i = 1; i <= resultSet.getMetaData().getColumnCount(); i++) {
                        lstRowData.add(resultSet.getObject(i));
                    }
                    aLstQryResultData.add(lstRowData);
                }
            }
        }
        catch (Exception e) {
            // Handle the error
        }
        finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
            } catch (Exception ex) {}

            try {
                if (pStmt != null) {
                    pStmt.close();
                }
            } catch (Exception ex) {}

            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception ex) {}
        }

        return aLstQryResultData;
    }   
}