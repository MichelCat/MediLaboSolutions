package com.medilabo.mdiabetesrisks.business;

import com.medilabo.mdiabetesrisks.beans.NoteBean;
import com.medilabo.mdiabetesrisks.beans.PatientBean;
import com.medilabo.mdiabetesrisks.enumerator.DiabetesRiskLevel;
import com.medilabo.mdiabetesrisks.enumerator.Gender;
import com.medilabo.mdiabetesrisks.enumerator.TriggerTerm;
import com.medilabo.mdiabetesrisks.model.DiabetesRisk;
import com.medilabo.mdiabetesrisks.proxies.MicroserviceNotesProxy;
import com.medilabo.mdiabetesrisks.proxies.MicroservicePatientsProxy;
import com.medilabo.mdiabetesrisks.web.exceptions.DiabetesRiskNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * DiabeteRiskBusiness is the diabetes risk processing service
 *
 * @author MC
 * @version 1.0
 */
@Slf4j
@Service
public class DiabetesRiskBusiness {

    @Autowired
    private MicroserviceNotesProxy microserviceNotesProxy;
    @Autowired
    private MicroservicePatientsProxy microservicePatientsProxy;
    @Autowired
    private MessageSource messageSource;

    /**
     * Get diabetes risk by Patient ID
     *
     * @param id Patient ID founded
     * @return Diabetes risk founded
     */
    public DiabetesRisk diabetesRiskAssessmentByPatientId(Integer id) {
//        Optional<PatientBean> optPatient = microservicePatientsProxy.getPatient(id);
//        if(!optPatient.isPresent()) {
//            throw new DiabetesRiskNotFoundException("This patient does not exist");
//        }
//        PatientBean patient = optPatient.get();
        PatientBean patient = microservicePatientsProxy.getPatient(id);

        List<NoteBean> notes = microserviceNotesProxy.getNotesByPatientId(id);
        DiabetesRiskLevel diabetesRiskLevel = diabetesRiskAssessment(patient, notes);

        DiabetesRisk diabetesRisk = new  DiabetesRisk();
        diabetesRisk.setPatientId(patient.getId());
        diabetesRisk.setDiabetesRiskAssessment(diabetesRiskLevel);
        return diabetesRisk;
    }

    /**
     * Diabetes risk assessment
     *
     * @param patient Patient
     * @param notes List of notes
     * @return Diabetes risk level of patient
     */
    public DiabetesRiskLevel diabetesRiskAssessment(PatientBean patient,  List<NoteBean> notes) {
        int patientAge = agePerson(patient.getBirthOfDate());
        int numberOfTriggerTerms = numberOfTriggerTerms(notes);

        if (patientAge <= 30) {
            switch (patient.getGender()) {
                case Gender.M:
                    if (numberOfTriggerTerms >= 5) {
                        return DiabetesRiskLevel.EARLYONSET;
                    } else if (numberOfTriggerTerms >= 3) {
                        return DiabetesRiskLevel.INDANGER;
                    }
                    break;
                default:
                    if (numberOfTriggerTerms >= 7) {
                        return DiabetesRiskLevel.EARLYONSET;
                    } else if (numberOfTriggerTerms >= 4) {
                        return DiabetesRiskLevel.INDANGER;
                    }
                    break;
            }
        } else {
            if (numberOfTriggerTerms >= 8) {
                return DiabetesRiskLevel.EARLYONSET;
            } else if (numberOfTriggerTerms >= 6) {
                return DiabetesRiskLevel.INDANGER;
            } else if (numberOfTriggerTerms >= 2) {
                return DiabetesRiskLevel.BORDERLINE;
            }
        }
        return DiabetesRiskLevel.NONE;
    }

    /**
     * Age of a person
     *
     * @param birthOfDate Date of birth
     * @return Age of person
     */
    public int agePerson(LocalDate birthOfDate) {
        return Period.between(birthOfDate, LocalDate.now()).getYears();
    }

    /**
     * Number of trigger terms in the notes list
     *
     * @param notes List of notes
     * @return Number of trigger terms
     */
    public int numberOfTriggerTerms(List<NoteBean> notes) {
        if (notes.isEmpty()) {
            return 0;
        }
        List<String> triggerTerms = Arrays.stream(TriggerTerm.values())
                .map(val -> StringUtils.stripAccents(val.getDescription()).toUpperCase())
                .toList();

        List<String> uppercaseNotes = notes.stream()
                .map(note -> StringUtils.stripAccents(note.getNote()).toUpperCase())
                .toList();

        int numberOfTerms = 0;
        for(String term : triggerTerms) {
            if (uppercaseNotes.stream().anyMatch(note -> note.contains(term))) {
                numberOfTerms++;
            }
        }
        return numberOfTerms;
    }
}
