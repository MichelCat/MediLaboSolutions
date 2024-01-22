package com.medilabo.mnotes.dao.db;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
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
@ToString
@Document(collection = "notes")
public class Note {
    @Id
    private String id;

    @NotNull
    private int patientId;

    private String note;

    /**
     * Create date
     * ISO Date Time Format yyyy-MM-dd'T'HH:mm:ss.SSSXXX — for example, "2000-10-31T01:30:00.000-05:00"
     */
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private Date createDate;

    /**
     * Update date
     * ISO Date Time Format yyyy-MM-dd'T'HH:mm:ss.SSSXXX — for example, "2000-10-31T01:30:00.000-05:00"
     */
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private Date updateDate;
}
