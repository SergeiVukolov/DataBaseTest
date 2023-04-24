package com.a1qa.v1task.models;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TestsData {
    String url;
    String apiUrl;
    Integer version;
    String textProjects;
    String projectName;
    String success;
    String methodToken;
    String contentType;
    String hostWeb;
    String hostDb;
    String datePattern;
    String nameDataBase;
    String wordAlert;

}
