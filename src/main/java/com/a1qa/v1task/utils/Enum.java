package com.a1qa.v1task.utils;

public class Enum {

    public enum NumberEnum {
        ZERO(0),
        ONE(1),
        TWO(2),
        THREE(3),
        FOUR(4),
        FIVE(5),
        SIXTEEN(16),
        THIRTYONE(31);


        private int value;

        NumberEnum(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

}
