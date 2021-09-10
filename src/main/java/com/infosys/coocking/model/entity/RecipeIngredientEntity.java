package com.infosys.coocking.model.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;

import javax.persistence.*;

@Audited
@Setter
@Getter
@Entity
@Table(name = RecipeIngredientEntity.TABLE_NAME)
public class RecipeIngredientEntity extends BaseEntity<Long> {

    public static final String TABLE_NAME = "RECIPE_INGREDIENT";

    @Column(name = "amount")
    private Double amount;

    @NotAudited
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "RECIPE_ID", referencedColumnName = "ID")
    private RecipeEntity recipe;

    @NotAudited
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "INGREDIENT_ID", referencedColumnName = "ID")
    private IngredientEntity ingredient;

    @NotAudited
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MEASUREMENT_UNIT_ID", referencedColumnName = "ID")
    private MeasurementUnitEntity measurementUnit;


}
