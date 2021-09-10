package com.infosys.coocking.repository;

import com.infosys.coocking.model.entity.RecipeEntity;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RecipeRepository extends BaseRepository<RecipeEntity, Long> {

    Optional<RecipeEntity> findByNameAndUserId(String name, Long userID);

    List<RecipeEntity> findByUserId(Long userId);

}
