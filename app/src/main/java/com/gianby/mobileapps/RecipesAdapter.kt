import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.gianby.mobileapps.R
import com.gianby.mobileapps.Recipe
import com.gianby.mobileapps.RecipeDetailsActivity

class RecipesAdapter(
    private var recipes: List<Recipe>,
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

    fun updateRecipes(newRecipes: List<Recipe>) {
        val diffCallBack = object : DiffUtil.Callback() {
            override fun getOldListSize(): Int = recipes.size
            override fun getNewListSize(): Int = newRecipes.size
            override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
                return recipes[oldItemPosition].id == newRecipes[newItemPosition].id
            }
            override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
                return recipes[oldItemPosition] == newRecipes[newItemPosition]
            }
        }
        val diffResult = DiffUtil.calculateDiff(diffCallBack)
        recipes = newRecipes
        diffResult.dispatchUpdatesTo(this)
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