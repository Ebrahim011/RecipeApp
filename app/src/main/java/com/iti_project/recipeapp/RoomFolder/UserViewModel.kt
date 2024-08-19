package com.iti_project.recipeapp.RoomFolder

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.iti_project.recipeapp.RoomFolder.RoomFiles.Repo.UserRepository
import com.iti_project.recipeapp.RoomFolder.RoomFiles.User
import com.iti_project.recipeapp.RoomFolder.RoomFiles.Converters
import kotlinx.coroutines.launch

class UserViewModel(application: Application) : AndroidViewModel(application) {

    private val _emailExists = MutableLiveData<Boolean>()
    val emailExists: LiveData<Boolean> get() = _emailExists

    private val _password = MutableLiveData<String?>()
    val password: LiveData<String?> get() = _password

    private val _userId = MutableLiveData<Int?>()
    val userId: LiveData<Int?> get() = _userId
    private val userRepository: UserRepository = UserRepository.getInstance(application)
    private val _favorites = MutableLiveData<List<Int>>()
    val favorites: LiveData<List<Int>> get() = _favorites

    fun getFavorites(userId: Int) {
        viewModelScope.launch {
            val favoritesString = userRepository.getFavorites(userId).value
            _favorites.postValue(Converters().toListOfInt(favoritesString ?: ""))
        }
    }

    fun updateFavorites(userId: Int, favorites: List<Int>) {
        viewModelScope.launch {
            val favoritesString = Converters().fromListOfInt(favorites)
            userRepository.updateFavorites(userId, favoritesString)
            _favorites.postValue(favorites) // Update the LiveData after updating the database
        }
    }



    fun checkIfEmailExists(email: String) {
        viewModelScope.launch {
            _emailExists.postValue(userRepository.checkIfEmailExists(email))
        }
    }

    fun getPasswordByEmail(email: String) {
        viewModelScope.launch {
            _password.postValue(userRepository.getPasswordByEmail(email))
        }
    }

    fun getUserId(email: String) {
        viewModelScope.launch {
            _userId.postValue(userRepository.getUserId(email))
        }
    }

    fun addAccount(user: User) {
        viewModelScope.launch {
            userRepository.addAccount(user)
        }
    }


}