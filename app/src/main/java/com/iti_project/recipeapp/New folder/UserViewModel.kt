package com.iti_project.recipeapp.ViewModels

import com.iti_project.recipeapp.RoomFiles.Repo.UserRepository


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.iti_project.recipeapp.RoomFiles.User
import kotlinx.coroutines.launch

class UserViewModel(private val userRepository: UserRepository) : ViewModel() {

    private val _favorites = MutableLiveData<List<Int>>()
    val favorites: LiveData<List<Int>> get() = _favorites

    private val _emailExists = MutableLiveData<Boolean>()
    val emailExists: LiveData<Boolean> get() = _emailExists

    private val _password = MutableLiveData<String?>()
    val password: LiveData<String?> get() = _password

    private val _userName = MutableLiveData<String?>()
    val userName: LiveData<String?> get() = _userName

    fun getFavorites(userId: Int) {
        viewModelScope.launch {
            _favorites.postValue(userRepository.getFavorites(userId).value)
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

    fun getUserName(userId: Int) {
        viewModelScope.launch {
            _userName.postValue(userRepository.getUserName(userId))
        }
    }

    fun addAccount(user: User) {
        viewModelScope.launch {
            userRepository.addAccount(user)
        }
    }

    fun updateFavorites(userId: Int, favorites: List<Int>) {
        viewModelScope.launch {
            userRepository.updateFavorites(userId, favorites)
        }
    }
}