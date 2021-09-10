package com.infosys.coocking.config;

import com.infosys.coocking.model.entity.MeasurementUnitEntity;
import com.infosys.coocking.model.entity.RoleEntity;
import com.infosys.coocking.model.entity.UserEntity;
import com.infosys.coocking.repository.MeasurementUnitRepository;
import com.infosys.coocking.repository.RoleRepository;
import com.infosys.coocking.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * A <i>DbInit</i>. This class has responsibility to populate the data base for the first time<p>
 *
 * @author Omid Rostami
 */


@Component
public class DbInit {

    private final MeasurementUnitRepository measurementUnitRepository;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    @Value("${role.admin}")
    private String roleAdmin;
    @Value("${role.user}")
    private String roleUser;
    @Value("${admin.username}")
    private String adminUsername;
    @Value("${admin.password}")
    private String adminPassword;


    @Autowired
    public DbInit(MeasurementUnitRepository measurementUnitRepository, UserRepository userRepository, RoleRepository roleRepository) {
        this.measurementUnitRepository = measurementUnitRepository;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @PostConstruct
    private void populateMeasurementUnit() {
        MeasurementUnitEntity categoryA = new MeasurementUnitEntity();
        categoryA.setName("Category A");
        measurementUnitRepository.save(categoryA);

        MeasurementUnitEntity categoryB = new MeasurementUnitEntity();
        categoryB.setName("Category B");
        measurementUnitRepository.save(categoryB);

        MeasurementUnitEntity categoryC = new MeasurementUnitEntity();
        categoryC.setName("Category C");
        measurementUnitRepository.save(categoryC);

        MeasurementUnitEntity categoryD = new MeasurementUnitEntity();
        categoryD.setName("Category D");
        measurementUnitRepository.save(categoryD);


    }

    @PostConstruct
    private void populateUserRole() {
        RoleEntity adminRole = new RoleEntity();
        adminRole.setName(roleAdmin);
        roleRepository.save(adminRole);

        RoleEntity userRole = new RoleEntity();
        userRole.setName(roleUser);
        roleRepository.save(userRole);

        UserEntity adminUser = new UserEntity();
        adminUser.setUserName(adminUsername);
        adminUser.setPassword(adminPassword);
        adminUser.setBlocked(Boolean.TRUE);
        adminUser = userRepository.save(adminUser);

        adminUser.getRoles().add(adminRole);
        userRepository.save(adminUser);
    }

}

