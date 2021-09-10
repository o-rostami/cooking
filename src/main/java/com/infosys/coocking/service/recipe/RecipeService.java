package com.infosys.coocking.service.recipe;

import com.infosys.coocking.model.dto.PagingRequest;
import com.infosys.coocking.model.dto.PagingResponse;
import com.infosys.coocking.model.dto.RecipeDto;
import com.infosys.coocking.model.dto.RecipeIngredientDto;
import com.infosys.coocking.model.entity.RecipeEntity;
import com.infosys.coocking.model.entity.RecipeIngredientEntity;

import java.util.List;
import java.util.Set;

/**
 * A <i>RecipeService</i>. This interface has responsibility to do CRUD action
 * on the recipe <p>
 * The <tt>RecipeService</tt> interface provides six methode for doing its job
 * the role service implemented by <tt>RecipeServiceImpl</tt> class.<p>
 *
 * @author Omid Rostami
 */

public interface RecipeService {

    /**
     * Create the Recipe and return the id of created one
     *
     * @param recipeEntity contains detail of Recipe which is going to be created
     * @return the Recipe's id just created.
     */

    Long createRecipe(RecipeEntity recipeEntity);

    /**
     * Modify and return the updated Recipe.
     *
     * @param recipeEntity contains detail of Recipe is going to be updated
     * @return the Product  just updated.
     */

    RecipeEntity updateRecipe(RecipeEntity recipeEntity);


    /**
     * Returns the details of Recipe fetched
     *
     * @param recipeId the id of the Recipe needs to be fetched
     * @return the Recipe entity fetched.
     */

    RecipeEntity getRecipeById(Long recipeId);


    /**
     * Delete the specified Recipe
     *
     * @param recipeId the id of the Recipe needs to be deleted
     */

    void deleteRecipeById(Long recipeId);

    /**
     * Returns the all Recipe for every user
     *
     * @return the Recipe list.
     */
    List<RecipeEntity> getAllProducts();

    /**
     * Returns the details of Recipe based on the filed searched
     *
     * @param pagingRequest contains the search criteria and filters
     * @return the PagingResponse fetched.
     */

    PagingResponse<RecipeEntity> searchRecipe(PagingRequest pagingRequest);

    /**
     * adding ingredients to specific Recipe
     *
     * @param recipeId contains the id of recipe
     * @param ingredients contains the list of ingredients
     * @return the RecipeEntity with added ingredients.
     */

    RecipeEntity addIngredients(Long recipeId, List<RecipeIngredientEntity> ingredients);
}
