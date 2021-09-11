package com.infosys.coocking.service.ingredient;

import com.infosys.coocking.model.dto.PagingRequest;
import com.infosys.coocking.model.dto.PagingResponse;
import com.infosys.coocking.model.entity.IngredientEntity;

import java.util.List;

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
     * Returns the details of Ingredient fetched
     *
     * @param ingredientId the id of the Ingredient needs to be fetched
     * @return the Ingredient entity fetched.
     */

    IngredientEntity getIngredientById(Long ingredientId);

    /**
     * Delete the specified Ingredient
     *
     * @param ingredientId the id of the Ingredient needs to be deleted
     */

    void deleteIngredientById(Long ingredientId);

    /**
     * Create the Ingredient and return the id of created one
     *
     * @param ingredientEntity contains detail of Ingredient which is going to be created
     * @return the Ingredient's id just created.
     */

    Long createIngredient(IngredientEntity ingredientEntity);

    /**
     * Modify and return the updated Ingredient.
     *
     * @param ingredientEntity contains detail of Ingredient is going to be updated
     * @return the Ingredient just updated.
     */

    IngredientEntity updateIngredient(IngredientEntity ingredientEntity);

    /**
     * Returns the all Ingredient for admin
     *
     * @return the Ingredient list.
     */
    List<IngredientEntity> getAllIngredient();


    /**
     * Returns the details of Ingredient based on the filed searched
     *
     * @param pagingRequest contains the search criteria and filters
     * @return the PagingResponse fetched.
     */

    PagingResponse<IngredientEntity> searchIngredient(PagingRequest pagingRequest);
}
