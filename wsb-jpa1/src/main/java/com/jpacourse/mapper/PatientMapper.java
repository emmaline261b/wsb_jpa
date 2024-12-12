package com.jpacourse.mapper;

import com.jpacourse.dto.AddressTO;
import com.jpacourse.persistence.entity.AddressEntity;

public final class PatientMapper
{

    public static PatientTO mapToTO(final PatientEntity entity)
    {
        if (entity == null)
        {
            return null;
        }
        final PatientTO patientTO = new PatientTO();
        patientTO.setId(entity.getId);
        patientTO.setFirstName(entity.getFirstName);
        patientTO.setLastName(entity.getLastName);
        patientTO.setTelephoneNumber(entity.getTelephoneNumber);
        patientTO.setEmail(entity.getEmail);
        patientTO.setPatientNumber(entity.getPatientNumber);
        patientTO.setDateOfBirth(entity.getDateOfBirth);
        patientTO.setAddress(entity.getAddress);
        patientTO.setVisits(entity.getVisits);



        return patientTO;
    }


//    public static AddressEntity mapToEntity(final AddressTO addressTO)
//    {
//        if(addressTO == null)
//        {
//            return null;
//        }
//        AddressEntity addressEntity = new AddressEntity();
//        addressEntity.setId(addressTO.getId());
//        addressEntity.setAddressLine1(addressTO.getAddressLine1());
//        addressEntity.setAddressLine2(addressTO.getAddressLine2());
//        addressEntity.setCity(addressTO.getCity());
//        addressEntity.setPostalCode(addressTO.getPostalCode());
//        return addressEntity;
//    }
}
