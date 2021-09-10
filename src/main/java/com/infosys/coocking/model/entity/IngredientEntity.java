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
@Table(name = IngredientEntity.TABLE_NAME)
public class IngredientEntity extends BaseEntity<Long> {

    public static final String TABLE_NAME = "INGREDIENT";

    @Column(name = "NAME",unique = true)
    private String name;

    @NotAudited
    @OneToMany(mappedBy = "ingredient", fetch = FetchType.LAZY,cascade = CascadeType.ALL,orphanRemoval = true)
    private List<RecipeIngredientEntity> recipeIngredients;

}
