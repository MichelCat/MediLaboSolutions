package com.medilabo.mdiabetesrisks.enumerator;

/**
 * DiabetesRiskLevel is Enumeration for diabetes risk level
 *
 * @author MC
 * @version 1.0
 */
public enum DiabetesRiskLevel {
    NONE("None")
    , BORDERLINE("Borderline")
    , INDANGER("InDanger")
    , EARLYONSET("EarlyOnset");

    private final String description;

    private DiabetesRiskLevel(String description){
        this.description = description;
    }
}
