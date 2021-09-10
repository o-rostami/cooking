package com.infosys.coocking.controller;//package com.infosys.coocking.controller;


import com.infosys.coocking.config.SwaggerConfig;
import com.infosys.coocking.exception.NotNullException;
import com.infosys.coocking.model.dto.PagingRequest;
import com.infosys.coocking.model.dto.PagingResponse;
import com.infosys.coocking.model.dto.RecipeDto;
import com.infosys.coocking.model.dto.RecipeIngredientDto;
import com.infosys.coocking.model.entity.RecipeEntity;
import com.infosys.coocking.model.mapper.RecipeIngredientMapper;
import com.infosys.coocking.model.mapper.RecipeMapper;
import com.infosys.coocking.service.recipe.RecipeService;
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
    private RecipeIngredientMapper recipeIngredientMapper;

    @Autowired
    public RecipeController(RecipeService recipeService, RecipeMapper mapper, RecipeIngredientMapper recipeIngredientMapper) {
        this.recipeService = recipeService;
        this.mapper = mapper;
        this.recipeIngredientMapper = recipeIngredientMapper;
    }

    @GetMapping(value = "{id}")
    @ApiOperation(
            value = "Endpoint for returning the details of Recipe",
            produces = MediaType.APPLICATION_JSON_VALUE,
            httpMethod = "GET")
    public RecipeDto getRecipeById(
            @ApiParam(value = "The id of Recipe must be present. It can't be empty or null",
                    required = true)
            @PathVariable(value = "id") Long id) {
        return mapper.entityToDto(recipeService.getRecipeById(id));
    }

    @GetMapping
    @ApiOperation(
            value = "Endpoint for returning the Recipe list",
            produces = MediaType.APPLICATION_JSON_VALUE,
            response = RecipeDto.class, httpMethod = "GET")
    public List<RecipeDto> getAllProducts() {
        return recipeService.getAllProducts().stream().map(mapper::entityToDto).collect(Collectors.toList());
    }

    @DeleteMapping(value = "{id}")
    @ApiOperation(
            value = "Endpoint for removing the Recipe",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public void deleteRecipeById(
            @ApiParam(value = "The id of Recipe must be present. It can't be empty or null",
                    required = true)
            @PathVariable(value = "id") Long id) {
        recipeService.deleteRecipeById(id);
    }

    @PostMapping
    @ApiOperation(
            value = "Endpoint for creating the Recipe",
            produces = MediaType.APPLICATION_JSON_VALUE,
            response = Long.class,
            httpMethod = "POST")
    public Long createRecipe(@RequestBody RecipeDto recipeDto) {
        validateDtoData(recipeDto);
        return recipeService.createRecipe(mapper.dtoToEntity(recipeDto));
    }

    @PutMapping
    @ApiOperation(
            value = "Endpoint for updating the Recipe",
            produces = MediaType.APPLICATION_JSON_VALUE,
            response = RecipeDto.class,
            httpMethod = "PUT")
    public RecipeDto updateRecipe(@RequestBody RecipeDto recipeDto) {
        validateDtoData(recipeDto);
        return mapper.entityToDto(recipeService.updateRecipe(mapper.dtoToEntity(recipeDto)));
    }

    @PostMapping("search")
    @ApiOperation(
            value = "Endpoint for searching the Recipe",
            produces = MediaType.APPLICATION_JSON_VALUE,
            response = PagingResponse.class,
            httpMethod = "POST")
    public PagingResponse<RecipeDto> searchProduct(
            @RequestBody PagingRequest pagingRequest) {

        if (pagingRequest.getSize() == 0) {
            throw new NotNullException("SIZE.IS.ZERO", "Size");
        }

        PagingResponse<RecipeEntity> response = recipeService.searchRecipe(pagingRequest);
        PagingResponse<RecipeDto> pagingResponse = new PagingResponse<>();
        pagingResponse.setCount(response.getCount());
        pagingResponse.setSize(response.getSize());
        pagingResponse.setStart(response.getStart());
        pagingResponse.setData(response.getData().stream().map(mapper::entityToDto).collect(Collectors.toList()));
        return pagingResponse;
    }

    private void validateDtoData(RecipeDto recipeDto) {
        if (Objects.isNull(recipeDto.getName()) || recipeDto.getName().isEmpty()) {
            throw new NotNullException("RECIPE.NAME.IS.NULL", "recipeName");
        }
        if (Objects.isNull(recipeDto.getInstructions()) || recipeDto.getInstructions().isEmpty()) {
            throw new NotNullException("INSTRUCTIONS.IS.NULL", "instructions");
        }
    }

    @PostMapping("addIngredients/{recipeId}")
    @ApiOperation(
            value = "Endpoint for adding ingredients to the specific Recipe",
            produces = MediaType.APPLICATION_JSON_VALUE,
            response = RecipeDto.class,
            httpMethod = "POST")
    public RecipeDto addIngredients(
            @PathVariable(value = "recipeId") Long recipeId,
            @RequestBody List<RecipeIngredientDto> ingredients) {

        if (Objects.isNull(ingredients) || ingredients.isEmpty()) {
            throw new NotNullException("RECIPE_INGREDIENTS.IS.NULL", "ingredients");
        }
        return mapper.entityToDto(recipeService.addIngredients(
                recipeId,
                ingredients.stream().map(recipeIngredientMapper::dtoToEntity).collect(Collectors.toList())));
    }
}
