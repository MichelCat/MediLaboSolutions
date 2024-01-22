package com.medilabo.mnotes.web.controller;

import com.medilabo.mnotes.dao.business.NoteBusiness;
import com.medilabo.mnotes.dao.db.Note;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * NoteController is the Endpoint will perform the following actions via Get/Post/Patch/Delete with HTTP on notes.
 *
 * @author MC
 * @version 1.0
 */
@Slf4j
@RestController
@RequestMapping("notes")
public class NoteController {

    @Autowired
    private NoteBusiness noteBusiness;

    /**
     * GET /notes/patients : Get notes for patient id
     * Retrieve notes
     *
     * @return OK (status code 200)
     *         or No Content (status code 204)
     *         or Bad Request (status code 400)
     */
    @GetMapping(value = "patients/{id}")
    public List<Note> getNotesByPatientId(@PathVariable("id") Integer id) {
        log.debug("HTTP GET, Get notes for patient id");
        return noteBusiness.getNotesByPatientId(id);
    }

    /**
     * POST /notes : Create New Note
     * Create a new note.
     *
     * @param note The note object added
     * @return Created (status code 201)
     *         or Bad Request (status code 400)
     *         or INTERNAL SERVER ERROR (status code 500)
     */
    @PostMapping(value = "")
    public ResponseEntity<Note> addNote(@RequestBody Note note) {
        log.debug("HTTP GET, Create New Note");
        Note newNote = noteBusiness.addNote(note);
        return new ResponseEntity<Note>(newNote, HttpStatus.CREATED);
    }

    /**
     * GET /notes/{id} : Get Note by Note ID
     * Retrieve Note with corresponding note ID.
     *
     * @param id Note ID founded (required)
     * @return OK (status code 200)
     *         or Bad Request (status code 400)
     *         or Not Found (status code 404)
     */
    @GetMapping(value = "/{id}")
    public Optional<Note> getNote(@PathVariable("id") String id) {
        log.debug("HTTP POST, Get Note by Note ID " + id);
        return noteBusiness.getNote(id);
    }

    /**
     * PATCH /notes/{id} : Update note
     * Update information for an existing note.
     *
     * @param id Note ID added (required)
     * @param note The note object updated
     * @return OK (status code 200)
     *         or Bad Request (status code 400)
     *         or Not Found (status code 404)
     */
    @PatchMapping(value = "/{id}")
    public Note updateNote(@PathVariable("id") String id
            , @RequestBody Note note) {
        log.debug("HTTP PATCH, Update note " + id);
        return noteBusiness.updateNote(id, note);
    }

    /**
     * DELETE /notes/{id} : Delete note
     * Delete information for an existing note.
     *
     * @param id Note ID deleted (required)
     * @return OK (status code 200)
     *         or Bad Request (status code 400)
     *         or Not Found (status code 404)
     */
    @DeleteMapping("/{id}")
    public void deleteNote(@PathVariable("id") String id) {
        log.debug("HTTP PATCH, Delete Note " + id);
        noteBusiness.deleteNote(id);
    }
}
