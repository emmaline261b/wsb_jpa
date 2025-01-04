package com.jpacourse.persistance.dao;

import com.jpacourse.persistence.dao.PatientDao;
import com.jpacourse.persistence.dao.DoctorDao;
import com.jpacourse.persistence.entity.PatientEntity;
import com.jpacourse.persistence.entity.DoctorEntity;
import com.jpacourse.persistence.entity.VisitEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;


@SpringBootTest
@Transactional
public class PatientDaoTest {

    @Autowired
    private PatientDao patientDao;

    @Autowired
    private DoctorDao doctorDao;

    @Test
    public void shouldAddVisitForPatient() {
        // Given
        Long patientId = 1L;
        Long doctorId = 1L;
        LocalDateTime visitDate = LocalDateTime.of(2025, 1, 5, 9, 20);
        String visitDescription = "Wizyta kontrolna";

        // When
        patientDao.addVisitForPatient(patientId, doctorId, visitDate, visitDescription);

        // Then
        PatientEntity patient = patientDao.findOne(patientId);
        assertThat(patient).isNotNull();
        assertThat(patient.getVisits()).isNotEmpty();

        VisitEntity visit = patient.getVisits()
                .stream()
                .filter(x -> Objects.equals(x.getTime(), visitDate))
                .collect(Collectors.toList()).get(0);

        assertThat(visit.getTime().withNano(0)).isEqualTo(visitDate.withNano(0));
        assertThat(visit.getDescription()).isEqualTo(visitDescription);
        assertThat(visit.getDoctor().getId()).isEqualTo(doctorId);
        assertThat(visit.getPatient().getId()).isEqualTo(patientId);

        DoctorEntity doctor = doctorDao.findOne(doctorId);
        assertThat(doctor).isNotNull();
        assertThat(doctorDao.getAllVisits(doctorId)).contains(visit);
    }
}
