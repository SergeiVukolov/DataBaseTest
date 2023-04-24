package com.a1qa.v1task.utils;

import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.request.HttpRequestWithBody;

public class ApiUtils {

    public static HttpRequestWithBody postRequest(String url) {
        return Unirest.post(url);
    }

}
