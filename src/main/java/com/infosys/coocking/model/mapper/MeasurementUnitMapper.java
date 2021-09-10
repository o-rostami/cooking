package com.infosys.coocking.model.mapper;

import com.infosys.coocking.model.dto.MeasurementUnitDto;
import com.infosys.coocking.model.entity.MeasurementUnitEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MeasurementUnitMapper extends BaseMapper {

    MeasurementUnitEntity dtoToEntity(MeasurementUnitDto dto);

    MeasurementUnitDto entityToDto(MeasurementUnitEntity entity);

}
