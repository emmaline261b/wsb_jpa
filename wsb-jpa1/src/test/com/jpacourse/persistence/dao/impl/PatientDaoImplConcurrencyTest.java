package com.jpacourse.persistence.dao.impl;

import com.jpacourse.persistence.dao.PatientDao;
import com.jpacourse.persistence.entity.PatientEntity;
import org.hibernate.OptimisticLockException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest
public class PatientDaoImplConcurrencyTest {

    @Autowired
    private PatientDao patientDao;

    @Test
    public void shouldThrowOptimisticLockException() {
        // Given
        PatientEntity wisniewskaBozenkaVersion = patientDao.getOneEager(1L);
        PatientEntity wisniewskaGrazynkaVersion = patientDao.getOneEager(1L);

        // When
        wisniewskaBozenkaVersion.setEmail("bozenka@mail.com");
        patientDao.merge(wisniewskaBozenkaVersion);

        // Odświeżam wersję bożenki, żeby sprawdzić, że wersja się podbiła
        wisniewskaBozenkaVersion = patientDao.getOneEager(1L);

        wisniewskaGrazynkaVersion.setEmail("grazynka@mail.com");

        // Then
        assertThatThrownBy(() -> patientDao.merge(wisniewskaGrazynkaVersion))
                .isInstanceOfAny(OptimisticLockException.class, org.springframework.orm.ObjectOptimisticLockingFailureException.class);
    }
}
