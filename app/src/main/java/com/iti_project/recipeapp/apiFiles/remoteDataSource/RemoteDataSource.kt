package com.iti_project.recipeapp.apiFiles.remoteDataSource

import android.util.Log
import com.iti_project.recipeapp.apiFiles.RetrofitClient
import com.iti_project.recipeapp.apiFiles.catogries.CategoryList
import com.iti_project.recipeapp.mealCatogry.Meal
import com.iti_project.recipeapp.mealCatogry.MealListResponse
import com.iti_project.recipeapp.mealFullDetail.mealfulldetailResponse


class RemoteDataSource : IRemoteDataSource {
    override suspend fun getMealsByCategory(category: String): List<Meal> {
        return try {
            val response: MealListResponse = RetrofitClient.getService().getMealsByCategory(category)
            response.meals
        } catch (e: Exception) {
            Log.e("RemoteDataSource", "Error fetching meals by category", e)
            emptyList()
        }
    }

    override suspend fun getMealDetailsById(id: String): mealfulldetailResponse {
        return try {
            RetrofitClient.getService().getMealDetailsById(id)
        } catch (e: Exception) {
            Log.e("RemoteDataSource", "Error fetching meal details by ID", e)
            mealfulldetailResponse(emptyList())
        }
    }

    override suspend fun getCategoryList(): CategoryList {
        return try {
            RetrofitClient.getService().getCategoryList()
        } catch (e: Exception) {
            Log.e("RemoteDataSource", "Error fetching category list", e)
            CategoryList(emptyList())
        }
    }

    override suspend fun searchMealsByName(name: String): List<Meal> {
        return try {
            val response: MealListResponse = RetrofitClient.getService().searchMealsByName(name)
            response.meals
        } catch (e: Exception) {
            Log.e("RemoteDataSource", "Error searching meals by name", e)
            emptyList()
        }
    }

    override suspend fun getRandomMeal(): mealfulldetailResponse {
        return try {
            RetrofitClient.getService().getRandomMeal()
        } catch (e: Exception) {
            Log.e("RemoteDataSource", "Error fetching random meal", e)
            mealfulldetailResponse(emptyList())
        }
    }
}