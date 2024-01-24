package com.medilabo.mdiabetesrisks.model;

import com.medilabo.mdiabetesrisks.enumerator.DiabetesRiskLevel;
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
public class DiabetesRisk {
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
