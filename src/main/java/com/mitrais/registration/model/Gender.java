package com.mitrais.registration.model;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum Gender {
    MALE, FEMALE;

    @JsonCreator
    public static Gender forName(String name) {
        for(Gender c: values()) {
            if(c.name().equals(name)) {
                return c;
            }
        }
        return null;
    }
}
