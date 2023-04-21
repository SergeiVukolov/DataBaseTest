package com.a1qa.v1task.steps;

import com.a1qa.v1task.utils.DbUtils;
import java.util.ArrayList;

public class DbSteps {
    DbUtils dbUtils = new DbUtils();

    public void getResult(String query) {
        ArrayList<ArrayList<String>> data = dbUtils.getDataFromDatabase(query);
    }

}


