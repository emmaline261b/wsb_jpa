package com.jpacourse.persistence.dao.impl;


import com.jpacourse.persistence.dao.PatientDao;
import com.jpacourse.persistence.dao.DoctorDao;
import com.jpacourse.persistence.dao.VisitDao;
import com.jpacourse.persistence.entity.PatientEntity;
import com.jpacourse.persistence.entity.DoctorEntity;
import com.jpacourse.persistence.entity.VisitEntity;
import com.jpacourse.persistence.enums.Pronoun;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@SpringBootTest
@Transactional
public class PatientDaoImplTest {

    @Autowired
    private PatientDao patientDao;

    @Autowired
    private DoctorDao doctorDao;

    @Autowired
    private VisitDao visitDao;

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

    @Test
    public void shouldFindPatientByLastName() {
        // When
        List<PatientEntity> wisniewskaList = patientDao.findByLastName("Wiśniewska");

        // Then
        assertThat(wisniewskaList).hasSize(1);
        PatientEntity wisniewska = wisniewskaList.get(0);
        assertThat(wisniewska.getLastName()).isEqualTo("Wiśniewska");
    }

    @Test
    public void shouldNotFindPatientsWithMoreThan1Visits() {
        // When
        List<PatientEntity> patientsWithNumberOfVisitsGreaterThan = patientDao.findWithNumberOfVisitsGreaterThan(1L);

        // Then
        assertThat(patientsWithNumberOfVisitsGreaterThan).hasSize(0);
    }

    @Test
    public void shouldFindPatientsWithMoreThan1Visits() {
        // Given
        PatientEntity wisniewska = patientDao.getOne(1L);
        DoctorEntity kowalski = doctorDao.getOne(1L);
        VisitEntity visit = new VisitEntity();
        visit.setPatient(wisniewska);
        visit.setDoctor(kowalski);
        visit.setTime(LocalDateTime.now());
        visit.setDescription("Konsultacja kardiologiczna");
        visitDao.save(visit);

        // When
        List<PatientEntity> patientsWithNumberOfVisitsGreaterThan = patientDao.findWithNumberOfVisitsGreaterThan(1L);

        // Then
        assertThat(patientsWithNumberOfVisitsGreaterThan).hasSize(1);
        assertThat(patientsWithNumberOfVisitsGreaterThan.get(0).getId()).isEqualTo(1L);
    }

    @Test
    public void shouldFindByPronouns() {
        // Given
        PatientEntity wisniewska = patientDao.getOne(1L);
        PatientEntity zielinski = patientDao.getOne(2L);

        // When
        List<PatientEntity> shePatients = patientDao.findByPronouns(Set.of(Pronoun.SHE));
        List<PatientEntity> hePatients = patientDao.findByPronouns(Set.of(Pronoun.HE));
        List<PatientEntity> heShePatients = patientDao.findByPronouns(Set.of(Pronoun.HE, Pronoun.SHE));
        List<PatientEntity> theyPatients = patientDao.findByPronouns(Set.of(Pronoun.THEY));

        // Then
        assertThat(shePatients).containsExactly(wisniewska);
        assertThat(hePatients).containsExactly(zielinski);
        assertThat(heShePatients).containsExactlyInAnyOrder(wisniewska, zielinski);
        assertThat(theyPatients).isEmpty();
    }
}
