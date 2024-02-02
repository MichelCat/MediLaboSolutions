package com.medilabo.mdiabetesrisks.beans;

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
@AllArgsConstructor
@FieldDefaults(level= AccessLevel.PRIVATE)
@Builder
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
    String observationNote;

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
