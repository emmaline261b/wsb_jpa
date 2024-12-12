package com.jpacourse.rest;

import com.jpacourse.dto.AddressTO;
import com.jpacourse.rest.exception.EntityNotFoundException;
import com.jpacourse.service.AddressService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PatientController
{

    private final  PatientService  patientService;


    public  PatientController( PatientService  patientService) {
        this.patientService =  patientService;
    }

    @GetMapping("/patient/{id}")
    PatientTO findBaId(@PathVariable final Long id) {
        final  PatientTO  patient =  patientService.findById(id);
        if( patient != null)
        {
            return  Patient;
        }
        throw new EntityNotFoundException(id);
    }
}
