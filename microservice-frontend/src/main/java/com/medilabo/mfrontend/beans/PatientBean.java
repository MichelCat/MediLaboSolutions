package com.medilabo.mfrontend.beans;

import com.medilabo.mfrontend.constant.PhoneNumberConstraint;
import com.medilabo.mfrontend.enumerator.Gender;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
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
    @NotBlank(message = "FirstName is mandatory")
    @Size(max = 125, message = "Maximum length of {max} characters")
    String firstName;

    /**
     * Patient last name
     */
    @NotBlank(message = "LastName is mandatory")
    @Size(max = 125, message = "Maximum length of {max} characters")
    String lastName;

    /**
     * Patient birth of date
     */
    @NotNull(message = "Birth of date must not be null")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    LocalDate birthOfDate;

    /**
     * Patient gender (M=Male/F=Female)
     */
    @NotNull(message = "Gender must not be null")
    @Enumerated(value = EnumType.STRING)
//  @Size(max = 1, message = "Maximum length of {max} characters")
    Gender gender;

    /**
     * Patient address
     */
    @Size(max = 125, message = "Maximum length of {max} characters")
    String address;

    /**
     * Patient phone number
     */
    @Pattern(regexp = PhoneNumberConstraint.REGEXP, message = PhoneNumberConstraint.MESSAGE)
    @Size(max = 12, message = "Maximum length of {max} characters")
    String phoneNumber;

    /**
     * Create name
     */
    @Size(max = 125, message = "Maximum length of {max} characters")
    String createName;

    /**
     * Create date
     */
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    OffsetDateTime createDate;

    /**
     * Update name
     */
    @Size(max = 125, message = "Maximum length of {max} characters")
    String updateName;

    /**
     * Update date
     */
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    OffsetDateTime updateDate;
}
