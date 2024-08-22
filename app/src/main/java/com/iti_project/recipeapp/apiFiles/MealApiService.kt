package com.iti_project.recipeapp.apiFiles

import com.iti_project.recipeapp.apiFiles.catogries.CategoryList
import com.iti_project.recipeapp.mealCatogry.MealListResponse
import com.iti_project.recipeapp.mealFullDetail.mealfulldetailResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface MealApiService {
    @GET("filter.php")
    suspend fun getMealsByCategory(@Query("c") category: String): MealListResponse

    @GET("lookup.php")
    suspend fun getMealDetailsById(@Query("i") id: String): mealfulldetailResponse

    @GET("categories.php")
    suspend fun getCategoryList(): CategoryList

    @GET("search.php")
    suspend fun searchMealsByName(@Query("s") name: String): MealListResponse

    @GET("random.php")
    suspend fun getRandomMeal(): mealfulldetailResponse
}