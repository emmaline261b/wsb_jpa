package com.jpacourse.service.impl;

import com.jpacourse.persistence.dao.DoctorDao;
import com.jpacourse.persistence.dao.PatientDao;
import com.jpacourse.persistence.dao.VisitDao;
import com.jpacourse.persistence.entity.DoctorEntity;
import com.jpacourse.persistence.entity.PatientEntity;
import com.jpacourse.persistence.entity.VisitEntity;
import com.jpacourse.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import java.util.List;


@SpringBootTest
@Transactional
public class PatientServiceImplIntegrationTest {

    @Autowired
    private PatientService patientService;

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
//        List<VisitEntity> visitsBeforeDeletion = visitDao.findAll();
        List<DoctorEntity> doctorsBeforeDeletion = doctorDao.findAll();

        // When
        patientDao.delete(patient);

        // Then
        List<VisitEntity> visitsAfterDeletion = visitDao.findAll();
        List<DoctorEntity> doctorsAfterDeletion = doctorDao.findAll();

        assertThat(visitsAfterDeletion).doesNotContainAnyElementsOf(patient.getVisits());
        assertThat(doctorsAfterDeletion).containsExactlyElementsOf(doctorsBeforeDeletion);
    }
}
