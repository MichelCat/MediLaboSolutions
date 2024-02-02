package com.medilabo.mfrontend.proxies;

import com.medilabo.mfrontend.beans.NoteBean;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * MicroserviceNotesProxy is the Proxy will perform the following actions via Get/Post/Patch/Delete with HTTP on notes.
 *
 * @author MC
 * @version 1.0
 */
@FeignClient(name = "gateway-server", url = "${spring.cloud.openfeign.client.config.gateway.url}", contextId = "microservice-notes")
public interface MicroserviceNotesProxy {

    /**
     * GET /notes/patients : Get notes for patient id
     * Retrieve notes
     */
    @GetMapping(value = "/notes/patients/{id}")
    List<NoteBean> getNotesByPatientId(@PathVariable("id") Integer id);

    /**
     * POST /notes : Create New Note
     * Create a new note.
     */
    @PostMapping(value = "/notes")
    NoteBean addNote(@RequestBody NoteBean note);

    /**
     * GET /notes/{id} : Get Note by Note ID
     * Retrieve Note with corresponding note ID.
     */
    @GetMapping(value = "/notes/{id}")
    NoteBean getNote(@PathVariable("id") String id);

    /**
     * PATCH /notes/{id} : Update note
     * Update information for an existing note.
     */
    @PostMapping(value = "/notes/{id}")
    NoteBean updateNote(@PathVariable("id") String id
            , @RequestBody NoteBean note);

    /**
     * DELETE /notes/{id} : Delete note
     * Delete information for an existing note.
     */
    @DeleteMapping("/notes/{id}")
    void deleteNote(@PathVariable("id") String id);

    /**
     * DELETE /notes/patients/{id} : Delete notes for patient id
     */
    @DeleteMapping("/notes/patients/{id}")
    void deleteNotesByPatientId(@PathVariable("id") Integer id);
}
