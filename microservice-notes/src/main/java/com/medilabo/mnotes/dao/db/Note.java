package com.medilabo.mnotes.dao.db;

import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * Note is document notes
 *
 * @author MC
 * @version 1.0
 */
@Getter
@Setter
@FieldDefaults(level= AccessLevel.PRIVATE)
@ToString
@Document(collection = "notes")
public class Note {

    /**
     * Note ID
     */
    @Id
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
