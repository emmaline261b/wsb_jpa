package com.jpacourse.persistence.dao;

import com.jpacourse.persistence.entity.DoctorEntity;
import com.jpacourse.persistence.entity.VisitEntity;

import java.util.List;

public interface DoctorDao extends Dao<DoctorEntity, Long>
{
    List<VisitEntity> getAllVisits(Long doctorId);
}
