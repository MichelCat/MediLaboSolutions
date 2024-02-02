package com.medilabo.mpatients.web.exceptions;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * PatientBadRequestException is the exception BAD REQUEST
 *
 * @author MC
 * @version 1.0
 */
@Slf4j
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class PatientBadRequestException extends RuntimeException {
    public PatientBadRequestException(String message) {
        super(message);
        log.debug("Exception, " + message);
    }
}
