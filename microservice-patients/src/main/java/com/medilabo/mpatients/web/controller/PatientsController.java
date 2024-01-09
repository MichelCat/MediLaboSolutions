package com.medilabo.mpatients.web.controller;

import com.medilabo.mpatients.business.PatientBusiness;
import com.medilabo.mpatients.model.Patient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * PatientsController is the Endpoint will perform the following actions via Get/Post/Patch/Delete with HTTP on patients.
 *
 * @author MC
 * @version 1.0
 */
@Slf4j
@RestController
@RequestMapping("patients")
public class PatientsController {

    @Autowired
    private PatientBusiness patientBusiness;

    /**
     * GET /patients : Get Patients Information
     * Retrieve patients information.
     *
     * @return OK (status code 200)
     *         or No Content (status code 204)
     *         or Bad Request (status code 400)
     */
    @GetMapping(value = "")
    public List<Patient> getPatients() {
        log.debug("HTTP GET, Get Patients Information");
        return patientBusiness.getPatients();
    }

    /**
     * POST /patients : Create New Patient
     * Create a new patient.
     *
     * @param patient The patient object added
     * @return Created (status code 201)
     *         or Bad Request (status code 400)
     *         or INTERNAL SERVER ERROR (status code 500)
     */
    @PostMapping(value = "")
    public ResponseEntity<Patient> addPatient(@RequestBody Patient patient) {
        log.debug("HTTP GET, Create New Patient");
        Patient newPatient = patientBusiness.AddPatient(patient);
        return new ResponseEntity<Patient>(newPatient, HttpStatus.CREATED);
    }

    /**
     * GET /patients/{patientId} : Get Patient Info by Patient ID
     * Retrieve patient information with corresponding patient ID.
     *
     * @param id Patient ID founded (required)
     * @return OK (status code 200)
     *         or Bad Request (status code 400)
     *         or Not Found (status code 404)
     */
    @GetMapping(value = "/{id}")
    public Optional<Patient> getPatient(@PathVariable int id) {
        log.debug("HTTP POST, Get Patient Info by Patient ID " + id);
        return patientBusiness.getPatient(id);
    }

    /**
     * PATCH /patients/{patientId} : Update Patient Information
     * Update information for an existing patient.
     *
     * @param id Patient ID added (required)
     * @param patient The patient object updated
     * @return OK (status code 200)
     *         or Bad Request (status code 400)
     *         or Not Found (status code 404)
     */
    @PatchMapping(value = "/{id}")
    public Patient updatePatient(@PathVariable("id") Integer id
                                 , @RequestBody Patient patient) {
        log.debug("HTTP PATCH, Update Patient Information " + id);
        return patientBusiness.updatePatient(id, patient);
    }

    /**
     * DELETE /patients/{patientId} : Delete Patient Information
     * Delete information for an existing patient.
     *
     * @param id Patient ID deleted (required)
     * @return OK (status code 200)
     *         or Bad Request (status code 400)
     *         or Not Found (status code 404)
     */
    @DeleteMapping("/{id}")
    public void deletePatient(@PathVariable("id") Integer id) {
        log.debug("HTTP PATCH, Delete Patient Information " + id);
        patientBusiness.deletePatient(id);
    }
}
