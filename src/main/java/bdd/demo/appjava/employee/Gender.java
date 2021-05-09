package bdd.demo.appjava.employee;

import com.fasterxml.jackson.annotation.JsonValue;

public enum Gender {
    MALE("M"), FEMALE("F");
    private final String value;
    private Gender(String value) {
        this.value = value;
    }
    @JsonValue
    @Override
    public String toString(){
        return this.value;
    }
}
