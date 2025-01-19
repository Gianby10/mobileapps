package com.gianby.mobileapps

import RecipesAdapter
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.SearchView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Recycler
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.myapplication.RecipesList
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

data class Recipe(val id: Int, val title: String, val description: String)
class MainActivity : AppCompatActivity(), RecipesAdapter.EventsHandler {
    private val recipeViewModel: RecipesViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        val recyclerView = findViewById<RecyclerView>(R.id.recipesList)
        val searchView = findViewById<SearchView>(R.id.recipeSearchView)
        val adapter = RecipesAdapter(emptyList(), this)


        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        lifecycleScope.launch {
            recipeViewModel.recipes.collectLatest { recipes ->
                if (adapter.itemCount != recipes.size) {
                    adapter.updateRecipes(recipes)
                }
            }
        }

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                recipeViewModel.searchRecipes(newText.orEmpty())
                return true
            }
        })
    }

     override fun onRecipeClicked(itemId: Int) {
        Toast.makeText(this, "Recipe clicked: ID $itemId", Toast.LENGTH_SHORT).show()
        // Log.d("MainActivity", "Recipe clicked: ID $itemId")
    }

    override fun onActionButtonClicked(itemId: Int, action: String) {
        Toast.makeText(this, "Action '$action' on Recipe ID $itemId", Toast.LENGTH_SHORT).show()
       // Log.d("MainActivity", "Action '$action' on Recipe ID $itemId")
    }
}


