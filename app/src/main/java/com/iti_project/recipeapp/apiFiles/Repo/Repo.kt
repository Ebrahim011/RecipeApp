package com.iti_project.recipeapp.apiFiles.Repo

import com.iti_project.recipeapp.apiFiles.catogries.CategoryList
import com.iti_project.recipeapp.apiFiles.remoteDataSource.IRemoteDataSource
import com.iti_project.recipeapp.apiFiles.remoteDataSource.RemoteDataSource
import com.iti_project.recipeapp.mealCatogry.Meal
import com.iti_project.recipeapp.mealFullDetail.mealfulldetailResponse

class Repo(private val remoteDataSource: IRemoteDataSource = RemoteDataSource()) : IRepo {
    override suspend fun getMealsByCategory(category: String): List<Meal> {
        return remoteDataSource.getMealsByCategory(category)
    }

    override suspend fun getMealDetailsById(id: String): mealfulldetailResponse {
        return remoteDataSource.getMealDetailsById(id)
    }

    override suspend fun getCategoryList(): CategoryList {
        return remoteDataSource.getCategoryList()
    }

    override suspend fun searchMealsByName(name: String): List<Meal> {
        return remoteDataSource.searchMealsByName(name)
    }
}