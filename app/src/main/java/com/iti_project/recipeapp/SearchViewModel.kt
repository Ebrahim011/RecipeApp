package com.iti_project.recipeapp

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.iti_project.recipeapp.apiFiles.Repo.IRepo
import com.iti_project.recipeapp.apiFiles.Repo.Repo
import com.iti_project.recipeapp.mealCatogry.Meal
import kotlinx.coroutines.launch

class SearchViewModel(private val repo: IRepo = Repo()) : ViewModel() {

    private val _searchResults = MutableLiveData<List<Meal>>()
    val searchResults: LiveData<List<Meal>> get() = _searchResults

    fun searchMealsByName(name: String) {
        viewModelScope.launch {
            val results = repo.searchMealsByName(name)
            _searchResults.postValue(results ?: emptyList())
        }
    }
}