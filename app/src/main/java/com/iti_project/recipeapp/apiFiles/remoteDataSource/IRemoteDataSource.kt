package com.iti_project.recipeapp.apiFiles.remoteDataSource

import com.iti_project.recipeapp.apiFiles.catogries.CategoryList
import com.iti_project.recipeapp.mealCatogry.Meal
import com.iti_project.recipeapp.mealFullDetail.mealfulldetailResponse

interface IRemoteDataSource {
    suspend fun getMealsByCategory(category: String): List<Meal>
    suspend fun getMealDetailsById(id: String): mealfulldetailResponse
    suspend fun getCategoryList(): CategoryList
    suspend fun searchMealsByName(name: String): List<Meal>
}