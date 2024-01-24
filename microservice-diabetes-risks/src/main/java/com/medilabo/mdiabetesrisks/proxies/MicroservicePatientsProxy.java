package com.medilabo.mdiabetesrisks.proxies;

import com.medilabo.mdiabetesrisks.beans.PatientBean;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

/**
 * MicroservicePatientsProxy is the Proxy will perform the following actions via Get/Post/Patch/Delete with HTTP on patients.
 *
 * @author MC
 * @version 1.0
 */
// TODO
@FeignClient(name = "microservice-patients", url = "localhost:9001")
//@FeignClient(name = "gateway-server", url = "localhost:9004", contextId = "microservice-patients")
public interface MicroservicePatientsProxy {

    /**
     * GET /patients/{id} : Get Patient Info by Patient ID
     * Retrieve patient information with corresponding patient ID.
     */
    @GetMapping(value = "/patients/{id}")
//    @GetMapping(value = "/microservice-patients/patients/{id}")
//    Optional<PatientBean> getPatient(@PathVariable("id") Integer id);
    PatientBean getPatient(@PathVariable("id") Integer id);
}
