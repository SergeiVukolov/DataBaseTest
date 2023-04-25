package com.a1qa.v1task.utils;

import com.a1qa.v1task.models.CredentialsData;
import com.a1qa.v1task.models.HostsData;
import com.a1qa.v1task.models.TestsData;

import java.lang.reflect.InvocationTargetException;
import java.sql.*;
import java.util.LinkedList;

public class DbUtils {
    private CredentialsData credentialsData = JsonHelper.getJsonData(JsonHelper.getValueFromJson("pathToCredentials"), CredentialsData.class);
    private TestsData testsData = JsonHelper.getJsonData(JsonHelper.getValueFromJson("pathToTestsData"), TestsData.class);
    private HostsData hostsData = JsonHelper.getJsonData(JsonHelper.getValueFromJson("pathToHosts"), HostsData.class);

    public Connection getConnection() {
        Connection conn = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
            conn = DriverManager.getConnection(String.format(testsData.getDbLink(), hostsData.getHostDb(), testsData.getNameDataBase()),
                    credentialsData.getLoginDb(), credentialsData.getPasswordDb());
        } catch (SQLException | ClassNotFoundException | InstantiationException | IllegalAccessException |
                 NoSuchMethodException | InvocationTargetException e) {
            e.printStackTrace();
        }
        return conn;
    }

    public LinkedList<LinkedList<String>> getDataFromDatabase(String query) {
        LinkedList<LinkedList<String>> data = new LinkedList<>();
        try {
            Statement statement = getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
            int numColumns = resultSetMetaData.getColumnCount();
            while (resultSet.next()) {
                LinkedList<String> row = new LinkedList<>();
                for (int i = 1; i <= numColumns; i++) {
                    row.add(resultSet.getString(i));
                }
                data.add(row);
            }
            resultSet.close();
            statement.close();
            getConnection().close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return data;
    }

}