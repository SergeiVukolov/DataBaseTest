package com.a1qa.v1task.utils;

import java.net.URI;
import java.net.URISyntaxException;

public class ProjectId {
    public int getId(String urlString) {
        int id = 0;
        URI uri = null;
        try {
            uri = new URI(urlString);
        } catch (
                URISyntaxException e) {
            throw new RuntimeException(e);
        }
        String query = uri.getQuery();
        String[] params = query.split("&");
        for (String param : params) {
            String[] keyValuePair = param.split("=");
            String key = keyValuePair[0];
            String value = keyValuePair[1];
            if (key.equals("projectId")) {
                id = Integer.parseInt(value);
            }
        }
        return id;
    }

}