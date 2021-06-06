package com.sample.constant;

public enum Gender {
    MALE(1), FEMALE(2), OTHER(3);
    private final int gender;

    Gender(int i) {
        this.gender = i;
    }

    public int getGender(){
        return this.gender;
    }
}
