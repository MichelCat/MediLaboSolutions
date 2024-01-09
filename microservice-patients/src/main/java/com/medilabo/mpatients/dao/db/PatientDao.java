package com.medilabo.mpatients.dao.db;

import com.medilabo.mpatients.dao.db.entities.PatientEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

/**
 * PatientDao is the interface that manages PatientEntity
 *
 * @author MC
 * @version 1.0
 */
@Repository
public interface PatientDao extends JpaRepository<PatientEntity, Integer> {
    boolean existsByFirstNameAndLastNameAndBirthOfDate(String firstName, String lastName, LocalDate birthOfDate);
}
