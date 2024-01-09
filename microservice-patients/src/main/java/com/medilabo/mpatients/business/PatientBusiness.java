package com.medilabo.mpatients.business;

import com.medilabo.mpatients.dao.db.PatientDao;
import com.medilabo.mpatients.dao.db.entities.PatientEntity;
import com.medilabo.mpatients.mapper.PatientMapper;
import com.medilabo.mpatients.model.Patient;
import com.medilabo.mpatients.web.exceptions.PatientBadRequestException;
import com.medilabo.mpatients.web.exceptions.PatientInternalServerErrorException;
import com.medilabo.mpatients.web.exceptions.PatientNoContentException;
import com.medilabo.mpatients.web.exceptions.PatientNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;

/**
 * PatientBusiness is the patients page processing service
 *
 * @author MC
 * @version 1.0
 */
@Slf4j
@Service
public class PatientBusiness {

    @Autowired
    private PatientDao patientDao;
    @Autowired
    private PatientMapper patientMapper;

    /**
     * Get Patients Information
     *
     * @return List of patients
     * @throws PatientNoContentException Exception
     */
    public List<Patient> getPatients()
            throws PatientNoContentException {
        List<PatientEntity> patientEntities = patientDao.findAll();
        // Empty list
        if(patientEntities.isEmpty()) {
            throw new PatientNoContentException("No patients found");
        }
        // Patients found
        return patientMapper.entitiesToDao(patientEntities);
    }

    /**
     * Create a new patient.
     *
     * @param patient The patient object added
     * @return Patient added
     * @throws PatientInternalServerErrorException,PatientBadRequestException Exception
     */
    @Transactional(rollbackFor = Exception.class)
    public Patient AddPatient(final Patient patient)
            throws PatientInternalServerErrorException, PatientBadRequestException {
        // Patient parameter is null
        if (patient == null) {
            throw new PatientBadRequestException("Patient is null");
        }

        // Patient exist
        if (patientDao.existsByFirstNameAndLastNameAndBirthOfDate(
                patient.getFirstName(), patient.getLastName(), patient.getBirthOfDate())) {
            throw new PatientBadRequestException("Patient already exists");
        }

        // Patient saved
        PatientEntity patientEntity = patientMapper.daoToEntity(patient);
//        patientEntity.setCreateName("");
        patientEntity.setCreateDate(Timestamp.from(OffsetDateTime.now().toInstant()));
        PatientEntity newPatientEntity = patientDao.save(patientEntity);
        if(newPatientEntity == null) {
            throw new PatientInternalServerErrorException("Unable to add this patient");
        }
        return patientMapper.entityToDao(newPatientEntity);
    }

    /**
     * Get Patient Info by Patient ID
     *
     * @param id Patient ID founded
     * @return Patient founded
     * @throws PatientNotFoundException Exception
     */
    public Optional<Patient> getPatient(final int id)
            throws PatientNotFoundException {
        // Patient does not exist
        Optional<PatientEntity> patientEntity = patientDao.findById(id);
        if(!patientEntity.isPresent()) {
            throw new PatientNotFoundException("This patient does not exist");
        }
        // Patient found
        Patient patient = patientMapper.entityToDao(patientEntity.get());
        return Optional.ofNullable(patient);
    }

    /**
     * Update Patient Information
     *
     * @param id Patient ID updated
     * @param patient The patient object updated
     * @return Patient updated
     * @throws PatientNotFoundException Exception
     */
    @Transactional(rollbackFor = Exception.class)
    public Patient updatePatient(final Integer id
            , final Patient patient)
            throws PatientNotFoundException {
        // Patient does not exist
        Optional<PatientEntity> patientEntity = patientDao.findById(id);
        if(!patientEntity.isPresent()) {
            throw new PatientNotFoundException("This patient does not exist");
        }

        // Patient updated
        PatientEntity currentPatientEntity = patientEntity.get();
        if(patient.getFirstName() != null) {
            currentPatientEntity.setFirstName(patient.getFirstName());
        }
        if(patient.getLastName() != null) {
            currentPatientEntity.setLastName(patient.getLastName());
        }
        if(patient.getBirthOfDate() != null) {
            currentPatientEntity.setBirthOfDate(patient.getBirthOfDate());
        }
        if(patient.getGender() != null) {
            currentPatientEntity.setGender(patient.getGender());
        }
        if(patient.getAddress() != null) {
            currentPatientEntity.setAddress(patient.getAddress());
        }
        if(patient.getPhoneNumber() != null) {
            currentPatientEntity.setPhoneNumber(patient.getPhoneNumber());
        }
//        currentPatientEntity.setUpdateName("");
        currentPatientEntity.setUpdateDate(Timestamp.from(OffsetDateTime.now().toInstant()));
        PatientEntity newPatientEntity = patientDao.save(currentPatientEntity);
        return patientMapper.entityToDao(newPatientEntity);
    }

    /**
     * Delete Patient Information
     *
     * @param id Patient ID deleted
     * @throws PatientNotFoundException Exception
     */
    @Transactional(rollbackFor = Exception.class)
    public void deletePatient(final Integer id)
            throws PatientNotFoundException {
        // Patient does not exist
        Optional<PatientEntity> patientEntity = patientDao.findById(id);
        if(!patientEntity.isPresent()) {
            throw new PatientNotFoundException("This patient does not exist");
        }
        // Patient deleted
        patientDao.deleteById(id);
    }
}
