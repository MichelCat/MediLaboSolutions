package com.medilabo.mnotes.web.exceptions;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * NoteInternalServerErrorException is the exception INTERNAL SERVER ERROR
 *
 * @author MC
 * @version 1.0
 */
@Slf4j
@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class NoteInternalServerErrorException extends RuntimeException {
    public NoteInternalServerErrorException(String message) {
        super(message);
        log.debug("Exception, " + message);
    }
}
