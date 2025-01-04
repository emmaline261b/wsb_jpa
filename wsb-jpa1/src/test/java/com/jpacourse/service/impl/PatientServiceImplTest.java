package com.jpacourse.service.impl;


import com.jpacourse.dto.PatientTO;
import com.jpacourse.persistence.dao.PatientDao;
import com.jpacourse.persistence.entity.PatientEntity;

import com.jpacourse.persistence.enums.Pronoun;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class PatientServiceImplTest {


    @Mock
    private PatientDao patientDao;

    @InjectMocks
    private PatientServiceImpl patientService;

    @Test
    public void testShouldFindPatientById() {
        // given
        Long patientId = 1L;
        PatientEntity patientEntity = new PatientEntity();
        patientEntity.setId(patientId);
        patientEntity.setFirstName("John");
        patientEntity.setLastName("Doe");
        patientEntity.setPronoun(Pronoun.HE);
        when(patientDao.findOne(patientId)).thenReturn(patientEntity);

        // when
        PatientTO result = patientService.findById(patientId);

        // then
        assertThat(result).isNotNull();
        assertThat(result.getId()).isEqualTo(patientId);
        assertThat(result.getFirstName()).isEqualTo("John");
        verify(patientDao, times(1)).findOne(patientId);
    }

    @Test
    public void testShouldReturnNullWhenPatientNotFound() {
        // given
        Long patientId = 1L;
        when(patientDao.findOne(patientId)).thenReturn(null);

        // when
        PatientTO result = patientService.findById(patientId);

        // then
        assertThat(result).isNull();
        verify(patientDao, times(1)).findOne(patientId);
    }

    @Test
    public void testShouldSavePatient() {
        // given
        PatientEntity patientEntity = new PatientEntity();
        patientEntity.setFirstName("John");
        patientEntity.setLastName("Doe");
        when(patientDao.save(patientEntity)).thenReturn(patientEntity);

        // when
        patientDao.save(patientEntity);

        // then
        verify(patientDao, times(1)).save(patientEntity);
    }

}