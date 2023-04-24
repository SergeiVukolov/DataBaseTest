package com.a1qa.v1task.utils;

import java.util.Random;

public class RandomString {

    public static String getRandomString() {
        StringBuilder randStringTitle = new StringBuilder();
        String latinSymbols = "abcdefghijklmnopqrstuvwxyz";
        Random random = new Random();
        int count = random.nextInt(latinSymbols.length());
        for (int i = 0; i<count; i++) {
            randStringTitle.append(latinSymbols.charAt((int) (Math.random() * latinSymbols.length())));
        }
        return randStringTitle.toString();
    }

    public static long getRandomLong() {
        StringBuilder randInt = new StringBuilder();
        String counts = "1234567890";
        Random random = new Random();
        int count = Enum.NumberEnum.SIXTEEN.getValue();
        for (int i = 0; i<count; i++) {
            randInt.append(counts.charAt((int) (Math.random() * counts.length())));
        }
        return Long.parseLong(randInt.toString());
    }

}
