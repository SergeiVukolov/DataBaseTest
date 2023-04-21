package com.a1qa.v1task.models;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CompanyTests {
    private String duration;
    private String method;
    private String name;
    private String startTime;
    private String endTime;
    private String status;

}