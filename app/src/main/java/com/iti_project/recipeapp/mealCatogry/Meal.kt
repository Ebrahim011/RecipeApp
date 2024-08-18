package com.iti_project.recipeapp.mealCatogry

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Meal(
    val idMeal: String,
    val strMeal: String,
    val strMealThumb: String
) : Parcelable