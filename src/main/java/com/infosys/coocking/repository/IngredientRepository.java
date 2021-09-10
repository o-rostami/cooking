package com.infosys.coocking.repository;

import com.infosys.coocking.model.entity.IngredientEntity;
import com.infosys.coocking.model.entity.UserEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface IngredientRepository extends BaseRepository<IngredientEntity, Long> {

}
