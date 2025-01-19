package com.gianby.mobileapps

import RecipesAdapter
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.SearchView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Recycler
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.myapplication.RecipesList
import com.google.android.material.progressindicator.CircularProgressIndicator
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

data class Recipe(val id: Int, val title: String, val description: String)
class MainActivity : AppCompatActivity(), RecipesAdapter.EventsHandler {
    private val recipeViewModel: RecipesViewModel by viewModels()
    private lateinit var recyclerView: RecyclerView
    private lateinit var progressIndicator: CircularProgressIndicator
    private lateinit var adapter: RecipesAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)



        setupViews()
        setupRecyclerView()
        setupSearchView()
        observeUiState()
        observeLoginState()

    }

    private fun setupRecyclerView() {
        adapter = RecipesAdapter(emptyList(), this)




        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)


    }

    private fun setupViews() {
        recyclerView = findViewById(R.id.recipesList)
        progressIndicator = findViewById(R.id.progressIndicator)
    }

     override fun onRecipeClicked(itemId: Int) {
        Toast.makeText(this, "Recipe clicked: ID $itemId", Toast.LENGTH_SHORT).show()
        // Log.d("MainActivity", "Recipe clicked: ID $itemId")
    }

    override fun onActionButtonClicked(itemId: Int, action: String) {
        Toast.makeText(this, "Action '$action' on Recipe ID $itemId", Toast.LENGTH_SHORT).show()
       // Log.d("MainActivity", "Action '$action' on Recipe ID $itemId")
    }

    private fun setupSearchView() {
        val searchView: SearchView = findViewById(R.id.recipeSearchView)
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean = true
            override fun onQueryTextChange(newText: String?): Boolean {
                recipeViewModel.queryRecipes(newText.orEmpty())
                return true
            }
        })
    }

    private fun observeUiState() {
        lifecycleScope.launch {
            recipeViewModel.uiState.collectLatest { state ->
                progressIndicator.isVisible = state.isLoading
                recyclerView.isVisible = !state.isLoading
                adapter.updateRecipes(state.recipes)
            }
        }
    }
    private fun observeLoginState() {
        lifecycleScope.launch {
            MobileApplication.getInstance().credentialsManager.isLoggedIn.collectLatest { isLoggedIn ->
                if (!isLoggedIn) {
                    startActivity(Intent(this@MainActivity, AuthActivity::class.java))
                    finish()
                }
            }
        }
    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.actionLogout -> {
                recipeViewModel.logout()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}


