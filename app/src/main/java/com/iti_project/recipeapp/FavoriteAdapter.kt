package com.iti_project.recipeapp

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.iti_project.recipeapp.mealCatogry.Meal

class FavoriteAdapter(
    private var meals: List<Meal>,
    private val itemClickListener: OnItemClickListener
) : RecyclerView.Adapter<FavoriteAdapter.FavoriteViewHolder>() {

    interface OnItemClickListener {
        fun onItemClick(meal: Meal)
    }

    class FavoriteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val mealTitle: TextView = itemView.findViewById(R.id.mealTitle)
        val mealImage: ImageView = itemView.findViewById(R.id.mealImage)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_meal, parent, false)
        return FavoriteViewHolder(view)
    }

    override fun onBindViewHolder(holder: FavoriteViewHolder, position: Int) {
        val meal = meals[position]
        holder.mealTitle.text = meal.strMeal
        Glide.with(holder.itemView.context)
            .load(meal.strMealThumb)
            .into(holder.mealImage)
        holder.itemView.setOnClickListener {
            itemClickListener.onItemClick(meal)
        }
    }

    override fun getItemCount(): Int {
        return meals.size
    }


    fun updateMeals(newMeals: List<Meal>) {
        meals = newMeals
        notifyDataSetChanged()
    }
}