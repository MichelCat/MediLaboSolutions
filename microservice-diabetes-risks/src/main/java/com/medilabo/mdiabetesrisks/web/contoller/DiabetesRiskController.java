package com.medilabo.mdiabetesrisks.web.contoller;

import com.medilabo.mdiabetesrisks.business.DiabetesRiskBusiness;
import com.medilabo.mdiabetesrisks.model.DiabetesRisk;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * DiabetesRiskController is the Endpoint will perform the following actions via Get/Post/Put/Delete with HTTP on diabetes risk.
 *
 * @author MC
 * @version 1.0
 */
@Slf4j
@Controller
public class DiabetesRiskController {

    @Autowired
    private DiabetesRiskBusiness diabetesRiskBusiness;
    @Autowired
    private MessageSource messageSource;

    /**
     * GET /diabetes-risk/{id} : Get diabetes risk by Patient ID
     * Retrieve diabetes risk with corresponding patient ID.
     *
     * @param id Patient ID founded (required)
     * @return OK (status code 200)
     *         or Bad Request (status code 400)
     *         or Not Found (status code 404)
     */
    @GetMapping(value = "/diabetes-risk/{id}")
    public ResponseEntity<DiabetesRisk> getDiabetesRiskByPatientId(@PathVariable("id") Integer id) {
        log.debug("HTTP POST, Get diabetes risk by Patient ID " + id);
        DiabetesRisk diabetesRisk = diabetesRiskBusiness.diabetesRiskAssessmentByPatientId(id);
        return new ResponseEntity<DiabetesRisk>(diabetesRisk, HttpStatus.ACCEPTED);
    }
}
