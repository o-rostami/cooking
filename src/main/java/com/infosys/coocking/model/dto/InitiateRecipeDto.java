package com.infosys.coocking.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InitiateRecipeDto extends BaseDto {

    private String name;
    private String instructions;
    private RecipeIngredientDto recipeIngredient;
}
