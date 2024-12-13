package com.jpacourse.mapper;

import com.jpacourse.dto.AddressTO;
import com.jpacourse.dto.PatientTO;
import com.jpacourse.persistence.entity.AddressEntity;
import com.jpacourse.persistence.entity.PatientEntity;

public final class PatientMapper
{

    public static PatientTO mapToTO(final PatientEntity entity)
    {
        if (entity == null)
        {
            return null;
        }
        final PatientTO patientTO = new PatientTO();
        patientTO.setId(entity.getId());
        patientTO.setFirstName(entity.getFirstName());
        patientTO.setLastName(entity.getLastName());
        patientTO.setTelephoneNumber(entity.getTelephoneNumber());
        patientTO.setEmail(entity.getEmail());
        patientTO.setPatientNumber(entity.getPatientNumber());
        patientTO.setDateOfBirth(entity.getDateOfBirth());
        patientTO.setAddress(entity.getAddress());
        patientTO.setVisits(entity.getVisits());



        return patientTO;
    }


    public static PatientEntity mapToEntity(final PatientTO patientTO)
    {
        if(patientTO == null)
        {
            return null;
        }
        PatientEntity patientEntity = new PatientEntity();
        patientEntity.setId(patientTO.getId());
        patientEntity.setFirstName(patientTO.getFirstName());
        patientEntity.setLastName(patientTO.getLastName());
        patientEntity.setTelephoneNumber(patientTO.getTelephoneNumber());
        patientEntity.setEmail(patientTO.getEmail());
        patientEntity.setPatientNumber(patientTO.getPatientNumber());
        patientEntity.setDateOfBirth(patientTO.getDateOfBirth());
        patientEntity.setAddress(patientTO.getAddress());
        patientEntity.setVisits(patientTO.getVisits());
        return patientEntity;
    }
}
