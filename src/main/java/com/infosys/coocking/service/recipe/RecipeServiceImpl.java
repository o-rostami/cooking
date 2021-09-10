package com.infosys.coocking.service.recipe;

import com.infosys.coocking.exception.BadRequestException;
import com.infosys.coocking.exception.BusinessException;
import com.infosys.coocking.exception.NotFoundException;
import com.infosys.coocking.model.dto.PagingRequest;
import com.infosys.coocking.model.dto.PagingResponse;
import com.infosys.coocking.model.dto.SearchCriteria;
import com.infosys.coocking.model.entity.RecipeEntity;
import com.infosys.coocking.model.entity.RecipeIngredientEntity;
import com.infosys.coocking.model.enums.SearchOperation;
import com.infosys.coocking.repository.RecipeRepository;
import com.infosys.coocking.service.ingredient.IngredientService;
import com.infosys.coocking.service.measurementunit.MeasurementUnitService;
import com.infosys.coocking.specification.PaginationExecutor;
import com.infosys.coocking.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class RecipeServiceImpl implements RecipeService {

    private final RecipeRepository repository;
    private final MeasurementUnitService measurementUnitService;
    private final IngredientService ingredientService;


    @Autowired
    public RecipeServiceImpl(RecipeRepository repository, MeasurementUnitService measurementUnitService, IngredientService ingredientService) {
        this.repository = repository;
        this.measurementUnitService = measurementUnitService;
        this.ingredientService = ingredientService;
    }

    @Override
    @Transactional
    public Long createRecipe(RecipeEntity recipeEntity) {
        if (Objects.nonNull(recipeEntity.getId())) {
            throw new BadRequestException("ID.IS.NOT.NULL", "ID");
        }
        checkDuplicateRecipeName(recipeEntity);
        recipeEntity.setUser(SecurityUtils.getCurrentUser());
        return repository.save(recipeEntity).getId();
    }

    private void checkDuplicateRecipeName(RecipeEntity recipeEntity) {
        if (repository.findByNameAndUserId(recipeEntity.getName(), SecurityUtils.getCurrentUser().getId()).isPresent()) {
            throw new BusinessException("RECIPE.IS.DUPLICATE");
        }
    }

    @Override
    @Transactional
    public RecipeEntity updateRecipe(RecipeEntity recipeEntity) {
        RecipeEntity dbRecipe = getRecipeById(recipeEntity.getId());
        if (!recipeEntity.getVersion().equals(dbRecipe.getVersion())) {
            throw new BusinessException("VERSION.IS.NOT.VALID", "version");
        }
        checkDuplicateRecipeName(recipeEntity);
        dbRecipe.setName(recipeEntity.getName());
        dbRecipe.setInstructions(recipeEntity.getInstructions());
        repository.save(dbRecipe);
        return dbRecipe;
    }

    @Override
    public RecipeEntity getRecipeById(Long recipeId) {
        return repository
                .findById(recipeId)
                .orElseThrow(() -> new NotFoundException("RECIPE.NOT.EXIST"));
    }

    @Override
    public void deleteRecipeById(Long recipeId) {
        RecipeEntity entity = getRecipeById(recipeId);
        repository.delete(entity);
    }

    @Override
    public List<RecipeEntity> getAllProducts() {
        return repository.findByUserId(SecurityUtils.getCurrentUser().getId());
    }

    @Override
    public PagingResponse<RecipeEntity> searchRecipe(PagingRequest pagingRequest) {
        SearchCriteria searchCriteria = new SearchCriteria();
        searchCriteria.setKey("user");
        searchCriteria.setOperation(SearchOperation.EQUAL);
        searchCriteria.setValue(SecurityUtils.getCurrentUser());
        if (Objects.nonNull(pagingRequest.getParams())) {
            pagingRequest.getParams().add(searchCriteria);
        } else {
            pagingRequest.setParams(Arrays.asList(searchCriteria));
        }
        PaginationExecutor<RecipeEntity> paginationExecutor = new PaginationExecutor<>(pagingRequest, repository);
        return paginationExecutor.execute();
    }

    @Override
    @Transactional
    public RecipeEntity addIngredients(Long recipeId, List<RecipeIngredientEntity> ingredients) {
        RecipeEntity recipe = getRecipeById(recipeId);
        recipe.getRecipeIngredients().clear();
        recipe.getRecipeIngredients().addAll(ingredients.stream().map(item -> {
            item.setMeasurementUnit(measurementUnitService.getMeasurementUnitById(item.getMeasurementUnit().getId()));
            item.setIngredient(ingredientService.getIngredientById(item.getIngredient().getId()));
            item.setRecipe(recipe);
            return item;
        }).collect(Collectors.toList()));
        return repository.save(recipe);
    }
}
