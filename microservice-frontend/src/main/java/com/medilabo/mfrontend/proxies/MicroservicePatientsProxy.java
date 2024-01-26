package com.medilabo.mfrontend.proxies;

import com.medilabo.mfrontend.beans.PatientBean;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * MicroservicePatientsProxy is the Proxy will perform the following actions via Get/Post/Patch/Delete with HTTP on patients.
 *
 * @author MC
 * @version 1.0
 */
//@FeignClient(name = "microservice-patients", url = "localhost:9001")
@FeignClient(name = "gateway-server", url = "localhost:9004", contextId = "microservice-patients")
//@FeignClient(name = "gateway-server")
public interface MicroservicePatientsProxy {
    /**
     * GET /patients : Get Patients Information
     * Retrieve patients information.
     */
    @GetMapping(value = "/patients")
//    @GetMapping(value = "/microservice-patients/patients")
    List<PatientBean> getPatients();

    /**
     * POST /patients : Create New Patient
     * Create a new patient.
     */
    @PostMapping(value = "/patients")
//    @PostMapping(value = "/microservice-patients/patients")
    PatientBean addPatient(@RequestBody PatientBean patient);

    /**
     * GET /patients/{id} : Get Patient Info by Patient ID
     * Retrieve patient information with corresponding patient ID.
     */
    @GetMapping(value = "/patients/{id}")
//    @GetMapping(value = "/microservice-patients/patients/{id}")
    PatientBean getPatient(@PathVariable("id") Integer id);

    /**
     * PATCH /patients/{id} : Update Patient Information
     * Update information for an existing patient.
     */
    @PostMapping(value = "/patients/{id}")
//    @PostMapping(value = "/microservice-patients/patients/{id}")
    PatientBean updatePatient(@PathVariable("id") Integer id
            , @RequestBody PatientBean patient);

    /**
     * DELETE /patients/{id} : Delete Patient Information
     * Delete information for an existing patient.
     */
    @DeleteMapping("/patients/{id}")
//    @DeleteMapping("/microservice-patients/patients/{id}")
    void deletePatient(@PathVariable("id") Integer id);
}
