package com.infosys.coocking.repository;

import com.infosys.coocking.model.entity.RecipeEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface RecipeRepository extends BaseRepository<RecipeEntity, Long> {

}
