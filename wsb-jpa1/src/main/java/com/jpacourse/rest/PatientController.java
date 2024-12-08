package com.jpacourse.rest;

import com.jpacourse.dto.AddressTO;
import com.jpacourse.rest.exception.EntityNotFoundException;
import com.jpacourse.service.PatientService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PatientController {


    private final PatientService patientService;

    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

//
//    @GetMapping("/address/{id}")
//    AddressTO findBaId(@PathVariable final Long id) {
//        final AddressTO address = patientService.findById(id);
//        if(address != null)
//        {
//            return address;
//        }
//        throw new EntityNotFoundException(id);
//    }


}
