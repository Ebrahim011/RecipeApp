package com.iti_project.recipeapp.RoomFolder

import android.app.Application
import android.content.Context.MODE_PRIVATE
import android.widget.Toast
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.iti_project.recipeapp.RoomFolder.RoomFiles.Repo.UserRepository
import com.iti_project.recipeapp.RoomFolder.RoomFiles.Converters
import com.iti_project.recipeapp.RoomFolder.RoomFiles.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class UserViewModel(application: Application) : AndroidViewModel(application) {

    private val _emailExists = MutableLiveData<Boolean>()
    val emailExists: LiveData<Boolean> get() = _emailExists

    private val _password = MutableLiveData<String?>()
    val password: LiveData<String?> get() = _password

    private val _userId = MutableLiveData<Int?>()
    val userId: LiveData<Int?> get() = _userId
    private val userRepository: UserRepository = UserRepository.getInstance(application)
    private val _favorites = MutableLiveData<List<Int>>()
    val favorites: MutableLiveData<List<Int>> get() = _favorites

    fun getFavorites(userId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            val favoritesString = userRepository.getFavorites(userId)
            _favorites.postValue(Converters().toListOfInt(favoritesString ?: ""))
        }
    }

    fun getFavoritesAsList(userId: Int): List<Int> {
        return Converters().toListOfInt(userRepository.getFavorites(userId) ?: "")
    }

    fun addFavorite(userId: Int, mealId: Int) {
        viewModelScope.launch(Dispatchers.IO) {

            val currentFavorites = _favorites.value?.toMutableList() ?: mutableListOf()
            if (!currentFavorites.contains(mealId)) {
                currentFavorites.add(mealId)
                val favoritesString = Converters().fromListOfInt(currentFavorites)
                userRepository.updateFavorites(userId, favoritesString)
                _favorites.postValue(currentFavorites)
                getFavorites(userId)

            }

        }


    }

    fun removeFavorite(userId: Int, mealId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            val currentFavorites = _favorites.value?.toMutableList() ?: mutableListOf()
            if (currentFavorites.contains(mealId)) {
                currentFavorites.remove(mealId)
                val favoritesString = Converters().fromListOfInt(currentFavorites)
                userRepository.updateFavorites(userId, favoritesString)
                _favorites.postValue(currentFavorites)
                getFavorites(userId)
            }
        }

    }

    fun checkIfEmailExists(email: String) {
       viewModelScope.launch(Dispatchers.IO) {
          _emailExists.postValue(userRepository.checkIfEmailExists(email))
       }
    }
    fun checkIfEmailExistsBoolean(email: String):Boolean {
    return userRepository.checkIfEmailExists(email)}

    fun getPasswordByEmail(email: String) {
        viewModelScope.launch(Dispatchers.IO) {
            _password.postValue(userRepository.getPasswordByEmail(email))
        }
    }

    fun getUserId(email: String) {
        viewModelScope.launch(Dispatchers.IO) {
            _userId.postValue(userRepository.getUserId(email))
        }
    }

    fun addAccount(user: User) {
        viewModelScope.launch(Dispatchers.IO) {
            userRepository.addAccount(user)
        }
    }
}