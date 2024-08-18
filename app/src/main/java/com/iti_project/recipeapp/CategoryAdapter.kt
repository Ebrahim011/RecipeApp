package com.iti_project.recipeapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.iti_project.recipeapp.apiFiles.catogries.Category

class CategoryAdapter(
    private val categories: List<Category>,
    private val listener: OnItemClickListener
) : RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>() {

    interface OnItemClickListener {
        fun onItemClick(category: Category)
    }

    inner class CategoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
        val categoryImage: ImageView = itemView.findViewById(R.id.categoryImage)
        val categoryTitle: TextView = itemView.findViewById(R.id.categoryTitle)
        val categoryDescription: TextView = itemView.findViewById(R.id.categoryDescription)

        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            val position = adapterPosition
            if (position != RecyclerView.NO_POSITION) {
                listener.onItemClick(categories[position])
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_category, parent, false)
        return CategoryViewHolder(view)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        val currentCategory = categories[position]
        holder.categoryTitle.text = currentCategory.strCategory
        holder.categoryDescription.text = currentCategory.strCategoryDescription
        Glide.with(holder.itemView.context).load(currentCategory.strCategoryThumb).into(holder.categoryImage)
    }

    override fun getItemCount(): Int {
        return categories.size
    }
}