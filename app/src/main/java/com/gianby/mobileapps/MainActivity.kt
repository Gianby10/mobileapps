package com.gianby.mobileapps

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Recycler
import androidx.recyclerview.widget.RecyclerView.ViewHolder
data class Recipe(val id: Int, val title: String, val description: String)
class MainActivity : AppCompatActivity(), RecipesAdapter.EventsHandler {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        val recyclerView = findViewById<RecyclerView>(R.id.recipesList)
        /*recyclerView.adapter = RecipesAdapter(
            listOf("Lorem","Ipsum","Saas","Cazzo")
        )

        recyclerView.layoutManager = LinearLayoutManager(this)*/

        val recipes = listOf(
            Recipe(1, "Recipe 1", "Description 1."),
            Recipe(2, "Recipe 2", "Description 2."),
            Recipe(3, "Recipe 3", "Description 3."),
            Recipe(4, "Recipe 4", "Description 4.")
        )

        val adapter = RecipesAdapter(recipes,this)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)
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

class RecipesAdapter(
    private val recipes: List<Recipe>,
    private val eventsHandler: EventsHandler
) : RecyclerView.Adapter<RecipesAdapter.ViewHolder>() {

    interface EventsHandler {
        fun onRecipeClicked(itemId: Int)
        fun onActionButtonClicked(itemId: Int, action: String)
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val title: TextView = view.findViewById(R.id.title)
        val description: TextView = view.findViewById(R.id.description)
        val shareButton: ImageButton = view.findViewById(R.id.shareButton)
        val likeButton: ImageButton = view.findViewById(R.id.likeButton)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_recipe, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val recipe = recipes[position]
        holder.title.text = recipe.title
        holder.description.text = recipe.description


        holder.itemView.setOnClickListener {
            val context = holder.itemView.context
            val intent = Intent(context, RecipeDetailsActivity::class.java)
            intent.putExtra("RECIPE_TITLE", recipe.title)
            intent.putExtra("RECIPE_DESCRIPTION", recipe.description)
            intent.putExtra("RECIPE_IMAGE", R.drawable.ic_launcher_background) // Cambia con l'immagine effettiva
            context.startActivity(intent)
        }

        // Handle clicks on specific buttons
        holder.shareButton.setOnClickListener {
            eventsHandler.onActionButtonClicked(recipe.id, "share")
        }
        holder.likeButton.setOnClickListener {
            eventsHandler.onActionButtonClicked(recipe.id, "like")
        }
    }

    override fun getItemCount(): Int = recipes.size
}
