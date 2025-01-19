package com.gianby.mobileapps

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.RecipesList
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class RecipesViewModel : ViewModel() {
    private val _recipes = MutableStateFlow<List<Recipe>>(emptyList())
    val recipes: StateFlow<List<Recipe>> = _recipes.asStateFlow()

    private val allRecipes = RecipesList.recipes

    init {
        _recipes.value = allRecipes
    }

    fun searchRecipes(query: String) {
        if (query.length < 3) {
            _recipes.update { allRecipes }
        } else {
            val filteredRecipes = allRecipes.filter {
                it.title.contains(query, ignoreCase = true) ||
                        it.description.contains(query, ignoreCase = true)
            }
            _recipes.update { filteredRecipes }
        }
    }
}
