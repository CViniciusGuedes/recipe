package com.recipe.service;

import com.recipe.model.Recipe;
import com.recipe.model.Usuario;

import java.util.List;

public interface RecipeService {

    public Recipe createRecipe(Recipe recipe, Usuario usuario);

    public Recipe findRecipeById(Long id) throws Exception;

    public void deleteRecipe(Long id) throws Exception;

    public Recipe updateRecipe(Recipe recipe, Long id) throws Exception;

    public List<Recipe> findAllRecipe();

    public Recipe likeRecipe(Long recipeId, Usuario usuario) throws Exception;
}
