package com.iti_project.recipeapp

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.iti_project.recipeapp.apiFiles.Repo.IRepo
import com.iti_project.recipeapp.apiFiles.Repo.Repo
import com.iti_project.recipeapp.apiFiles.catogries.CategoryList
import kotlinx.coroutines.launch
import android.util.Log
import com.iti_project.recipeapp.mealCatogry.Meal
import com.iti_project.recipeapp.mealFullDetail.MealFullDetails

class CategoryListViewModel(private val repo: IRepo = Repo()) : ViewModel() {

    private val _categoryList = MutableLiveData<CategoryList>()
    val categoryList: LiveData<CategoryList> get() = _categoryList
    private val _oneRandomMeal=MutableLiveData<MealFullDetails>()
    val oneRandomMeal:LiveData<MealFullDetails> get() = _oneRandomMeal
    fun fetchCategoryList() {
        viewModelScope.launch {
            try {
                val response = repo.getCategoryList()
                _categoryList.postValue(response)

            } catch (e: Exception) {
                Log.e("CategoryListViewModel", "Error fetching category list", e)
                _categoryList.postValue(CategoryList(emptyList()))
            }
        }
    }
    fun fetchRandomMeal(){
        viewModelScope.launch {
            try {
                val response = repo.getRandomMeal().meals.first()
                _oneRandomMeal.postValue(response)
            } catch (e: Exception) {
                Log.e("CategoryListViewModel", "Error fetching random meal", e)
            }
        }
    }
}