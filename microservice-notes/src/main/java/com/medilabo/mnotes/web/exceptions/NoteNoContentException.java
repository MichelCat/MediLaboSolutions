package com.medilabo.mnotes.web.exceptions;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * NoteNoContentException is the exception NOT FOUND
 *
 * @author MC
 * @version 1.0
 */
@Slf4j
@ResponseStatus(HttpStatus.NOT_FOUND)
public class NoteNoContentException extends RuntimeException {
    public NoteNoContentException(String message) {
        super(message);
        log.debug("Exception, " + message);
    }
}
