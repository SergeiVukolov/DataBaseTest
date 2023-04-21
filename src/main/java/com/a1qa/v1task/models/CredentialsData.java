package com.a1qa.v1task.models;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CredentialsData {
    String loginWeb;
    String passwordWeb;
    String loginDb;
    String passwordDb;

}