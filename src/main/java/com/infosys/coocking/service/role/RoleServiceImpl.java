package com.infosys.coocking.service.role;

import com.infosys.coocking.model.entity.RoleEntity;
import com.infosys.coocking.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository repository;


    @Autowired
    public RoleServiceImpl(RoleRepository repository) {
        this.repository = repository;
    }

    @Override
    public RoleEntity getRoleByName(String roleName) {
        return repository.findByName(roleName);
    }

}
