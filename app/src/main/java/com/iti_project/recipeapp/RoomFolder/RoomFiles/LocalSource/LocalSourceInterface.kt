package com.iti_project.recipeapp.RoomFolder.RoomFiles.LocalSource

import androidx.lifecycle.LiveData
import com.iti_project.recipeapp.RoomFolder.RoomFiles.User

interface LocalSourceInterface {
    fun getFavorites(userId: Int): LiveData<String>
    fun checkIfEmailExists(email: String): Boolean
    fun getPasswordByEmail(email: String): String?
    fun getUserId(email: String): Int?
    fun addAccount(user: User)
    fun updateFavorites(userId: Int, favorites: String)
    //nader
    fun updateProfile(userInfo : User)
    fun getUserByEmail(email: String): LiveData<User>
    fun getUserById(userId: Int): LiveData<User>
}