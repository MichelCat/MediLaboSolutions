package com.medilabo.mpatients.web.exceptions;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * PatientNotFoundException is the exception NOT FOUND
 *
 * @author MC
 * @version 1.0
 */
@Slf4j
@ResponseStatus(HttpStatus.NOT_FOUND)
public class PatientNotFoundException extends RuntimeException {
    public PatientNotFoundException(String message) {
        super(message);
        log.debug("Exception, " + message);
    }
}
