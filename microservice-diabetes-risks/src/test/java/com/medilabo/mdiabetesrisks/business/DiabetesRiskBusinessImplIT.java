package com.medilabo.mdiabetesrisks.business;

import com.medilabo.mdiabetesrisks.beans.NoteBean;
import com.medilabo.mdiabetesrisks.beans.PatientBean;
import com.medilabo.mdiabetesrisks.enumerator.DiabetesRiskLevel;
import com.medilabo.mdiabetesrisks.enumerator.Gender;
import com.medilabo.mdiabetesrisks.enumerator.TriggerTerm;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * DiabetesRiskBusinessImplIT is a class of integration tests on DiabetesRiskBusiness
 *
 * @author MC
 * @version 1.0
 */
@ExtendWith(SpringExtension.class)
@SpringBootTest
public class DiabetesRiskBusinessImplIT {

    @Autowired
    private DiabetesRiskBusinessImpl diabetesRiskBusiness;

    PatientBean patient;
    List<NoteBean> notes;
    NoteBean note1;
    NoteBean note2;
    LocalDate currentDate = LocalDate.now();

    @BeforeEach
    public void setUpBefore() {
        patient = PatientBean.builder()
                .id(1)
                .firstName("Test")
                .lastName("TestNone")
                .birthOfDate(LocalDate.of(1966, 12, 31))
                .gender(Gender.F)
                .build();
        notes = new ArrayList<>();
        note1 = NoteBean.builder()
                .id("1")
                .patientId(1)
                .build();
        note2 = NoteBean.builder()
                .id("2")
                .patientId(1)
                .build();
    }

    // -----------------------------------------------------------------------------------------------
    // diabetesRiskAssessment method
    // -----------------------------------------------------------------------------------------------

    // ------------  None ------------
    // No triggers
    @Test
    void diabetesRiskAssessment_none() {
        // GIVEN
        note1.setObservationNote("note 1");
        notes.add(note1);
        // WHEN
        DiabetesRiskLevel result = diabetesRiskBusiness.diabetesRiskAssessment(patient, notes);
        // THEN
        assertThat(result).isEqualTo(DiabetesRiskLevel.NONE);
    }

    // ------------  Borderline ------------
    // Between two and five triggers and the patient is over 30 years old
    @Test
    void diabetesRiskAssessment_more30TriggerTwoFive_borderline() {
        // GIVEN
        patient.setBirthOfDate(currentDate.minusYears(31));
        note1.setObservationNote(TriggerTerm.ANORMAL + "  " + TriggerTerm.FUMEUR);
        notes.add(note1);
        // WHEN
        DiabetesRiskLevel result = diabetesRiskBusiness.diabetesRiskAssessment(patient, notes);
        // THEN
        assertThat(result).isEqualTo(DiabetesRiskLevel.BORDERLINE);
    }

    // ------------  In Danger ------------
    // If the patient is a male under 30 years old, then three trigger terms must be present
    @Test
    void diabetesRiskAssessment_maleUnder30TriggeMore3_inDanger() {
        // GIVEN
        patient.setBirthOfDate(currentDate.minusYears(29));
        patient.setGender(Gender.M);
        note1.setObservationNote(TriggerTerm.ANORMAL.getDescription() + "  " + TriggerTerm.FUMEUR.getDescription());
        notes.add(note1);
        note2.setObservationNote("" + TriggerTerm.ANTICORPS.getDescription());
        notes.add(note2);
        // WHEN
        DiabetesRiskLevel result = diabetesRiskBusiness.diabetesRiskAssessment(patient, notes);
        // THEN
        assertThat(result).isEqualTo(DiabetesRiskLevel.INDANGER);
    }

    // If the patient is female and under 30 years old, four trigger terms will be required
    @Test
    void diabetesRiskAssessment_femaleUnder30TriggeMore4_inDanger() {
        // GIVEN
        patient.setBirthOfDate(currentDate.minusYears(29));
        note1.setObservationNote(TriggerTerm.ANORMAL.getDescription() + "  " + TriggerTerm.FUMEUR.getDescription());
        notes.add(note1);
        note2.setObservationNote(TriggerTerm.ANTICORPS.getDescription()
                + " " + TriggerTerm.CHOLESTEROL.getDescription());
        notes.add(note2);
        // WHEN
        DiabetesRiskLevel result = diabetesRiskBusiness.diabetesRiskAssessment(patient, notes);
        // THEN
        assertThat(result).isEqualTo(DiabetesRiskLevel.INDANGER);
    }

