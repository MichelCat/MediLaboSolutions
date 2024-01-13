package com.medilabo.mpatients.model;

import com.medilabo.mpatients.constant.PhoneNumberConstraint;
import com.medilabo.mpatients.enumerator.Gender;
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
 * Patient
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level= AccessLevel.PRIVATE)
@Builder
@ToString
public class Patient {

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
   * ISO Date Format yyyy-MM-dd — for example, "2000-10-31"
   */
  @NotNull(message = "Birth of date must not be null")
  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
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
   * ISO Date Time Format yyyy-MM-dd'T'HH:mm:ss.SSSXXX — for example, "2000-10-31T01:30:00.000-05:00"
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
   * ISO Date Time Format yyyy-MM-dd'T'HH:mm:ss.SSSXXX — for example, "2000-10-31T01:30:00.000-05:00"
   */
  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
  OffsetDateTime updateDate;
}

