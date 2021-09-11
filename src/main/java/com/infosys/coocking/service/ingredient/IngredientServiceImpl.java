package com.infosys.coocking.service.ingredient;

import com.infosys.coocking.exception.BadRequestException;
import com.infosys.coocking.exception.BusinessException;
import com.infosys.coocking.exception.NotFoundException;
import com.infosys.coocking.model.dto.PagingRequest;
import com.infosys.coocking.model.dto.PagingResponse;
import com.infosys.coocking.model.entity.IngredientEntity;
import com.infosys.coocking.model.entity.RecipeEntity;
import com.infosys.coocking.repository.IngredientRepository;
import com.infosys.coocking.specification.PaginationExecutor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class IngredientServiceImpl implements IngredientService {

    private final IngredientRepository repository;

    @Autowired
    public IngredientServiceImpl(IngredientRepository repository) {
        this.repository = repository;
    }


    @Override
    public IngredientEntity getIngredientById(Long ingredientId) {
        return repository
                .findById(ingredientId)
                .orElseThrow(() -> new NotFoundException("INGREDIENT.NOT.EXIST"));
    }

    @Override
    public void deleteIngredientById(Long ingredientId) {
        IngredientEntity entity = getIngredientById(ingredientId);
        if (Objects.nonNull(entity.getRecipeIngredients()) || !entity.getRecipeIngredients().isEmpty()) {
            throw new BusinessException("INGREDIENT.MUST.NOT.DELETE");
        }
        repository.delete(entity);
    }

    @Override
    public Long createIngredient(IngredientEntity ingredientEntity) {
        if (Objects.nonNull(ingredientEntity.getId())) {
            throw new BadRequestException("ID.IS.NOT.NULL", "ID");
        }
        checkDuplicateIngredientName(ingredientEntity);
        return repository.save(ingredientEntity).getId();
    }

    @Override
    public IngredientEntity updateIngredient(IngredientEntity ingredientEntity) {
        IngredientEntity dbIngredient = getIngredientById(ingredientEntity.getId());
        if (Objects.nonNull(dbIngredient.getRecipeIngredients()) || !dbIngredient.getRecipeIngredients().isEmpty()) {
            throw new BusinessException("INGREDIENT.MUST.NOT.UPDATE");
        }
        if (!ingredientEntity.getVersion().equals(dbIngredient.getVersion())) {
            throw new BusinessException("VERSION.IS.NOT.VALID", "version");
        }
        checkDuplicateIngredientName(ingredientEntity);
        dbIngredient.setName(ingredientEntity.getName());
        return repository.save(dbIngredient);
    }

    private void checkDuplicateIngredientName(IngredientEntity ingredientEntity) {
        if (repository.findByName(ingredientEntity.getName()).isPresent()) {
            throw new BusinessException("INGREDIENT.IS.DUPLICATE");
        }
    }

    @Override
    public List<IngredientEntity> getAllIngredient() {
        return repository.findAll();
    }

    @Override
    public PagingResponse<IngredientEntity> searchIngredient(PagingRequest pagingRequest) {
        PaginationExecutor<RecipeEntity> paginationExecutor = new PaginationExecutor<>(pagingRequest, repository);
        return paginationExecutor.execute();
    }
}
