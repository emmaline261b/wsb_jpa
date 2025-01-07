package com.jpacourse.service.impl;

import com.jpacourse.dto.AddressTO;
import com.jpacourse.dto.PatientTO;
import com.jpacourse.dto.VisitTO;
import com.jpacourse.mapper.AddressMapper;
import com.jpacourse.mapper.PatientMapper;
import com.jpacourse.mapper.VisitMapper;
import com.jpacourse.persistence.dao.AddressDao;
import com.jpacourse.persistence.dao.PatientDao;
import com.jpacourse.persistence.entity.AddressEntity;
import com.jpacourse.persistence.entity.PatientEntity;
import com.jpacourse.persistence.entity.VisitEntity;
import com.jpacourse.rest.exception.EntityNotFoundException;
import com.jpacourse.service.AddressService;
import com.jpacourse.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;


@Service
@Transactional
public class PatientServiceImpl implements PatientService
{
    private final PatientDao patientDao;

    @Autowired
    public PatientServiceImpl( PatientDao patientDao)
    {
        this.patientDao = patientDao;
    }

    @Override
    public PatientTO findById(Long id) {
        final PatientEntity entity = patientDao.findOne(id);
        return PatientMapper.mapToTO(entity);
    }

    @Override
    public List<VisitTO> findVisits(Long patientId) {
        PatientEntity patient = patientDao.findOne(patientId);
        return patient.getVisits().stream().map(VisitMapper::mapToTO).collect(Collectors.toList());
    }

    @Override
    public PatientEntity updateEmail(Long patientId, String email) {
        if (email == null || email == "") {
            throw new IllegalArgumentException("invalid data.");
        }

        PatientEntity entity = patientDao.getOne(patientId);
        if (entity == null) {
            throw new EntityNotFoundException(patientId);
        }

        entity.setEmail(email);
        patientDao.update(entity);
        return entity;
    }
}
