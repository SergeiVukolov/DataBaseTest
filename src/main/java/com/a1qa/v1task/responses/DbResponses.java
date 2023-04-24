package com.a1qa.v1task.responses;

import com.a1qa.v1task.models.CompanyTests;
import com.a1qa.v1task.steps.DbSteps;
import com.a1qa.v1task.utils.Enum;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class DbResponses {
    DbSteps dbSteps = new DbSteps();

    public List<CompanyTests> transformData(String projectName, int limit) {
        LinkedList<LinkedList<String>> data = dbSteps.getArrayListWithTests(projectName, limit);
        List<CompanyTests> transformedData = new ArrayList<>();
        for (LinkedList<String> row : data) {
            CompanyTests test = CompanyTests.builder()
                    .name(row.get(Enum.NumberEnum.ZERO.getValue()))
                    .method(row.get(Enum.NumberEnum.ONE.getValue()))
                    .status(row.get(Enum.NumberEnum.TWO.getValue()))
                    .startTime(row.get(Enum.NumberEnum.THREE.getValue()))
                    .endTime(row.get(Enum.NumberEnum.FOUR.getValue()))
                    .duration(row.get(Enum.NumberEnum.FIVE.getValue()))
                    .build();
            transformedData.add(test);
        }
        return transformedData;
    }

}

