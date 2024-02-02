package com.medilabo.mfrontend.controller;

import com.medilabo.mfrontend.beans.PatientBean;
import com.medilabo.mfrontend.proxies.MicroserviceNotesProxy;
import com.medilabo.mfrontend.proxies.MicroservicePatientsProxy;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * PatientsController is the Endpoint will perform the following actions via Get/Post/Put/Delete with HTTP on patients.
 *
 * @author MC
 * @version 1.0
 */
@Slf4j
@Controller
//@RequestMapping("patient")
public class PatientsController {

    @Autowired
    private MicroservicePatientsProxy microservicePatientsProxy;
    @Autowired
    private MicroserviceNotesProxy microserviceNotesProxy;
    @Autowired
    private MessageSource messageSource;
    @Value("${medilabo.front.redirect.url}")
    private String gatewayUrl;

    /**
     * Read - Get the list of patients.
     *
     * @param model Model object
     * @return View
     */
    @GetMapping(value = {"/", "/patient/list"})
    public String accueil(Model model){
        String msgSource = messageSource.getMessage("debug.patient.listForm"
                , null, LocaleContextHolder.getLocale());
        log.debug("HTTP GET, " + msgSource);
        model.addAttribute("patients", microservicePatientsProxy.getPatients());
        return "patient/list";
    }

    /**
     * Read - Page add new patient.
     *
     * @param model Model object
     * @return View
     */
    @GetMapping("/patient/add")
    public String addPatientForm(Model model) {
        String msgSource = messageSource.getMessage("debug.patient.addForm"
                , null, LocaleContextHolder.getLocale());
        log.debug("HTTP GET, " + msgSource);
        PatientBean patient = new PatientBean();
        model.addAttribute("patient", patient);
        return "patient/add";
    }

    /**
     * Create - Add new patient
     *
     * @param patient The patient object added
     * @param result Result of a validation
     * @param model Model object
     * @param redirectAttributes RedirectAttributes object
     * @return View
     */
    @PostMapping("/patient/validate")
    public String validate(@Valid @ModelAttribute("patient") PatientBean patient
            , BindingResult result
            , Model model
            , RedirectAttributes redirectAttributes) {

        // Patient parameter is not valid
        if (result.hasErrors()) {
            String msgSource = messageSource.getMessage("debug.patient.validation"
                    , new Object[] { patient }
                    , LocaleContextHolder.getLocale());
            log.debug("HTTP POST, " + msgSource);
            return "patient/add";
        }
        try {
            // Save patient
            microservicePatientsProxy.addPatient(patient);
            String msgSource = messageSource.getMessage("info.patient.created"
                    , null, LocaleContextHolder.getLocale());
            log.info("HTTP POST, " + msgSource);
            redirectAttributes.addFlashAttribute("successMessage", msgSource);
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
        }
        return "redirect:"+gatewayUrl+"/patient/list";
    }

    /**
     * Read - Page add new patient.
     *
     * @param id Patient ID added
     * @param model Model object
     * @param redirectAttributes RedirectAttributes object
     * @return View
     */
    @GetMapping("/patient/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id
            , Model model
            , RedirectAttributes redirectAttributes) {

        try {
            PatientBean patient = microservicePatientsProxy.getPatient(id);
            String msgSource = messageSource.getMessage("debug.patient.updateForm"
                    , null, LocaleContextHolder.getLocale());
            log.debug("HTTP GET, " + msgSource);
            model.addAttribute("patient", patient);
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
            return "redirect:"+gatewayUrl+"/patient/list";
        }
        return "patient/update";
    }

    /**
     * Update - Update an existing patient
     *
     * @param id Patient ID added
     * @param patient The patient object updated
     * @param result Result of a validation
     * @param model Model object
     * @param redirectAttributes RedirectAttributes object
     * @return View
     */
    @PostMapping("/patient/update/{id}")
    public String updatePatient(@PathVariable("id") Integer id
            , @Valid @ModelAttribute("patient") PatientBean patient
            , BindingResult result
            , Model model
            , RedirectAttributes redirectAttributes) {

        // Patient parameter is not valid
        if (result.hasErrors()) {
            String msgSource = messageSource.getMessage("debug.patient.validation"
                    , new Object[] { patient }
                    , LocaleContextHolder.getLocale());
            log.debug("HTTP PATCH, " + msgSource);
            return "patient/update";
        }
        try {
            // Modify patient
            microservicePatientsProxy.updatePatient(id, patient);
            String msgSource = messageSource.getMessage("info.patient.updated"
                    , null, LocaleContextHolder.getLocale());
            log.info("HTTP PATCH, " + msgSource);
            redirectAttributes.addFlashAttribute("successMessage", msgSource);
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
        }
        return "redirect:"+gatewayUrl+"/patient/list";
    }

    /**
     * Delete - Delete an patient
     *
     * @param id Patient ID deleted
     * @param model Model object
     * @param redirectAttributes RedirectAttributes object
     * @return View
     */
    @RequestMapping("/patient/delete/{id}")
    public String deletePatient(@PathVariable("id") Integer id
            , Model model
            , RedirectAttributes redirectAttributes) {

        try {
            // Delete notes
            microserviceNotesProxy.deleteNotesByPatientId(id);
            // Delete patient
            microservicePatientsProxy.deletePatient(id);
            String msgSource = messageSource.getMessage("info.patient.deleted"
                    , null, LocaleContextHolder.getLocale());
            log.info("HTTP DELETE, " + msgSource);
            redirectAttributes.addFlashAttribute("successMessage", msgSource);
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
        }
        return "redirect:"+gatewayUrl+"/patient/list";
    }
}
