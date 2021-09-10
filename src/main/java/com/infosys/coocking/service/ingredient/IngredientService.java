package com.infosys.coocking.service.ingredient;

import com.infosys.coocking.model.entity.IngredientEntity;

/**
 * A <i>IngredientService</i>. This interface has responsibility to do CRUD action
 * on the Ingredient <p>
 * The <tt>IngredientService</tt> interface provides some methods for completing its task
 * the role service implemented by <tt>IngredientServiceImpl</tt> class.<p>
 *
 * @author Omid Rostami
 */

public interface IngredientService {

    /**
     * Returns the details of Recipe fetched
     *
     * @param ingredientId the id of the Recipe needs to be fetched
     * @return the Ingredient entity fetched.
     */

    IngredientEntity getIngredientById(Long ingredientId);

}
