package com.medilabo.mpatients.business;

import com.medilabo.mpatients.model.Patient;
import com.medilabo.mpatients.web.exceptions.PatientBadRequestException;
import com.medilabo.mpatients.web.exceptions.PatientInternalServerErrorException;
import com.medilabo.mpatients.web.exceptions.PatientNoContentException;
import com.medilabo.mpatients.web.exceptions.PatientNotFoundException;

import java.util.List;
import java.util.Optional;

/**
 * PatientBusiness is interface is the patient processing service
 *
 * @author MC
 * @version 1.0
 */
public interface PatientBusiness {
    /**
     * Get Patients Information
     *
     * @return List of patients
     * @throws PatientNoContentException Exception
     */
    public List<Patient> getPatients()
            throws PatientNoContentException;

    /**
     * Create a new patient.
     *
     * @param patient The patient object added
     * @return Patient added
     * @throws PatientInternalServerErrorException,PatientBadRequestException Exception
     */
    public Patient addPatient(final Patient patient)
            throws PatientInternalServerErrorException, PatientBadRequestException;

    /**
     * Get Patient Info by Patient ID
     *
     * @param id Patient ID founded
     * @return Patient founded
     * @throws PatientNotFoundException Exception
     */
    public Optional<Patient> getPatient(final int id)
            throws PatientNotFoundException;

    /**
     * Update Patient Information
     *
     * @param id Patient ID updated
     * @param patient The patient object updated
     * @return Patient updated
     * @throws PatientNotFoundException Exception
     */
    public Patient updatePatient(final Integer id
            , final Patient patient)
            throws PatientNotFoundException;

    /**
     * Delete Patient Information
     *
     * @param id Patient ID deleted
     * @throws PatientNotFoundException Exception
     */
    public void deletePatient(final Integer id)
            throws PatientNotFoundException;
}
