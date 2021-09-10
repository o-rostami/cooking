package com.infosys.coocking.model.mapper;

import com.infosys.coocking.model.dto.RecipeDto;
import com.infosys.coocking.model.entity.RecipeEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RecipeMapper extends BaseMapper {

    RecipeEntity dtoToEntity(RecipeDto dto);

    RecipeDto entityToDto(RecipeEntity entity);

}
