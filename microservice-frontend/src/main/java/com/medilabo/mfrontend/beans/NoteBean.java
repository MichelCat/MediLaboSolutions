package com.medilabo.mfrontend.beans;

import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * NoteBean
 */
@Getter
@Setter
@NoArgsConstructor
@FieldDefaults(level= AccessLevel.PRIVATE)
@ToString
public class NoteBean {

    /**
     * Note ID
     */
    String id;

    /**
     * Patient ID
     */
    @NotNull
    int patientId;

    /**
     * Observation note
     */
    String note;

    /**
     * Create date
     * ISO Date Time Format yyyy-MM-dd'T'HH:mm:ss.SSSXXX — for example, "2000-10-31T01:30:00.000-05:00"
     */
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    Date createDate;

    /**
     * Update date
     * ISO Date Time Format yyyy-MM-dd'T'HH:mm:ss.SSSXXX — for example, "2000-10-31T01:30:00.000-05:00"
     */
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    Date updateDate;
}
