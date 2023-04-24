package com.a1qa.v1task.steps;

import com.a1qa.v1task.utils.DbUtils;
import java.sql.Blob;
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
        String query = String.format("INSERT INTO union_reporting.test (id, name, status_id, method_name, project_id, session_id, env)\n" +
                "VALUES (%s, '%s', 1, '%s', %s, %s, '%s');", testId, name, method_name, projectId, sessionId, env);
        return dbUtils.sendTestInfo(query);
    }

    public int sendLogToDataBase(long id, String logContent, long testId) {
        String query = String.format("INSERT INTO union_reporting.log (id, content, is_exception, test_id)\n" +
                "VALUES (%s, '%s', 0, %s);", id, logContent, testId);
        return dbUtils.sendTestInfo(query);
    }

    public int sendAttachmentToDataBase(long id, Blob content, String contentType, long testId) {
        String query = String.format("INSERT INTO union_reporting.attachment (id, content, content_type, test_id)\n" +
                "VALUES (%s, '%s', '%s', %s);", id, content, contentType, testId);
        return dbUtils.sendTestInfo(query);
    }

}


