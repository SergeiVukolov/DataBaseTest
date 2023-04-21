package com.a1qa.v1task.models;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ConfigData {
    String pathToTestsData;
    String pathToTests;
    String pathToRequests;
    String pathToConfig;
    String pathToLogFile;

}
