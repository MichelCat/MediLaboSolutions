package com.medilabo.mfrontend.enumerator;

/**
 * Gender is Enumeration for Gender (M=Male/F=Female)
 *
 * @author MC
 * @version 1.0
 */
public enum Gender {
    M("Male"), F("Female");

    private final String description;

    private Gender(String description){
        this.description = description;
    }
}
