package com.infosys.coocking.controller;//package com.infosys.coocking.controller;


import com.infosys.coocking.config.SwaggerConfig;
import com.infosys.coocking.exception.NotNullException;
import com.infosys.coocking.model.dto.MeasurementUnitDto;
import com.infosys.coocking.model.dto.PagingRequest;
import com.infosys.coocking.model.dto.PagingResponse;
import com.infosys.coocking.model.entity.MeasurementUnitEntity;
import com.infosys.coocking.model.mapper.MeasurementUnitMapper;
import com.infosys.coocking.service.measurementunit.MeasurementUnitService;
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
@RequestMapping("/measurementunit/")
@Api(tags = {SwaggerConfig.INGREDIENT_CONTROLLER_TAG})
public class MeasurementUnitController {

    private MeasurementUnitMapper mapper;
    private MeasurementUnitService service;


    @Autowired
    public MeasurementUnitController(MeasurementUnitService service, MeasurementUnitMapper mapper) {
        this.mapper = mapper;
        this.service = service;
    }

    @GetMapping(value = "{id}")
    @ApiOperation(
            value = "Endpoint for returning the details of MeasurementUnit",
            produces = MediaType.APPLICATION_JSON_VALUE,
            response = MeasurementUnitDto.class,
            httpMethod = "GET")
    public MeasurementUnitDto getMeasurementUnitById(
            @ApiParam(value = "The id of Recipe must be present. It can't be empty or null",
                    required = true)
            @PathVariable(value = "id") Long id) {
        return mapper.entityToDto(service.getMeasurementUnitById(id));
    }

    @GetMapping
    @ApiOperation(
            value = "Endpoint for returning the MeasurementUnit list",
            produces = MediaType.APPLICATION_JSON_VALUE,
            response = MeasurementUnitDto.class,
            httpMethod = "GET")
    public List<MeasurementUnitDto> getAllIngredient() {
        return service.getAllIngredient().stream().map(mapper::entityToDto).collect(Collectors.toList());
    }

    @DeleteMapping(value = "{id}")
    @ApiOperation(
            value = "Endpoint for removing the MeasurementUnit",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public void deleteMeasurementUnitById(
            @ApiParam(value = "The id of Recipe must be present. It can't be empty or null",
                    required = true)
            @PathVariable(value = "id") Long id) {
        service.deleteMeasurementUnitById(id);
    }

    @PostMapping
    @ApiOperation(
            value = "Endpoint for creating the MeasurementUnit",
            produces = MediaType.APPLICATION_JSON_VALUE,
            response = Long.class,
            httpMethod = "POST")
    public Long createMeasurementUnit(@RequestBody MeasurementUnitDto measurementUnit) {
        validateDtoData(measurementUnit);
        return service.createMeasurementUnit(mapper.dtoToEntity(measurementUnit));
    }

    @PutMapping
    @ApiOperation(
            value = "Endpoint for updating the MeasurementUnit",
            produces = MediaType.APPLICATION_JSON_VALUE,
            response = MeasurementUnitDto.class,
            httpMethod = "PUT")
    public MeasurementUnitDto updateMeasurementUnit(@RequestBody MeasurementUnitDto measurementUnitDto) {
        validateDtoData(measurementUnitDto);
        return mapper.entityToDto(service.updateMeasurementUnit(mapper.dtoToEntity(measurementUnitDto)));
    }

    @PostMapping("search")
    @ApiOperation(
            value = "Endpoint for searching the MeasurementUnit",
            produces = MediaType.APPLICATION_JSON_VALUE,
            response = PagingResponse.class,
            httpMethod = "POST")
    public PagingResponse<MeasurementUnitDto> searchProduct(
            @RequestBody PagingRequest pagingRequest) {

        if (pagingRequest.getSize() == 0) {
            throw new NotNullException("SIZE.IS.ZERO", "Size");
        }

        PagingResponse<MeasurementUnitEntity> response = service.searchIngredient(pagingRequest);
        PagingResponse<MeasurementUnitDto> pagingResponse = new PagingResponse<>();
        pagingResponse.setCount(response.getCount());
        pagingResponse.setSize(response.getSize());
        pagingResponse.setStart(response.getStart());
        pagingResponse.setData(response.getData().stream().map(mapper::entityToDto).collect(Collectors.toList()));
        return pagingResponse;
    }

    private void validateDtoData(MeasurementUnitDto measurementUnit) {
        if (Objects.isNull(measurementUnit.getName()) || measurementUnit.getName().isEmpty()) {
            throw new NotNullException("MEASUREMENTUNIT.IS.NULL", "Name");
        }
    }
}
