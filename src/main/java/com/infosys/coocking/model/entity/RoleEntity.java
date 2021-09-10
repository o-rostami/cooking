package com.infosys.coocking.model.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.envers.Audited;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Audited
@Setter
@Getter
@Entity
@Table(name = RoleEntity.TABLE_NAME)
public class RoleEntity extends BaseEntity<Long> {

    public static final String TABLE_NAME = "ROLE";

    @Column(name = "NAME")
    private String name;
}
