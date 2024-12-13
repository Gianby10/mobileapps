package com.gianby.mobileapps

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class RecipeDetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recipe_details)

        val recipeTitle = findViewById<TextView>(R.id.recipeTitle)
        val recipeDescription = findViewById<TextView>(R.id.recipeDescription)
        val recipeImage = findViewById<ImageView>(R.id.recipeImage)


        val title = intent.getStringExtra("RECIPE_TITLE")
        val description = intent.getStringExtra("RECIPE_DESCRIPTION")
        val imageResId = intent.getIntExtra("RECIPE_IMAGE", R.drawable.ic_launcher_background)


        recipeTitle.text = title
        recipeDescription.text = description
        recipeImage.setImageResource(imageResId)
    }
}