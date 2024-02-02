package com.medilabo.mdiabetesrisks.business;

import com.medilabo.mdiabetesrisks.beans.NoteBean;
import com.medilabo.mdiabetesrisks.beans.PatientBean;
import com.medilabo.mdiabetesrisks.enumerator.DiabetesRiskLevel;
import com.medilabo.mdiabetesrisks.model.DiabetesRisk;
import com.medilabo.mdiabetesrisks.web.exceptions.DiabetesRiskNotFoundException;

import java.time.LocalDate;
import java.util.List;

/**
 * DiabeteRiskBusiness is interface is the diabetes risk processing service
 *
 * @author MC
 * @version 1.0
 */
public interface DiabetesRiskBusiness {
    /**
     * Get diabetes risk by Patient ID
     *
     * @param id Patient ID founded
     * @return Diabetes risk founded
     * @throws DiabetesRiskNotFoundException Exception
     */
    public DiabetesRisk diabetesRiskAssessmentByPatientId(Integer id);

    /**
     * Diabetes risk assessment
     *
     * @param patient Patient
     * @param notes List of observations notes
     * @return Diabetes risk level of patient
     */
    public DiabetesRiskLevel diabetesRiskAssessment(PatientBean patient,  List<NoteBean> notes);

    /**
     * Age of a person
     *
     * @param birthOfDate Date of birth
     * @return Age of person
     */
    public int agePerson(LocalDate birthOfDate);

    /**
     * Number of trigger terms in the observations notes list
     *
     * @param notes List of observations notes
     * @return Number of trigger terms
     */
    public int numberOfTriggerTerms(List<NoteBean> notes);
}
