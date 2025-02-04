package com.jpacourse.mapper;

import com.jpacourse.dto.PatientTO;
import com.jpacourse.persistence.entity.PatientEntity;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class PatientMapper {

    public static PatientTO mapToTO(final PatientEntity entity) {
        if (entity == null) {
            return null;
        }

        PatientTO patientTO = new PatientTO();
        patientTO.setId(entity.getId());
        patientTO.setFirstName(entity.getFirstName());
        patientTO.setLastName(entity.getLastName());
        patientTO.setPronoun(entity.getPronoun());
        patientTO.setTelephoneNumber(entity.getTelephoneNumber());
        patientTO.setEmail(entity.getEmail());
        patientTO.setPatientNumber(entity.getPatientNumber());
        patientTO.setDateOfBirth(entity.getDateOfBirth());
        patientTO.setAddress(entity.getAddress());
        patientTO.setVersion(entity.getVersion());


        // Mapowanie wizyt
        patientTO.setVisits(
                entity.getVisits().stream()
                        .filter(visit -> visit.getTime().isBefore(java.time.LocalDateTime.now()))
                        .map(VisitMapper::mapToTO)
                        .collect(Collectors.toList())
        );

        return patientTO;
    }


}
