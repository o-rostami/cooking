package com.infosys.coocking.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RecipeIngredientDto  extends BaseDto{

    private Double amount;
    private IngredientDto ingredient;
    private MeasurementUnitDto measurementUnit;

}
