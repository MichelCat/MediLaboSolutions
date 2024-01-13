package com.medilabo.mfrontend.constant;

public class PhoneNumberConstraint {
    public static final String REGEXP = "^(\\d{3}-\\d{3}-\\d{4})?$";

    public static final String MESSAGE = "{constraint.phoneNumber.message}";
}
