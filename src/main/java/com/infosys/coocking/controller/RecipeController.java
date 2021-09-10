package com.infosys.coocking.controller;//package com.infosys.coocking.controller;


import com.infosys.coocking.config.SwaggerConfig;
import com.infosys.coocking.exception.NotNullException;
import com.infosys.coocking.model.dto.DetailRecipeDto;
import com.infosys.coocking.model.dto.InitiateRecipeDto;
import com.infosys.coocking.model.mapper.RecipeMapper;
import com.infosys.coocking.service.RecipeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/recipe/")
@Api(tags = {SwaggerConfig.RECIPE_CONTROLLER_TAG})
public class RecipeController {

    private RecipeService recipeService;
    private RecipeMapper mapper;

    @Autowired
    public RecipeController(RecipeService recipeService, RecipeMapper mapper) {
        this.recipeService = recipeService;
        this.mapper = mapper;

    }

    @GetMapping(value = "{id}")
    @ApiOperation(value = "Endpoint for returning the details of Recipe",
            produces = MediaType.APPLICATION_JSON_VALUE,
            httpMethod = "GET")
    public void getRecipeById(
            @ApiParam(value = "The id of Recipe must be present. It can't be empty or null",
                    required = true)
            @PathVariable(value = "id") Long id) {
        recipeService.getRecipeById(id);
    }

    @GetMapping
    @ApiOperation(value = "Endpoint for returning the Recipe list",
            produces = MediaType.APPLICATION_JSON_VALUE,
            response = DetailRecipeDto.class, httpMethod = "GET")
    public List<DetailRecipeDto> getAllProducts() {
        return recipeService.getAllProducts().stream().map(mapper::entityToDto).collect(Collectors.toList());
    }

    @DeleteMapping(value = "{id}")
    @ApiOperation(value = "Endpoint for removing the Recipe",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public void deleteRecipeById(
            @ApiParam(value = "The id of Recipe must be present. It can't be empty or null",
                    required = true)
            @PathVariable(value = "id") Long id) {
        recipeService.deleteRecipeById(id);
    }

    @PostMapping
    @ApiOperation(value = "Endpoint for creating the Recipe",
            produces = MediaType.APPLICATION_JSON_VALUE,
            response = Long.class,
            httpMethod = "POST")
    public Long createRecipe(@RequestBody InitiateRecipeDto initiateRecipeDto) {
        validateData(initiateRecipeDto);
        return recipeService.createRecipe(mapper.initiateDtoToEntity(initiateRecipeDto));
    }

    private void validateData(InitiateRecipeDto initiateRecipeDto) {
        if (initiateRecipeDto.getName().isEmpty()) {
            throw new NotNullException("RECIPE.NAME.IS.NULL", "recipeName");
        }
        if (initiateRecipeDto.getInstructions().isEmpty()) {
            throw new NotNullException("INSTRUCTIONS.IS.NULL", "instructions");
        }
        if (Objects.isNull(initiateRecipeDto.getRecipeIngredient())) {
            throw new NotNullException("RECIPE_INGREDIENTS.IS.NULL", "recipeIngredients");
        } else {
            if (initiateRecipeDto.getRecipeIngredient().getAmount().isNaN()) {
                throw new NotNullException("AMOUNT.IS.NOT.NUMBER", "amount");
            }
            if (Objects.isNull(initiateRecipeDto.getRecipeIngredient().getMeasurementUnit()) || Objects.isNull(initiateRecipeDto.getRecipeIngredient().getMeasurementUnit().getId())) {
                throw new NotNullException("MEASUREMENT_UNIT.IS.NULL", "measurementUnit");
            }
            if (Objects.isNull(initiateRecipeDto.getRecipeIngredient().getIngredient()) || Objects.isNull(initiateRecipeDto.getRecipeIngredient().getIngredient().getId())) {
                throw new NotNullException("INGREDIENT.IS.NULL", "ingredient");
            }
        }
    }

    @PutMapping
    @ApiOperation(value = "Endpoint for updating the Recipe",
            produces = MediaType.APPLICATION_JSON_VALUE,
            response = DetailRecipeDto.class,
            httpMethod = "PUT")
    public DetailRecipeDto updateRecipe(@RequestBody InitiateRecipeDto detailRecipeDto) {
        validateData(detailRecipeDto);
        return mapper.entityToDto(recipeService.updateRecipe(mapper.initiateDtoToEntity(detailRecipeDto)));
    }
}
