package com.jpacourse.persistance.dao;

import com.jpacourse.persistence.dao.PatientDao;
import com.jpacourse.persistence.dao.DoctorDao;
import com.jpacourse.persistence.entity.PatientEntity;
import com.jpacourse.persistence.entity.DoctorEntity;
import com.jpacourse.persistence.entity.VisitEntity;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PatientDaoTest {

    @Autowired
    private PatientDao patientDao;

    @Autowired
    private DoctorDao doctorDao;

    @Transactional
    @Test
    public void shouldAddVisitForPatient() {
        // Given
        Long patientId = 1L;
        Long doctorId = 1L;
        LocalDateTime visitDate = LocalDateTime.now().plusDays(1);
        String visitDescription = "Wizyta kontrolna";

        // When
        patientDao.addVisitForPatient(patientId, doctorId, visitDate, visitDescription);

        // Then
        PatientEntity patient = patientDao.findOne(patientId);
        assertThat(patient).isNotNull();
        assertThat(patient.getVisits()).isNotEmpty();

        VisitEntity visit = patient.getVisits().get(0);

        assertThat(visit.getTime()).isEqualTo(visitDate);
        assertThat(visit.getDescription()).isEqualTo(visitDescription);
        assertThat(visit.getDoctor().getId()).isEqualTo(doctorId);
        assertThat(visit.getPatient().getId()).isEqualTo(patientId);

        DoctorEntity doctor = doctorDao.findOne(doctorId);
        assertThat(doctor).isNotNull();
        assertThat(doctorDao.getAllVisits(doctorId)).contains(visit);
    }
}
