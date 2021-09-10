package com.infosys.coocking.model.mapper;

import com.infosys.coocking.model.dto.IngredientDto;
import com.infosys.coocking.model.entity.IngredientEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface IngredientMapper extends BaseMapper {

    IngredientEntity dtoToEntity(IngredientDto dto);

    IngredientDto entityToDto(IngredientEntity entity);

}
