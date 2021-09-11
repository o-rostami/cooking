package com.infosys.coocking.repository;

import com.infosys.coocking.model.entity.IngredientEntity;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IngredientRepository extends BaseRepository<IngredientEntity, Long> {


    Optional<IngredientEntity> findByName(String name);

}
