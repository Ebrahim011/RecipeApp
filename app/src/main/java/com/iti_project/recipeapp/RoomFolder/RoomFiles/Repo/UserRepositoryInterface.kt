package com.iti_project.recipeapp.RoomFolder.RoomFiles.Repo

import androidx.lifecycle.LiveData
import com.iti_project.recipeapp.RoomFolder.RoomFiles.User

interface UserRepositoryInterface {
    fun getFavorites(userId: Int): String
    fun checkIfEmailExists(email: String): Boolean
    fun getPasswordByEmail(email: String): String?
    fun getUserId(email: String): Int?
    fun addAccount(user: User)
    fun updateFavorites(userId: Int, favorites: String)
}