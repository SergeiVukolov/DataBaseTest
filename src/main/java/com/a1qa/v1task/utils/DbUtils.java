package com.a1qa.v1task.utils;

import com.a1qa.v1task.models.CredentialsData;
import com.a1qa.v1task.models.TestsData;

import java.lang.reflect.InvocationTargetException;
import java.sql.*;
import java.util.LinkedList;

public class DbUtils {
    private CredentialsData credentialsData = JsonHelper.getJsonData(JsonHelper.getValueFromJson("pathToCredentials"), CredentialsData.class);
    private TestsData testsData = JsonHelper.getJsonData(JsonHelper.getValueFromJson("pathToTestsData"), TestsData.class);

    public Connection getConnection() {
        Connection conn = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
            conn = DriverManager.getConnection(String.format("jdbc:mysql://localhost:%s/%s", testsData.getHostDb(), testsData.getNameDataBase()),
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

    public int sendTestInfo(String query) {
        try (PreparedStatement ps = getConnection().prepareStatement(query)) {
            return ps.executeUpdate(query);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}