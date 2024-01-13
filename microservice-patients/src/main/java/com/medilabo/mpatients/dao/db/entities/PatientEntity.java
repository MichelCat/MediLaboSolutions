package com.medilabo.mpatients.dao.db.entities;

import com.medilabo.mpatients.constant.PhoneNumberConstraint;
import com.medilabo.mpatients.enumerator.Gender;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.sql.Timestamp;
import java.time.LocalDate;

/**
 * PatientEntity is entity model
 *
 * @author MC
 * @version 1.0
 */
@Entity
@Table(name = "patient"
        , uniqueConstraints = { @UniqueConstraint(name = "uc_patient_firstname_lastname_birthofdate"
        , columnNames = { "last_name", "first_name", "birth_of_date" }) })
@FieldDefaults(level= AccessLevel.PRIVATE)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class PatientEntity {

    /**
     * Patient ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
    LocalDate birthOfDate;

    /**
     * Patient gender (M=Male/F=Female)
     */
    @NotNull(message = "Gender must not be null")
    @Enumerated(value = EnumType.STRING)
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
    Timestamp createDate;

    /**
     * Update name
     */
    @Size(max = 125, message = "Maximum length of {max} characters")
    String updateName;

    /**
     * Update date
     */
    Timestamp updateDate;
}
