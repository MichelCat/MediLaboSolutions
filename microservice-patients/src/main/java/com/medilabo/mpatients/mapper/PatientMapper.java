package com.medilabo.mpatients.mapper;

import com.medilabo.mpatients.dao.db.entities.PatientEntity;
import com.medilabo.mpatients.model.Patient;
import jakarta.validation.constraints.Null;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.sql.Timestamp;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.util.List;

/**
 * PatientMapper is the mapper Patient / PatientEntity
 *
 * @author MC
 * @version 1.0
 */
@Mapper(componentModel = "spring")
public interface PatientMapper {
    /**
     * Mapper Patient to PatientEntity
     *
     * @param patient Patient object
     * @return PatientEntity
     */
    @Mapping(source = "createDate", target = "createDate", qualifiedByName = "offsetDateTimeToTimestamp")
    @Mapping(source = "updateDate", target = "updateDate", qualifiedByName = "offsetDateTimeToTimestamp")
    PatientEntity daoToEntity(Patient patient);

    /**
     * Mapper List Patient to List PatientEntity
     *
     * @param patients List Patient object
     * @return List PatientEntity
     */
    List<PatientEntity> daoToEntities(List<Patient> patients);

    /**
     * Mapper PatientEntity to Patient
     *
     * @param patientEntity PatientEntity object
     * @return Patient
     */
    @Mapping(source = "createDate", target = "createDate", qualifiedByName = "timestampToOffsetDateTime")
    @Mapping(source = "updateDate", target = "updateDate", qualifiedByName = "timestampToOffsetDateTime")
    Patient entityToDao(PatientEntity patientEntity);

    /**
     * Mapper List PatientEntity to List Patient
     *
     * @param patientEntities List PatientEntity object
     * @return List Patient
     */
    List<Patient> entitiesToDao(List<PatientEntity> patientEntities);

    /**
     * OffsetDateTime to Timestamp conversion
     *
     * @param offsetDateTime OffsetDateTime variable
     * @return Timestamp variable
     */
    @Named("offsetDateTimeToTimestamp")
    default Timestamp toTimestamp(OffsetDateTime offsetDateTime) {
        if (offsetDateTime == null) {
            return null;
        }
        return Timestamp.from(offsetDateTime.toInstant());
    }

    /**
     * Timestamp to OffsetDateTime conversion
     *
     * @param timestamp Timestamp variable
     * @return OffsetDateTime variable
     */
    @Named("timestampToOffsetDateTime")
    default OffsetDateTime toDate(Timestamp timestamp) {
        if (timestamp == null) {
            return null;
        }
        return OffsetDateTime.ofInstant(timestamp.toInstant(), ZoneId.systemDefault());
    }
}
