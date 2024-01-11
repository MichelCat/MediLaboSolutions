package com.medilabo.mfrontend.constant;

public class PhoneNumberConstraint {
    public static final String REGEXP = "^(\\d{3}[-]?){2}\\d{4}$";

    public static final String MESSAGE = "Phone number format is 999-999-9999 or empty";
}