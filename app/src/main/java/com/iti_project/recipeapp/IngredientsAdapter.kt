package com.iti_project.recipeapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class IngredientsAdapter(
    private val ingredients: List<String>,
    private val measures: List<String>
) : RecyclerView.Adapter<IngredientsAdapter.IngredientViewHolder>() {

    class IngredientViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val ingredientName: TextView = itemView.findViewById(R.id.ingredientName)
        val ingredientMeasure: TextView = itemView.findViewById(R.id.ingredientMeasure)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IngredientViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_ingredient, parent, false)
        return IngredientViewHolder(view)
    }

    override fun onBindViewHolder(holder: IngredientViewHolder, position: Int) {
        holder.ingredientName.text = ingredients[position]
        holder.ingredientMeasure.text = measures[position]
    }

    override fun getItemCount(): Int {
        return ingredients.size
    }
}