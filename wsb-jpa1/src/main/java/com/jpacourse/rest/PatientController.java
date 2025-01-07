package com.jpacourse.rest;

import com.jpacourse.dto.PatientTO;
import com.jpacourse.mapper.PatientMapper;
import com.jpacourse.rest.exception.EntityNotFoundException;
import com.jpacourse.service.PatientService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpServerErrorException;

@RestController
public class PatientController
{

    private final PatientService patientService;
    private final PatientMapper mapper;


    public  PatientController(PatientService  patientService, PatientMapper mapper) {
        this.patientService =  patientService;
        this.mapper = mapper;
    }

    @GetMapping("/patient/{id}")
    PatientTO findBaId(@PathVariable final Long id) {
        final  PatientTO  patient =  patientService.findById(id);
        if( patient != null)
        {
            return patient;
        }
        throw new EntityNotFoundException(id);
    }

    @PostMapping("/patient/update")
    PatientTO updatePatientEmail(@RequestParam final Long id, @RequestParam final String email) {
        return mapper.mapToTO(patientService.updateEmail(id, email));
    }

}
