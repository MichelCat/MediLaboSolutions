package com.medilabo.mfrontend.beans;

import com.medilabo.mfrontend.enumerator.DiabetesRiskLevel;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.FieldDefaults;

/**
 * DiabetesRisk
 */
@Getter
@Setter
@NoArgsConstructor
@FieldDefaults(level= AccessLevel.PRIVATE)
@ToString
public class DiabetesRiskBean {
    /**
     * Patient ID
     */
    @NotNull
    int patientId;

    /**
     * Diabetes risk assessment
     */
    DiabetesRiskLevel diabetesRiskAssessment;
}
