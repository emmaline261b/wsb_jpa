package com.jpacourse.persistence.dao;

import com.jpacourse.persistence.entity.PatientEntity;
import com.jpacourse.persistence.enums.Pronoun;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

public interface PatientDao extends Dao<PatientEntity, Long>
{

    void addVisitForPatient(Long patientId,
                            Long doctorId,
                            LocalDateTime visitDate,
                            String visitDescription);

    List<PatientEntity> findByLastName(String lastName);

    List<PatientEntity> findWithNumberOfVisitsGreaterThan(Long numberOfVisits);

    List<PatientEntity> findByPronouns(Set<Pronoun> pronouns);
}
