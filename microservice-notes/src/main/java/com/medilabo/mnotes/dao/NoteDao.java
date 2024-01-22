package com.medilabo.mnotes.dao;

import com.medilabo.mnotes.dao.db.Note;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * NoteDao is the interface that manages Note
 *
 * @author MC
 * @version 1.0
 */
@Repository
public interface NoteDao extends MongoRepository<Note, String> {
    public List<Note> findAllByPatientId (int patientId);
}
