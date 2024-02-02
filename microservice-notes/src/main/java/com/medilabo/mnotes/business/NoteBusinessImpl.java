package com.medilabo.mnotes.business;

import com.medilabo.mnotes.dao.NoteDao;
import com.medilabo.mnotes.dao.db.Note;
import com.medilabo.mnotes.web.exceptions.NoteBadRequestException;
import com.medilabo.mnotes.web.exceptions.NoteInternalServerErrorException;
import com.medilabo.mnotes.web.exceptions.NoteNoContentException;
import com.medilabo.mnotes.web.exceptions.NoteNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * NoteBusiness is the note processing service
 *
 * @author MC
 * @version 1.0
 */
@Slf4j
@Service
public class NoteBusinessImpl implements NoteBusiness {
    @Autowired
    private NoteDao noteDao;

    /**
     * Get Notes for patient id
     *
     * @param id Patient ID founded
     * @return List of note for patient id
     * @throws NoteNoContentException Exception
     */
    @Override
    public List<Note> getNotesByPatientId(final int id)
            throws NoteNoContentException {
        return noteDao.findAllByPatientId(id);
    }

    /**
     * Create a new note.
     *
     * @param note The note object added
     * @return Note added
     * @throws NoteInternalServerErrorException, NoteBadRequestException Exception
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Note addNote(final Note note)
            throws NoteInternalServerErrorException, NoteBadRequestException {
        // Note parameter is null
        if (note == null) {
            throw new NoteBadRequestException("Note is null");
        }

        // Note saved
        note.setCreateDate(new Date());
        Note newNote = noteDao.save(note);
        if(newNote == null) {
            throw new NoteInternalServerErrorException("Unable to add this note");
        }
        return newNote;
    }

    /**
     * Get Note by Note ID
     *
     * @param id Note ID founded
     * @return Note founded
     * @throws NoteNotFoundException Exception
     */
    @Override
    public Optional<Note> getNote(final String id)
            throws NoteNotFoundException {
        // Note does not exist
        Optional<Note> note = noteDao.findById(id);
        if(!note.isPresent()) {
            throw new NoteNotFoundException("This note does not exist");
        }
        // Note found
        return note;
    }

    /**
     * Update Note
     *
     * @param id Note ID updated
     * @param note The note object updated
     * @return Note updated
     * @throws NoteNotFoundException Exception
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Note updateNote(final String id
            , final Note note)
            throws NoteNotFoundException {
        // Note does not exist
        Optional<Note> oldNote = noteDao.findById(id);
        if(!oldNote.isPresent()) {
            throw new NoteNotFoundException("This note does not exist");
        }

        // Note updated
        Note newNote = oldNote.get();
        if(note.getObservationNote() != null) {
            newNote.setObservationNote(note.getObservationNote());
        }
        newNote.setUpdateDate(new Date());
        return noteDao.save(newNote);
    }

    /**
     * Delete Note
     *
     * @param id Note ID deleted
     * @throws NoteNotFoundException Exception
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteNote(final String id)
            throws NoteNotFoundException {
        // Note does not exist
        Optional<Note> note = noteDao.findById(id);
        if(!note.isPresent()) {
            throw new NoteNotFoundException("This note does not exist");
        }
        // Note deleted
        noteDao.deleteById(id);
    }

    /**
     * Delete Notes for patient id
     *
     * @param id Patient ID founded
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteNotesByPatientId(final int id) {
        noteDao.deleteByPatientId(id);
    }
}
