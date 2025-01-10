package com.gianby.mobileapps

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class RecipesViewModel : ViewModel() {
    private val _recipes = MutableStateFlow<List<Recipe>>(emptyList())
    val recipes: StateFlow<List<Recipe>> = _recipes.asStateFlow()

    private val allRecipes = listOf(
        Recipe(1, "Recipe 1", "Description 1."),
        Recipe(2, "Recipe 2", "Description 2."),
        Recipe(3, "Recipe 3", "Description 3."),
        Recipe(4, "Recipe 4", "Description 4.")
    )

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
