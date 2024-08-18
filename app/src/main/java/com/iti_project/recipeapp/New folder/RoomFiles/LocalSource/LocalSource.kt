package com.iti_project.recipeapp.RoomFiles.LocalSource

import android.content.Context
import androidx.lifecycle.LiveData
import com.iti_project.recipeapp.RoomFiles.RoomDataBase
import com.iti_project.recipeapp.RoomFiles.User
import com.iti_project.recipeapp.RoomFiles.UserDao

class LocalSource(private val userDao: UserDao) : LocalSourceInterface {

    companion object {
        @Volatile
        private var INSTANCE: LocalSource? = null

        fun getInstance(context: Context): LocalSource {
            return INSTANCE ?: synchronized(this) {
                val database = RoomDataBase.getInstance(context)
                val instance = LocalSource(database.getUserDao())
                INSTANCE = instance
                instance
            }
        }
    }

    override fun getFavorites(userId: Int): LiveData<List<Int>> {
        return userDao.getFavorites(userId)
    }

    override fun checkIfEmailExists(email: String): Boolean {
        return userDao.checkIfEmailExists(email)
    }

    override fun getPasswordByEmail(email: String): String? {
        return userDao.getPasswordByEmail(email)
    }

    override fun getUserName(userId: Int): String? {
        return userDao.getUserName(userId)
    }

    override fun addAccount(user: User) {
        userDao.addAccount(user)
    }

    override fun updateFavorites(userId: Int, favorites: List<Int>) {
        userDao.updateFavorites(userId, favorites)
    }
}