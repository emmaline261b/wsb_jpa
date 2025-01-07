package com.jpacourse.persistence.dao.impl;


import com.jpacourse.persistence.dao.PatientDao;
import com.jpacourse.persistence.entity.PatientEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

@SpringBootTest
public class PatientDaoImplConcurrencyTest {

    @Autowired
    private PatientDao patientDao;

    @Autowired
    private EntityManagerFactory entityManagerFactory;

//    @Autowired
//    private EntityManager entityManager;

//    @BeforeEach
//    private void setUp() {
//        sessionFactory =
//    }

    @Test
    public void shouldThrowExceptionOnConcurrentSaveNoTransaction() {
        PatientEntity wisniewskaBozenkaVersion = patientDao.getOne(1L);
        PatientEntity wisniewskaGrazynkaVersion = patientDao.getOne(1L);
        wisniewskaBozenkaVersion.setEmail("bozenka@mail.com");
        wisniewskaGrazynkaVersion.setEmail("grazynka@mail.com");

        patientDao.save(wisniewskaBozenkaVersion);

        patientDao.save(wisniewskaGrazynkaVersion);
    }

    @Test
    @Transactional
    public void shouldThrowExceptionOnConcurrentSaveTransactional() {
        PatientEntity wisniewskaBozenkaVersion = patientDao.getOne(1L);
        PatientEntity wisniewskaGrazynkaVersion = patientDao.getOne(1L);
        wisniewskaBozenkaVersion.setEmail("bozenka@mail.com");
        wisniewskaGrazynkaVersion.setEmail("grazynka@mail.com");

        patientDao.save(wisniewskaBozenkaVersion);

        patientDao.save(wisniewskaGrazynkaVersion);
    }

    @Test
    public void shouldThrowExceptionOnConcurrentSaveUsingThreadsNoDao() throws InterruptedException {
        Thread bozenkaModifications = new Thread(() -> {
            EntityManager entityManager = entityManagerFactory.createEntityManager();
            entityManager.getTransaction().begin();

            PatientEntity wisniewskaBozenkaVersion = entityManager.getReference(PatientEntity.class, 1L);
            wisniewskaBozenkaVersion.setEmail("bozenka@email.com");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            entityManager.persist(wisniewskaBozenkaVersion);

            entityManager.getTransaction().commit();
            entityManager.close();
        });
        Thread grazynkaModifications = new Thread(() -> {

            EntityManager entityManager = entityManagerFactory.createEntityManager();
            entityManager.getTransaction().begin();

            PatientEntity wisniewskaGrazynkaVersion = entityManager.getReference(PatientEntity.class, 1L);
            wisniewskaGrazynkaVersion.setEmail("grazynka@email.com");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            entityManager.persist(wisniewskaGrazynkaVersion);

            entityManager.getTransaction().commit();
            entityManager.close();
        });
        bozenkaModifications.start();
        grazynkaModifications.start();
        bozenkaModifications.join();
        grazynkaModifications.join();

    }

    @Test
    public void shouldThrowExceptionOnConcurrentSaveUsingThreadsUsingDao() throws InterruptedException {
        Thread bozenkaModifications = new Thread(() -> {
            EntityManager entityManager = entityManagerFactory.createEntityManager();
            entityManager.getTransaction().begin();

            PatientEntity wisniewskaBozenkaVersion = patientDao.getOne(1L);
            wisniewskaBozenkaVersion.setEmail("bozenka@email.com");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            patientDao.save(wisniewskaBozenkaVersion);

            entityManager.getTransaction().commit();
            entityManager.close();
        });
        Thread grazynkaModifications = new Thread(() -> {
            EntityManager entityManager = entityManagerFactory.createEntityManager();
            entityManager.getTransaction().begin();

            PatientEntity wisniewskaGrazynkaVersion = patientDao.getOne(1L);
            wisniewskaGrazynkaVersion.setEmail("grazynka@email.com");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            entityManager.persist(wisniewskaGrazynkaVersion);
            patientDao.save(wisniewskaGrazynkaVersion);

            entityManager.getTransaction().commit();
            entityManager.close();
        });
        bozenkaModifications.start();
        grazynkaModifications.start();
        bozenkaModifications.join();
        grazynkaModifications.join();

    }

}
