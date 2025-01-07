package com.jpacourse.service;

import com.jpacourse.dto.PatientTO;
import com.jpacourse.dto.VisitTO;

import java.util.List;

public interface PatientService
{
    public PatientTO findById(final Long id);

    List<VisitTO> findVisits(final Long patientId);
}
