package com.jpacourse.persistence.dao.impl;

import com.jpacourse.persistence.dao.DoctorDao;
import com.jpacourse.persistence.dao.VisitDao;
import com.jpacourse.persistence.entity.DoctorEntity;
import com.jpacourse.persistence.entity.VisitEntity;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Repository
public class DoctorDaoImpl extends AbstractDao<DoctorEntity, Long> implements DoctorDao
{

    private final VisitDao visitDao;

    public DoctorDaoImpl(VisitDao visitDao) {
        this.visitDao = visitDao;
    }

    @Override
    public List<VisitEntity> getAllVisits(Long doctorId) {
        return visitDao.findAll().stream()
                .filter(x -> Objects.equals(x.getDoctor().getId(), doctorId))
                .collect(Collectors.toList());
    }


}
