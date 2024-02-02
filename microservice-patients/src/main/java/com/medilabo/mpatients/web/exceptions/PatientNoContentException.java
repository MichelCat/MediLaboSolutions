package com.medilabo.mpatients.web.exceptions;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * PatientNoContentException is the exception NO CONTENT
 *
 * @author MC
 * @version 1.0
 */
@Slf4j
@ResponseStatus(HttpStatus.NO_CONTENT)
public class PatientNoContentException extends RuntimeException {
    public PatientNoContentException(String message) {
        super(message);
        log.debug("Exception, " + message);
    }
}
