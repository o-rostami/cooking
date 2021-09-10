package com.infosys.coocking.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RecipeDto extends BaseDto {

    private String name;
    private String instructions;
    private List<RecipeIngredientDto> recipeIngredients;
}
