package com.infosys.coocking.service.measurementunit;

import com.infosys.coocking.exception.NotFoundException;
import com.infosys.coocking.model.entity.MeasurementUnitEntity;
import com.infosys.coocking.repository.MeasurementUnitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MeasurementUnitServiceImpl implements MeasurementUnitService {

    private final MeasurementUnitRepository repository;

    @Autowired
    public MeasurementUnitServiceImpl(MeasurementUnitRepository repository) {
        this.repository = repository;
    }


    @Override
    public MeasurementUnitEntity getMeasurementUnitById(Long measurementUnitId) {
        return repository
                .findById(measurementUnitId)
                .orElseThrow(() -> new NotFoundException("MEASUREMENT.UNIT.NOT.EXIST"));
    }

}
