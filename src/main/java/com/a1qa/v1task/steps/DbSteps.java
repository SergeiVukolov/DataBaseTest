package com.a1qa.v1task.steps;

import com.a1qa.v1task.utils.DbUtils;
import com.a1qa.v1task.utils.Enum;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.LinkedList;

public class DbSteps {
    DbUtils dbUtils = new DbUtils();

    public LinkedList<LinkedList<String>> getArrayListWithTests(String projectName, int limit) {
        String query = String.format("SELECT test.name, test.method_name, status.name, test.start_time, test.end_time, \n" +
                "DATE_FORMAT(TIMEDIFF(test.end_time, test.start_time), '%H:%i:%s:000') as duration\n" +
                "FROM union_reporting.test\n" +
                "JOIN union_reporting.status ON test.status_id = status.id\n" +
                "JOIN union_reporting.project ON test.project_id = project.id\n" +
                "WHERE project.name = '%s'\n" +
                "GROUP BY test.name, test.method_name, status.name, test.start_time, test.end_time\n" +
                "ORDER BY test.start_time LIMIT %s;", projectName, limit);
        LinkedList<LinkedList<String>> data = dbUtils.getDataFromDatabase(query);
        return data;
    }

    public int sendTestToDataBase(long testId, String name, String method_name, int projectId, int sessionId, String env) {
        String query = "INSERT INTO union_reporting.test (id, name, status_id, method_name, project_id, session_id, env) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = dbUtils.getConnection().prepareStatement(query)) {
            stmt.setLong(Enum.NumberEnum.ONE.getValue(), testId);
            stmt.setString(Enum.NumberEnum.TWO.getValue(), name);
            stmt.setInt(Enum.NumberEnum.THREE.getValue(), Enum.NumberEnum.ONE.getValue());
            stmt.setString(Enum.NumberEnum.FOUR.getValue(), method_name);
            stmt.setInt(Enum.NumberEnum.FIVE.getValue(), projectId);
            stmt.setInt(Enum.NumberEnum.SIX.getValue(), sessionId);
            stmt.setString(Enum.NumberEnum.SEVEN.getValue(), env);
            return stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int sendLogToDataBase(long id, String logContent, long testId) {
        String query = "INSERT INTO union_reporting.log (id, content, is_exception, test_id) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = dbUtils.getConnection().prepareStatement(query)) {
            stmt.setLong(Enum.NumberEnum.ONE.getValue(), id);
            stmt.setString(Enum.NumberEnum.TWO.getValue(), logContent);
            stmt.setInt(Enum.NumberEnum.THREE.getValue(), Enum.NumberEnum.ZERO.getValue());
            stmt.setLong(Enum.NumberEnum.FOUR.getValue(), testId);
            return stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int sendAttachmentToDataBase(long id, byte[] content, String contentType, long testId) {
        String query = "INSERT INTO union_reporting.attachment (id, content, content_type, test_id) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = dbUtils.getConnection().prepareStatement(query)) {
            stmt.setLong(Enum.NumberEnum.ONE.getValue(), id);
            stmt.setBytes(Enum.NumberEnum.TWO.getValue(), content);
            stmt.setString(Enum.NumberEnum.THREE.getValue(), contentType);
            stmt.setLong(Enum.NumberEnum.FOUR.getValue(), testId);
            return stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}


