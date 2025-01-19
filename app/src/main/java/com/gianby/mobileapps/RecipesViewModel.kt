package com.gianby.mobileapps

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.RecipesList
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class RecipeUIState(
    val isLoading: Boolean = false,
    val recipes: List<Recipe> = emptyList()
)

class RecipesViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(RecipeUIState())
   // private val _recipes = MutableStateFlow<List<Recipe>>(emptyList())
    //val recipes: StateFlow<List<Recipe>> = _recipes.asStateFlow()
    val uiState: StateFlow<RecipeUIState> = _uiState.asStateFlow()
    private var searchJob: kotlinx.coroutines.Job? = null

    private val allRecipes = RecipesList.recipes

    init {
        viewModelScope.launch {
            delay(2000)
            _uiState.update { it.copy(
                isLoading = false,
                recipes = RecipesList.recipes
            ) }
        }
    }

    fun queryRecipes(query: String) {
        searchJob?.cancel()
        searchJob = viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, recipes = emptyList()) }

            delay(300)

            val filteredRecipes = if (query.length < 3) {
                RecipesList.recipes
            } else {
                RecipesList.recipes.filter { recipe ->
                    recipe.title.contains(query, ignoreCase = true)
                }
            }

            delay(2000)

            _uiState.update { it.copy(isLoading = false, recipes = filteredRecipes) }
        }
    }

    fun logout() {
        MobileApplication.getInstance().credentialsManager.logout()
    }
}
