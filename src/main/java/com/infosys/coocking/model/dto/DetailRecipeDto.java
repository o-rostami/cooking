package com.infosys.coocking.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DetailRecipeDto extends BaseDto {

    private String name;
    private String instructions;
    private Set<RecipeIngredientDto> recipeIngredients;
}
