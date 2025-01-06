package com.jpacourse.persistence.dao.impl;

import com.jpacourse.persistence.dao.DoctorDao;
import com.jpacourse.persistence.dao.PatientDao;
import com.jpacourse.persistence.entity.DoctorEntity;
import com.jpacourse.persistence.entity.PatientEntity;
import com.jpacourse.persistence.entity.VisitEntity;
import com.jpacourse.persistence.enums.Pronoun;
import com.jpacourse.rest.exception.EntityNotFoundException;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Repository
public class PatientDaoImpl extends AbstractDao<PatientEntity, Long> implements PatientDao {

    private final DoctorDao doctorDao;

    public PatientDaoImpl(DoctorDao doctorDao) {
        this.doctorDao = doctorDao;
    }


    @Override
    public void addVisitForPatient(Long patientId, Long doctorId, LocalDateTime visitDate, String visitDescription) {

        PatientEntity patient = findOne(patientId);
        if (patient == null) {
            throw new EntityNotFoundException(patientId);
        }

        DoctorEntity doctor = doctorDao.findOne(doctorId);
        if (doctor == null) {
            throw new EntityNotFoundException(doctorId);
        }

        VisitEntity newVisit = new VisitEntity();
        newVisit.setTime(visitDate);
        newVisit.setDescription(visitDescription);
        newVisit.setDoctor(doctor);
        newVisit.setPatient(patient);

        patient.getVisits().add(newVisit);
        update(patient);
    }

    public List<PatientEntity> findByLastName(String lastName) {
        return entityManager.createQuery(
                        "select patient from PatientEntity patient " +
                                "where patient.lastName = :patientLastName",
                        PatientEntity.class)
                .setParameter("patientLastName", lastName).getResultList();
    }


    @Override
    public List<PatientEntity> findWithNumberOfVisitsGreaterThan(Long numberOfVisits) {
        return entityManager.createQuery(
                        "SELECT patient from PatientEntity patient " +
                                "JOIN patient.visits visit " +
                                "GROUP BY patient " +
                                "HAVING COUNT(visit) > :numberOfVisits",
                        PatientEntity.class)
                .setParameter("numberOfVisits", numberOfVisits)
                .getResultList();
    }

    @Override
    public List<PatientEntity> findByPronouns(Set<Pronoun> pronouns) {
        return entityManager.createQuery(
                "SELECT patient FROM PatientEntity patient " +
                        "WHERE patient.pronoun IN :pronouns", PatientEntity.class)
                .setParameter("pronouns", pronouns).getResultList();
    }

}
