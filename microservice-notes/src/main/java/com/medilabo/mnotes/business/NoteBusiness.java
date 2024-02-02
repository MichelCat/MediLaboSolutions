package com.medilabo.mnotes.business;

import com.medilabo.mnotes.dao.db.Note;
import com.medilabo.mnotes.web.exceptions.NoteBadRequestException;
import com.medilabo.mnotes.web.exceptions.NoteInternalServerErrorException;
import com.medilabo.mnotes.web.exceptions.NoteNoContentException;
import com.medilabo.mnotes.web.exceptions.NoteNotFoundException;

import java.util.List;
import java.util.Optional;

/**
 * NoteBusiness is interface is the note processing service
 *
 * @author MC
 * @version 1.0
 */
public interface NoteBusiness {
    /**
     * Get Notes for patient id
     *
     * @param id Patient ID founded
     * @return List of note for patient id
     * @throws NoteNoContentException Exception
     */
    public List<Note> getNotesByPatientId(final int id)
            throws NoteNoContentException;

    /**
     * Create a new note.
     *
     * @param note The note object added
     * @return Note added
     * @throws NoteInternalServerErrorException, NoteBadRequestException Exception
     */
    public Note addNote(final Note note)
            throws NoteInternalServerErrorException, NoteBadRequestException;

    /**
     * Get Note by Note ID
     *
     * @param id Note ID founded
     * @return Note founded
     * @throws NoteNotFoundException Exception
     */
    public Optional<Note> getNote(final String id)
            throws NoteNotFoundException;

    /**
     * Update Note
     *
     * @param id Note ID updated
     * @param note The note object updated
     * @return Note updated
     * @throws NoteNotFoundException Exception
     */
    public Note updateNote(final String id
            , final Note note)
            throws NoteNotFoundException;

    /**
     * Delete Note
     *
     * @param id Note ID deleted
     * @throws NoteNotFoundException Exception
     */
    public void deleteNote(final String id)
            throws NoteNotFoundException;

    /**
     * Delete Notes for patient id
     *
     * @param id Patient ID founded
     */
    public void deleteNotesByPatientId(final int id);
}
