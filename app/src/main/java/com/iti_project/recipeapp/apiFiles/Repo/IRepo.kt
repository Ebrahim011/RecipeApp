package com.iti_project.recipeapp.apiFiles.Repo

import com.iti_project.recipeapp.apiFiles.catogries.CategoryList
import com.iti_project.recipeapp.mealCatogry.Meal
import com.iti_project.recipeapp.mealFullDetail.mealfulldetailResponse

interface IRepo {
    suspend fun getMealsByCategory(category: String): List<Meal>
    suspend fun getMealDetailsById(id: String): mealfulldetailResponse
    suspend fun getCategoryList(): CategoryList
    suspend fun searchMealsByName(name: String): List<Meal>
}