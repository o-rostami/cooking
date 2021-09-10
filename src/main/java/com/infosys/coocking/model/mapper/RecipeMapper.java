package com.infosys.coocking.model.mapper;

import com.infosys.coocking.model.dto.InitiateRecipeDto;
import com.infosys.coocking.model.dto.DetailRecipeDto;
import com.infosys.coocking.model.entity.IngredientEntity;
import com.infosys.coocking.model.entity.MeasurementUnitEntity;
import com.infosys.coocking.model.entity.RecipeEntity;
import com.infosys.coocking.model.entity.RecipeIngredientEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RecipeMapper extends BaseMapper {

    RecipeEntity dtoToEntity(DetailRecipeDto dto);

    default RecipeEntity initiateDtoToEntity(InitiateRecipeDto dto) {
        IngredientEntity ingredientEntity = new IngredientEntity();
        ingredientEntity.setId(dto.getRecipeIngredient().getIngredient().getId());

        MeasurementUnitEntity measurementUnitEntity = new MeasurementUnitEntity();
        measurementUnitEntity.setId(dto.getRecipeIngredient().getMeasurementUnit().getId());

        RecipeIngredientEntity recipeIngredientEntity = new RecipeIngredientEntity();
        recipeIngredientEntity.setAmount(dto.getRecipeIngredient().getAmount());
        recipeIngredientEntity.setIngredient(ingredientEntity);
        recipeIngredientEntity.setMeasurementUnit(measurementUnitEntity);

        RecipeEntity recipeEntity = new RecipeEntity();
        recipeEntity.setName(dto.getName());
        recipeEntity.setInstructions(dto.getInstructions());
        recipeEntity.getRecipeIngredients().add(recipeIngredientEntity);

        return recipeEntity;
    }

    DetailRecipeDto entityToDto(RecipeEntity entity);

}