    // If the patient is over 30 years old, then it will take six or seven
    @Test
    void diabetesRiskAssessment_more30TriggerSixSevan_inDanger() {
        // GIVEN
        patient.setBirthOfDate(currentDate.minusYears(31));
        note1.setObservationNote(TriggerTerm.HEMOGLOBINE_A1C.getDescription()
                + " " + TriggerTerm.MICROALBUMINE.getDescription()
                + " " + TriggerTerm.TAILLE.getDescription()
                + " " + TriggerTerm.POIDS.getDescription()
                + " " + TriggerTerm.FUMEUR.getDescription()
                + " " + TriggerTerm.FUMEUSE.getDescription()
                + " " + TriggerTerm.ANORMAL.getDescription()
        );
        notes.add(note1);
        // WHEN
        DiabetesRiskLevel result = diabetesRiskBusiness.diabetesRiskAssessment(patient, notes);
        // THEN
        assertThat(result).isEqualTo(DiabetesRiskLevel.INDANGER);
    }

    // ------------  Early onset ------------
    // If the patient is a male under 30, then at least five trigger terms are required
    @Test
    void diabetesRiskAssessment_maleUnder30TriggeUnder5_earlyOnset() {
        // GIVEN
        patient.setBirthOfDate(currentDate.minusYears(29));
        patient.setGender(Gender.M);
        note1.setObservationNote(TriggerTerm.HEMOGLOBINE_A1C.getDescription()
                + " " + TriggerTerm.MICROALBUMINE.getDescription()
                + " " + TriggerTerm.TAILLE.getDescription()
                + " " + TriggerTerm.POIDS.getDescription()
                + " " + TriggerTerm.ANORMAL.getDescription()
        );
        notes.add(note1);
        // WHEN
        DiabetesRiskLevel result = diabetesRiskBusiness.diabetesRiskAssessment(patient, notes);
        // THEN
        assertThat(result).isEqualTo(DiabetesRiskLevel.EARLYONSET);
    }

    // If the patient is female and under 30 years old, at least seven trigger terms will be required
    @Test
    void diabetesRiskAssessment_femaleUnder30TriggeMore7_earlyOnset() {
        // GIVEN
        patient.setBirthOfDate(currentDate.minusYears(29));
        note1.setObservationNote(TriggerTerm.ANORMAL.getDescription() + "  " + TriggerTerm.FUMEUR.getDescription());
        note1.setObservationNote(TriggerTerm.HEMOGLOBINE_A1C.getDescription()
                + " " + TriggerTerm.MICROALBUMINE.getDescription()
                + " " + TriggerTerm.TAILLE.getDescription()
                + " " + TriggerTerm.POIDS.getDescription()
                + " " + TriggerTerm.FUMEUR.getDescription()
                + " " + TriggerTerm.FUMEUSE.getDescription()
                + " " + TriggerTerm.ANORMAL.getDescription()
        );
        notes.add(note1);
        // WHEN
        DiabetesRiskLevel result = diabetesRiskBusiness.diabetesRiskAssessment(patient, notes);
        // THEN
        assertThat(result).isEqualTo(DiabetesRiskLevel.EARLYONSET);
    }

    // If the patient is over 30 years old, then eight or more will be needed
    @Test
    void diabetesRiskAssessment_more30TriggerMore8_earlyOnset() {
        // GIVEN
        patient.setBirthOfDate(currentDate.minusYears(31));
        note1.setObservationNote(TriggerTerm.HEMOGLOBINE_A1C.getDescription()
                + " " + TriggerTerm.MICROALBUMINE.getDescription()
                + " " + TriggerTerm.TAILLE.getDescription()
                + " " + TriggerTerm.POIDS.getDescription()
                + " " + TriggerTerm.FUMEUR.getDescription()
                + " " + TriggerTerm.FUMEUSE.getDescription()
                + " " + TriggerTerm.ANORMAL.getDescription()
                + " " + TriggerTerm.CHOLESTEROL.getDescription()
        );
        notes.add(note1);
        // WHEN
        DiabetesRiskLevel result = diabetesRiskBusiness.diabetesRiskAssessment(patient, notes);
        // THEN
        assertThat(result).isEqualTo(DiabetesRiskLevel.EARLYONSET);
    }
}
