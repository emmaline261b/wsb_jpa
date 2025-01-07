package com.jpacourse.service.impl;

import com.jpacourse.dto.PatientTO;
import com.jpacourse.dto.VisitTO;
import com.jpacourse.persistence.dao.DoctorDao;
import com.jpacourse.persistence.dao.PatientDao;
import com.jpacourse.persistence.dao.VisitDao;
import com.jpacourse.persistence.entity.DoctorEntity;
import com.jpacourse.persistence.entity.PatientEntity;
import com.jpacourse.persistence.entity.VisitEntity;
import com.jpacourse.persistence.enums.Pronoun;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;


@SpringBootTest
@Transactional
public class PatientServiceImplIntegrationTest {

    @Autowired
    private PatientServiceImpl patientService;

    @Autowired
    private PatientDao patientDao;

    @Autowired
    private VisitDao visitDao;

    @Autowired
    private DoctorDao doctorDao;


    @Test
    public void shouldCascadeDeleteVisitsWhenDeletingPatient() {
        // Given
        Long patientId = 1L;
        PatientEntity patient = patientDao.findOne(patientId);
        List<DoctorEntity> doctorsBeforeDeletion = doctorDao.findAll();

        // When
        patientDao.delete(patient);

        // Then
        List<VisitEntity> visitsAfterDeletion = visitDao.findAll();
        List<DoctorEntity> doctorsAfterDeletion = doctorDao.findAll();

        assertThat(visitsAfterDeletion).doesNotContainAnyElementsOf(patient.getVisits());
        assertThat(doctorsAfterDeletion).containsExactlyElementsOf(doctorsBeforeDeletion);
    }

    @Test
    public void shouldReturnCorrectPatientTOById() {
        // Given
        Long patientId = 1L;

        // When
        PatientTO patientTO = patientService.findById(patientId);

        // Then
        assertThat(patientTO).isNotNull();
        assertThat(patientTO.getId()).isEqualTo(patientId);
        assertThat(patientTO.getFirstName()).isEqualTo("Maria");
        assertThat(patientTO.getLastName()).isEqualTo("Wiśniewska");
        assertThat(patientTO.getPronoun()).isEqualTo(Pronoun.SHE);


        // Sprawdzanie wizyt
        assertThat(patientTO.getVisits()).isNotEmpty();
        patientTO.getVisits().forEach(visit -> {
            assertThat(visit.getTime()).isNotNull();
            assertThat(visit.getDoctorFirstName()).isNotEmpty();
            assertThat(visit.getDoctorLastName()).isNotEmpty();
            assertThat(visit.getTreatmentTypes()).isNotEmpty();
        });
    }

    @Test
    public void shouldReturnPatientVisits() {
        // Given
        Long patientId = 1L;

        // When
        List<VisitTO> visits = patientService.findVisits(patientId);

        // Then
        assertThat(visits).hasSize(1);
        VisitTO wisniewskaVisit = visits.get(0);
        assertThat(wisniewskaVisit.getDescription()).isEqualTo("Konsultacja chirurgiczna");
        assertThat(wisniewskaVisit.getTime()).isEqualTo(LocalDateTime.parse("2024-12-05T09:00:00"));
        assertThat(wisniewskaVisit.getDoctorFirstName()).isEqualTo("Jan");
        assertThat(wisniewskaVisit.getDoctorLastName()).isEqualTo("Kowalski");
        assertThat(wisniewskaVisit.getTreatmentTypes()).containsExactlyInAnyOrder("USG");
    }
}
