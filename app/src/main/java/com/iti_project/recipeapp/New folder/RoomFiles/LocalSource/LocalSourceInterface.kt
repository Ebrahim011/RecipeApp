package com.iti_project.recipeapp.RoomFiles.LocalSource

import androidx.lifecycle.LiveData
import com.iti_project.recipeapp.RoomFiles.User

interface LocalSourceInterface {
    fun getFavorites(userId: Int): LiveData<List<Int>>
    fun checkIfEmailExists(email: String): Boolean
    fun getPasswordByEmail(email: String): String?
    fun getUserName(userId: Int): String?
    fun addAccount(user: User)
    fun updateFavorites(userId: Int, favorites: List<Int>)
}