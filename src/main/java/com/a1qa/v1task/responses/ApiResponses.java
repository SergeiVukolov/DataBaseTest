package com.a1qa.v1task.responses;

import com.a1qa.v1task.models.TestsData;
import com.a1qa.v1task.requests.ApiRequests;
import com.a1qa.v1task.utils.JsonHelper;

public class ApiResponses {
    private static TestsData testsData = JsonHelper.getJsonData(JsonHelper.getValueFromJson("pathToTestsData"), TestsData.class);

    public static String getTokenResponse() {
        String url = String.format(testsData.getApiUrl(), testsData.getHostWeb()) + testsData.getMethodToken();
        return ApiRequests.getToken(url, testsData.getVersion());
    }

}
