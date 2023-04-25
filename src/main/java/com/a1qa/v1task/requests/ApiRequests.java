package com.a1qa.v1task.requests;

import com.a1qa.v1task.models.FieldsData;
import com.a1qa.v1task.utils.ApiUtils;
import com.a1qa.v1task.utils.JsonHelper;
import com.mashape.unirest.http.exceptions.UnirestException;

public class ApiRequests {
    private static FieldsData fieldsData = JsonHelper.getJsonData(JsonHelper.getValueFromJson("pathToFieldsData"), FieldsData.class);

    public static String getToken(String url, int variant) {
        try {
            return ApiUtils.postRequest(url)
                    .field(fieldsData.getFieldVariant(), variant)
                    .asString().getBody();
        } catch (UnirestException e) {
            throw new RuntimeException(e);
        }
    }

}
