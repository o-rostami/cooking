package com.infosys.coocking.service.ingredient;

import com.infosys.coocking.exception.NotFoundException;
import com.infosys.coocking.model.entity.IngredientEntity;
import com.infosys.coocking.repository.IngredientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

}
