package com.infosys.coocking.model.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;

import javax.persistence.*;
import java.util.List;

@Audited
@Setter
@Getter
@Entity
@Table(name = RecipeEntity.TABLE_NAME)
public class RecipeEntity extends BaseEntity<Long> {

    public static final String TABLE_NAME = "RECIPE";

    @Column(name = "NAME")
    private String name;

    @Column(name = "INSTRUCTIONS")
    private String instructions;

    @NotAudited
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID", referencedColumnName = "ID")
    private UserEntity user;

    @NotAudited
    @OneToMany(mappedBy = "recipe", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<RecipeIngredientEntity> recipeIngredients;

}
