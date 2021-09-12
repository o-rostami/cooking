package com.infosys.coocking.service.measurementunit;

import com.infosys.coocking.exception.BadRequestException;
import com.infosys.coocking.exception.BusinessException;
import com.infosys.coocking.exception.NotFoundException;
import com.infosys.coocking.model.dto.PagingRequest;
import com.infosys.coocking.model.dto.PagingResponse;
import com.infosys.coocking.model.entity.MeasurementUnitEntity;
import com.infosys.coocking.model.entity.RecipeEntity;
import com.infosys.coocking.repository.MeasurementUnitRepository;
import com.infosys.coocking.specification.PaginationExecutor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class MeasurementUnitServiceImpl implements MeasurementUnitService {

    private final MeasurementUnitRepository repository;

    @Autowired
    public MeasurementUnitServiceImpl(MeasurementUnitRepository repository) {
        this.repository = repository;
    }


    @Override
    public MeasurementUnitEntity getMeasurementUnitById(Long measurementUnitId) {
        return repository
                .findById(measurementUnitId)
                .orElseThrow(() -> new NotFoundException("MEASUREMENTUNIT.NOT.EXIST"));
    }

    @Override
    public void deleteMeasurementUnitById(Long measurementUnitId) {
        MeasurementUnitEntity entity = getMeasurementUnitById(measurementUnitId);
        if (Objects.nonNull(entity.getRecipeIngredients()) || !entity.getRecipeIngredients().isEmpty()) {
            throw new BusinessException("MEASUREMENTUNIT.MUST.NOT.DELETE");
        }
        repository.delete(entity);
    }

    @Override
    public Long createMeasurementUnit(MeasurementUnitEntity measurementUnitEntity) {
        if (Objects.nonNull(measurementUnitEntity.getId())) {
            throw new BadRequestException("ID.IS.NOT.NULL", "ID");
        }
        checkDuplicateName(measurementUnitEntity);
        return repository.save(measurementUnitEntity).getId();
    }

    private void checkDuplicateName(MeasurementUnitEntity measurementUnitEntity) {
        if (repository.findByName(measurementUnitEntity.getName()).isPresent()) {
            throw new BusinessException("MEASUREMENTUNIT.IS.DUPLICATE");
        }
    }

    @Override
    public MeasurementUnitEntity updateMeasurementUnit(MeasurementUnitEntity measurementUnitEntity) {
        MeasurementUnitEntity dbMeasurementUnitEntity = getMeasurementUnitById(measurementUnitEntity.getId());
        if (Objects.nonNull(dbMeasurementUnitEntity.getRecipeIngredients()) || !dbMeasurementUnitEntity.getRecipeIngredients().isEmpty()) {
            throw new BusinessException("MEASUREMENTUNIT.MUST.NOT.UPDATE");
        }
        if (!measurementUnitEntity.getVersion().equals(dbMeasurementUnitEntity.getVersion())) {
            throw new BusinessException("VERSION.IS.NOT.VALID", "version");
        }
        checkDuplicateName(measurementUnitEntity);
        dbMeasurementUnitEntity.setName(measurementUnitEntity.getName());
        return repository.save(dbMeasurementUnitEntity);
    }

    @Override
    public List<MeasurementUnitEntity> getAllIngredient() {
        return repository.findAll();
    }

    @Override
    public PagingResponse<MeasurementUnitEntity> searchIngredient(PagingRequest pagingRequest) {
        PaginationExecutor<RecipeEntity> paginationExecutor = new PaginationExecutor<>(pagingRequest, repository);
        return paginationExecutor.execute();
    }
}
