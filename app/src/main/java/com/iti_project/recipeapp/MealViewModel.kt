package com.iti_project.recipeapp.viewmodel

import android.content.SharedPreferences
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.iti_project.recipeapp.apiFiles.Repo.IRepo
import com.iti_project.recipeapp.mealCatogry.Meal
import com.iti_project.recipeapp.mealFullDetail.mealfulldetailResponse
import kotlinx.coroutines.launch
import android.util.Log
import com.iti_project.recipeapp.apiFiles.Repo.Repo

class MealViewModel(private val repo: IRepo = Repo()) : ViewModel() {


    private val _mealsByCategory = MutableLiveData<List<Meal>>()
    val mealsByCategory: LiveData<List<Meal>> get() = _mealsByCategory

    private val _mealDetails = MutableLiveData<mealfulldetailResponse>()
    val mealDetails: LiveData<mealfulldetailResponse> get() = _mealDetails

    fun fetchMealsByCategory(category: String) {
        viewModelScope.launch {
            try {
                val response = repo.getMealsByCategory(category)
                _mealsByCategory.postValue(response)
            } catch (e: Exception) {
                Log.e("MealViewModel", "Error fetching meals by category", e)
                _mealsByCategory.postValue(emptyList())
            }
        }
    }

    fun fetchMealDetailsById(id: String) {
        viewModelScope.launch {
            try {
                val response = repo.getMealDetailsById(id)
                _mealDetails.postValue(response)
            } catch (e: Exception) {
                Log.e("MealViewModel", "Error fetching meal details by ID", e)
                _mealDetails.postValue(mealfulldetailResponse(emptyList()))
            }
        }
    }
}