package com.infosys.coocking.repository;

import com.infosys.coocking.model.entity.RoleEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends BaseRepository<RoleEntity, Long> {

    RoleEntity findByName(String name);
}
