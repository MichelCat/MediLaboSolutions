package com.medilabo.mfrontend.proxies;

import com.medilabo.mfrontend.beans.DiabetesRiskBean;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * MicroserviceDiabetesRisksProxy is the Proxy will perform the following actions via Get/Post/Patch/Delete with HTTP on diabetes risks.
 *
 * @author MC
 * @version 1.0
 */
@FeignClient(name = "gateway-server", url = "${spring.cloud.openfeign.client.config.gateway.url}", contextId = "microservice-diabetes-risks")
public interface MicroserviceDiabetesRisksProxy {

    /**
     * GET /diabetes-risk/{id} : Get diabetes risk by Patient ID
     * Retrieve diabetes risk with corresponding patient ID.
     */
    @GetMapping(value = "/diabetes-risk/{id}")
    DiabetesRiskBean getDiabetesRiskByPatientId(@PathVariable("id") Integer id);
}
