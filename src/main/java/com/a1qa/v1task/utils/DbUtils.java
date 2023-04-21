package com.a1qa.v1task.utils;

import com.a1qa.v1task.models.CredentialsData;

import java.sql.*;
import java.util.ArrayList;

public class DbUtils {
    private CredentialsData credentialsData = JsonHelper.getJsonData(JsonHelper.getValueFromJson("pathToCredentials"), CredentialsData.class);

    public ArrayList<ArrayList<String>> getDataFromDatabase(String query) {
//        String connectionString = JsonHelper.getTestsData().getHost() + JsonHelper.getTestsData().getDbName();
        String connectionString = "localhost:8080/web";
        ArrayList<ArrayList<String>> data = new ArrayList<ArrayList<String>>();
        try {
            Connection conn = DriverManager.getConnection(connectionString, credentialsData.getLoginDb(), credentialsData.getPasswordDb());
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
            int numColumns = resultSetMetaData.getColumnCount();
            while (resultSet.next()) {
                ArrayList<String> row = new ArrayList<String>();
                for (int i = 1; i <= numColumns; i++) {
                    row.add(resultSet.getString(i));
                }
                data.add(row);
            }
            resultSet.close();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return data;
    }

}