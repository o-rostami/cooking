package com.infosys.coocking.repository;

import com.infosys.coocking.model.entity.MeasurementUnitEntity;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MeasurementUnitRepository extends BaseRepository<MeasurementUnitEntity, Long> {

    Optional<MeasurementUnitEntity> findByName(String name);
}
