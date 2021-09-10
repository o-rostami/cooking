package com.infosys.coocking.model.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Audited
@Setter
@Getter
@Entity
@Table(name = UserEntity.TABLE_NAME)
public class UserEntity extends BaseEntity<Long> {

    public static final String TABLE_NAME = "USER";

    @Column(name = "USER_NAME", unique = true)
    private String userName;
    @Column(name = "PASSWORD")
    private String password;
    @Column(name = "ENABLED")
    private boolean blocked;
    @Column(name = "EMAIL")
    private String email;

    @NotAudited
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    List<RecipeEntity> recipes;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(
            name = "USERS_ROLES",
            joinColumns = @JoinColumn(name = "USER_ID"),
            inverseJoinColumns = @JoinColumn(name = "ROLE_ID")
    )
    private List<RoleEntity> roles = new ArrayList<>();
}
