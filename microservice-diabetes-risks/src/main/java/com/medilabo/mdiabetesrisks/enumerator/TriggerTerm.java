package com.medilabo.mdiabetesrisks.enumerator;

/**
 * TriggerTerm is Enumeration for trigger term
 *
 * @author MC
 * @version 1.0
 */
public enum TriggerTerm {
    HEMOGLOBINE_A1C("Hémoglobine A1C")
    , MICROALBUMINE("Microalbumine")
    , TAILLE("Taille")
    , POIDS("Poids")
    , FUMEUR("Fumeur")
    , FUMEUSE("Fumeuse")
    , ANORMAL("Anormal")
    , CHOLESTEROL("Cholestérol")
    , VERTIGES("Vertiges")
    , RECHUTE("Rechute")
    , REACTION("Réaction")
    , ANTICORPS("Anticorps");

    private final String description;

    private TriggerTerm(String description){
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
