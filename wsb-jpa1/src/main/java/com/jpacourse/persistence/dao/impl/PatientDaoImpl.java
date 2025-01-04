package com.jpacourse.persistence.dao.impl;

import com.jpacourse.persistence.dao.DoctorDao;
import com.jpacourse.persistence.dao.PatientDao;
import com.jpacourse.persistence.entity.DoctorEntity;
import com.jpacourse.persistence.entity.PatientEntity;
import com.jpacourse.persistence.entity.VisitEntity;
import com.jpacourse.rest.exception.EntityNotFoundException;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public class PatientDaoImpl extends AbstractDao<PatientEntity, Long> implements PatientDao
{

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


}
