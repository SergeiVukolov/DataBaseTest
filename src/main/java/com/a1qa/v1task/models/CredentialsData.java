package com.a1qa.v1task.models;

import lombok.*;

@Data
public class CredentialsData {
    String loginWeb;
    String passwordWeb;
    String loginDb;
    String passwordDb;
}