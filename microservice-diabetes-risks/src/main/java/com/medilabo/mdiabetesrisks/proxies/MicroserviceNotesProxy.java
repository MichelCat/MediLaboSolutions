package com.medilabo.mdiabetesrisks.proxies;

import com.medilabo.mdiabetesrisks.beans.NoteBean;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * MicroserviceNotesProxy is the Proxy will perform the following actions via Get/Post/Patch/Delete with HTTP on notes.
 *
 * @author MC
 * @version 1.0
 */
@FeignClient(name = "gateway-server", url = "${spring.cloud.openfeign.client.config.gateway.url}", contextId = "microservice-notes")
public interface MicroserviceNotesProxy {

    /**
     * GET /notes/patients : Get notes for patient id
     * Retrieve notes
     */
    @GetMapping(value = "/notes/patients/{id}")
    public List<NoteBean> getNotesByPatientId(@PathVariable("id") Integer id);
}
