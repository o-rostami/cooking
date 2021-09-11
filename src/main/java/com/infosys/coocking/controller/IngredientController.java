package com.infosys.coocking.controller;//package com.infosys.coocking.controller;


import com.infosys.coocking.config.SwaggerConfig;
import com.infosys.coocking.exception.NotNullException;
import com.infosys.coocking.model.dto.IngredientDto;
import com.infosys.coocking.model.dto.PagingRequest;
import com.infosys.coocking.model.dto.PagingResponse;
import com.infosys.coocking.model.entity.IngredientEntity;
import com.infosys.coocking.model.mapper.IngredientMapper;
import com.infosys.coocking.service.ingredient.IngredientService;
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
@RequestMapping("/ingredient/")
@Api(tags = {SwaggerConfig.INGREDIENT_CONTROLLER_TAG})
public class IngredientController {

    private IngredientMapper mapper;
    private IngredientService service;


    @Autowired
    public IngredientController(IngredientService service, IngredientMapper mapper) {
        this.mapper = mapper;
        this.service = service;
    }

    @GetMapping(value = "{id}")
    @ApiOperation(
            value = "Endpoint for returning the details of Ingredient",
            produces = MediaType.APPLICATION_JSON_VALUE,
            httpMethod = "GET")
    public IngredientDto getIngredientById(
            @ApiParam(value = "The id of Recipe must be present. It can't be empty or null",
                    required = true)
            @PathVariable(value = "id") Long id) {
        return mapper.entityToDto(service.getIngredientById(id));
    }

    @GetMapping
    @ApiOperation(
            value = "Endpoint for returning the Ingredient list",
            produces = MediaType.APPLICATION_JSON_VALUE,
            response = IngredientDto.class, httpMethod = "GET")
    public List<IngredientDto> getAllProducts() {
        return service.getAllIngredient().stream().map(mapper::entityToDto).collect(Collectors.toList());
    }

    @DeleteMapping(value = "{id}")
    @ApiOperation(
            value = "Endpoint for removing the Ingredient",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public void deleteIngredientById(
            @ApiParam(value = "The id of Recipe must be present. It can't be empty or null",
                    required = true)
            @PathVariable(value = "id") Long id) {
        service.deleteIngredientById(id);
    }

    @PostMapping
    @ApiOperation(
            value = "Endpoint for creating the Ingredient",
            produces = MediaType.APPLICATION_JSON_VALUE,
            response = Long.class,
            httpMethod = "POST")
    public Long createIngredient(@RequestBody IngredientDto ingredientDto) {
        validateDtoData(ingredientDto);
        return service.createIngredient(mapper.dtoToEntity(ingredientDto));
    }

    @PutMapping
    @ApiOperation(
            value = "Endpoint for updating the Ingredient",
            produces = MediaType.APPLICATION_JSON_VALUE,
            response = IngredientDto.class,
            httpMethod = "PUT")
    public IngredientDto updateIngredient(@RequestBody IngredientDto ingredientDto) {
        validateDtoData(ingredientDto);
        return mapper.entityToDto(service.updateIngredient(mapper.dtoToEntity(ingredientDto)));
    }

    @PostMapping("search")
    @ApiOperation(
            value = "Endpoint for searching the Ingredient",
            produces = MediaType.APPLICATION_JSON_VALUE,
            response = PagingResponse.class,
            httpMethod = "POST")
    public PagingResponse<IngredientDto> searchProduct(
            @RequestBody PagingRequest pagingRequest) {

        if (pagingRequest.getSize() == 0) {
            throw new NotNullException("SIZE.IS.ZERO", "Size");
        }

        PagingResponse<IngredientEntity> response = service.searchIngredient(pagingRequest);
        PagingResponse<IngredientDto> pagingResponse = new PagingResponse<>();
        pagingResponse.setCount(response.getCount());
        pagingResponse.setSize(response.getSize());
        pagingResponse.setStart(response.getStart());
        pagingResponse.setData(response.getData().stream().map(mapper::entityToDto).collect(Collectors.toList()));
        return pagingResponse;
    }

    private void validateDtoData(IngredientDto ingredientDto) {
        if (Objects.isNull(ingredientDto.getName()) || ingredientDto.getName().isEmpty()) {
            throw new NotNullException("INGREDIENT.NAME.IS.NULL", "Name");
        }
    }
}
