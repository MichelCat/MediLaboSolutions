package com.medilabo.mfrontend.controller;

import com.medilabo.mfrontend.beans.DiabetesRiskBean;
import com.medilabo.mfrontend.beans.NoteBean;
import com.medilabo.mfrontend.beans.PatientBean;
import com.medilabo.mfrontend.proxies.MicroserviceDiabetesRisksProxy;
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

import java.util.List;

/**
 * NoteController is the Endpoint will perform the following actions via Get/Post/Put/Delete with HTTP on notes.
 *
 * @author MC
 * @version 1.0
 */
@Slf4j
@Controller
@RequestMapping("note")
public class NoteController {

    @Autowired
    private MicroserviceNotesProxy microserviceNotesProxy;
    @Autowired
    private MicroservicePatientsProxy microservicePatientsProxy;
    @Autowired
    private MicroserviceDiabetesRisksProxy microserviceDiabetesRisksProxy;
    @Autowired
    private MessageSource messageSource;
    @Value("${medilabo.front.redirect.url}")
    private String gatewayUrl;

    /**
     * Read - Get notes for patient id
     *
     * @param id Note ID added
     * @param model Model object
     * @return View
     */
    @GetMapping("/list/{id}")
    public String home(@PathVariable("id") Integer id
                          , Model model){
        String msgSource = messageSource.getMessage("debug.note.listForm"
                , null, LocaleContextHolder.getLocale());
        log.debug("HTTP GET, " + msgSource);
        PatientBean patientBean = microservicePatientsProxy.getPatient(id);
        if (patientBean == null) {
            return "redirect:"+gatewayUrl+"/patient/list";
        }
        List<NoteBean> notes = microserviceNotesProxy.getNotesByPatientId(id);
        if (notes == null) {
            return "redirect:"+gatewayUrl+"/patient/list";
        }
        DiabetesRiskBean diabetesRisk = microserviceDiabetesRisksProxy.getDiabetesRiskByPatientId(id);

        model.addAttribute("patient", patientBean);
        model.addAttribute("notes", notes);
        model.addAttribute("diabetesRisk", diabetesRisk);
        return "note/list";
    }

    /**
     * Read - Page add new note.
     *
     * @param id Patient ID added
     * @param model Model object
     * @return View
     */
    @GetMapping("/add/{id}")
    public String addNoteForm(@PathVariable("id") Integer id
                              , Model model) {
        String msgSource = messageSource.getMessage("debug.note.addForm"
                , null, LocaleContextHolder.getLocale());
        log.debug("HTTP GET, " + msgSource);
        NoteBean note = new NoteBean();
        note.setPatientId(id);
        model.addAttribute("note", note);
        return "note/add";
    }

    /**
     * Create - Add new Note
     *
     * @param note The note object added
     * @param result Result of a validation
     * @param model Model object
     * @param redirectAttributes RedirectAttributes object
     * @return View
     */
    @PostMapping("/validate")
    public String validate(@Valid @ModelAttribute("note") NoteBean note
            , BindingResult result
            , Model model
            , RedirectAttributes redirectAttributes) {

        // Note parameter is not valid
        if (result.hasErrors()) {
            String msgSource = messageSource.getMessage("debug.note.validation"
                    , new Object[] { note }
                    , LocaleContextHolder.getLocale());
            log.debug("HTTP POST, " + msgSource);
            return "note/add";
        }
        try {
            // Save note
            microserviceNotesProxy.addNote(note);
            String msgSource = messageSource.getMessage("info.note.created"
                    , null, LocaleContextHolder.getLocale());
            log.info("HTTP POST, " + msgSource);
            redirectAttributes.addFlashAttribute("successMessage", msgSource);
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
        }
        return "redirect:"+gatewayUrl+"/note/list/" + note.getPatientId();
    }

    /**
     * Read - Page add new note.
     *
     * @param id Note ID added
     * @param model Model object
     * @param redirectAttributes RedirectAttributes object
     * @return View
     */
    @GetMapping("/update/{id}")
    public String showUpdateForm(@PathVariable("id") String id
            , Model model
            , RedirectAttributes redirectAttributes) {
        Integer patientId = 0;

        try {
            NoteBean note = microserviceNotesProxy.getNote(id);
            patientId = note.getPatientId();
            String msgSource = messageSource.getMessage("debug.note.updateForm"
                    , null, LocaleContextHolder.getLocale());
            log.debug("HTTP GET, " + msgSource);
            model.addAttribute("note", note);
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
            return "redirect:"+gatewayUrl+"/note/list/" + patientId;
        }
        return "note/update";
    }

    /**
     * Update - Update an existing note
     *
     * @param id Note ID added
     * @param note The note object updated
     * @param result Result of a validation
     * @param model Model object
     * @param redirectAttributes RedirectAttributes object
     * @return View
     */
    @PostMapping("/update/{id}")
    public String updateNote(@PathVariable("id") String id
            , @Valid @ModelAttribute("note") NoteBean note
            , BindingResult result
            , Model model
            , RedirectAttributes redirectAttributes) {

        // Note parameter is not valid
        if (result.hasErrors()) {
            String msgSource = messageSource.getMessage("debug.note.validation"
                    , new Object[] { note }
                    , LocaleContextHolder.getLocale());
            log.debug("HTTP PATCH, " + msgSource);
            return "note/update";
        }
        try {
            // Modify note
            microserviceNotesProxy.updateNote(id, note);
            String msgSource = messageSource.getMessage("info.note.updated"
                    , null, LocaleContextHolder.getLocale());
            log.info("HTTP PATCH, " + msgSource);
            redirectAttributes.addFlashAttribute("successMessage", msgSource);
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
        }
        return "redirect:"+gatewayUrl+"/note/list/" + note.getPatientId();
    }

    /**
     * Delete - Delete an note
     *
     * @param id Note ID deleted
     * @param model Model object
     * @param redirectAttributes RedirectAttributes object
     * @return View
     */
    @RequestMapping("/delete/{id}")
    public String deleteNote(@PathVariable("id") String id
            , Model model
            , RedirectAttributes redirectAttributes) {
        Integer patientId = 0;

        try {
            NoteBean note = microserviceNotesProxy.getNote(id);
            patientId = note.getPatientId();

            // Delete note
            microserviceNotesProxy.deleteNote(id);
            String msgSource = messageSource.getMessage("info.note.deleted"
                    , null, LocaleContextHolder.getLocale());
            log.info("HTTP DELETE, " + msgSource);
            redirectAttributes.addFlashAttribute("successMessage", msgSource);
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
        }
        return "redirect:"+gatewayUrl+"/note/list/" + patientId;
    }
}
