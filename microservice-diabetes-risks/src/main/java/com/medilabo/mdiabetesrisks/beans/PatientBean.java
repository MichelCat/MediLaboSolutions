package com.medilabo.mdiabetesrisks.beans;

import com.medilabo.mdiabetesrisks.constant.PhoneNumberConstraint;
import com.medilabo.mdiabetesrisks.enumerator.Gender;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.OffsetDateTime;

/**
 * PatientBean
 */
@Getter
@Setter
@NoArgsConstructor
@FieldDefaults(level= AccessLevel.PRIVATE)
@ToString
public class PatientBean {
    /**
     * Patient ID
     */
    Integer id;

    /**
     * Patient first name
     */
    @NotBlank(message = "{constraint.patient.firstName.notBlank}")
    @Size(max = 125, message = "{constraint.size.global}")
    String firstName;

    /**
     * Patient last name
     */
    @NotBlank(message = "{constraint.patient.lastName.notBlank}")
    @Size(max = 125, message = "{constraint.size.global}")
    String lastName;

    /**
     * Patient birth of date
     * ISO Date Format yyyy-MM-dd — for example, "2000-10-31"
     */
    @NotNull(message = "{constraint.patient.birthOfDate.notNull}")
    @PastOrPresent(message = "{constraint.patient.birthOfDate.pastOrPresent}")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    LocalDate birthOfDate;

    /**
     * Patient gender (M=Male/F=Female)
     */
    @NotNull(message = "{constraint.patient.gender.notNull}")
    @Enumerated(value = EnumType.STRING)
    Gender gender;

    /**
     * Patient address
     */
    @Size(max = 125, message = "{constraint.size.global}")
    String address;

    /**
     * Patient phone number
     */
    @Pattern(regexp = PhoneNumberConstraint.REGEXP, message = PhoneNumberConstraint.MESSAGE)
    @Size(max = 12, message = "{constraint.size.global}")
    String phoneNumber;

    /**
     * Create date
     * ISO Date Time Format yyyy-MM-dd'T'HH:mm:ss.SSSXXX — for example, "2000-10-31T01:30:00.000-05:00"
     */
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    OffsetDateTime createDate;

    /**
     * Update date
     * ISO Date Time Format yyyy-MM-dd'T'HH:mm:ss.SSSXXX — for example, "2000-10-31T01:30:00.000-05:00"
     */
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    OffsetDateTime updateDate;
}
