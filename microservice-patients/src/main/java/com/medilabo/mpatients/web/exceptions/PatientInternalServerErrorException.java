package com.medilabo.mpatients.web.exceptions;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * PatientInternalServerErrorException is the exception INTERNAL SERVER ERROR
 *
 * @author MC
 * @version 1.0
 */
@Slf4j
@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class PatientInternalServerErrorException extends RuntimeException {
    public PatientInternalServerErrorException(String message) {
        super(message);
        log.debug("Exception, " + message);
    }
}
