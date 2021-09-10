package com.infosys.coocking.model.mapper;

import com.infosys.coocking.model.dto.RecipeIngredientDto;
import com.infosys.coocking.model.entity.RecipeIngredientEntity;
import org.mapstruct.Mapper;

import java.util.Set;

@Mapper(componentModel = "spring")
public interface RecipeIngredientMapper extends BaseMapper {

    RecipeIngredientEntity dtoToEntity(RecipeIngredientDto dto);

    RecipeIngredientDto entityToDto(RecipeIngredientEntity entity);


}
