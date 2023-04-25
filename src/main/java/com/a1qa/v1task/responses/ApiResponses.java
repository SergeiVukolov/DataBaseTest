package com.a1qa.v1task.responses;

import com.a1qa.v1task.models.HostsData;
import com.a1qa.v1task.models.TestsData;
import com.a1qa.v1task.requests.ApiRequests;
import com.a1qa.v1task.utils.JsonHelper;

public class ApiResponses {
    private static TestsData testsData = JsonHelper.getJsonData(JsonHelper.getValueFromJson("pathToTestsData"), TestsData.class);
    private static HostsData hostsData = JsonHelper.getJsonData(JsonHelper.getValueFromJson("pathToHosts"), HostsData.class);

    public static String getTokenResponse() {
        String url = String.format(testsData.getApiUrl(), hostsData.getHostWeb()) + testsData.getMethodToken();
        return ApiRequests.getToken(url, testsData.getVersion());
    }

}
