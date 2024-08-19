package com.iti_project.recipeapp.RoomFolder.RoomFiles.Repo

import android.content.Context
import androidx.lifecycle.LiveData
import com.iti_project.recipeapp.RoomFolder.RoomFiles.LocalSource.LocalSource
import com.iti_project.recipeapp.RoomFolder.RoomFiles.LocalSource.LocalSourceInterface
import com.iti_project.recipeapp.RoomFolder.RoomFiles.User

class UserRepository(private val localSource: LocalSourceInterface) : UserRepositoryInterface {

    companion object {
        @Volatile
        private var instance: UserRepository? = null

        fun getInstance(context: Context): UserRepository {
            return instance ?: synchronized(this) {
                val localSource = LocalSource.getInstance(context)
                UserRepository(localSource).also { instance = it }
            }
        }
    }

    override fun getFavorites(userId: Int): LiveData<String> {
        return localSource.getFavorites(userId)
    }

    override fun checkIfEmailExists(email: String): Boolean {
        return localSource.checkIfEmailExists(email)
    }

    override fun getPasswordByEmail(email: String): String? {
        return localSource.getPasswordByEmail(email)
    }

    override fun getUserId(email: String): Int? {
        return localSource.getUserId(email)
    }

    override fun addAccount(user: User) {
        localSource.addAccount(user)
    }

    override fun updateFavorites(userId: Int, favorites: String) {
        localSource.updateFavorites(userId, favorites)
    }
}