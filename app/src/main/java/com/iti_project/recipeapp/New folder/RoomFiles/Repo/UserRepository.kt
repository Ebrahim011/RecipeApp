package com.iti_project.recipeapp.RoomFiles.Repo

import android.content.Context
import androidx.lifecycle.LiveData
import com.iti_project.recipeapp.RoomFiles.LocalSource.LocalSource
import com.iti_project.recipeapp.RoomFiles.LocalSource.LocalSourceInterface
import com.iti_project.recipeapp.RoomFiles.User
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


    override fun getFavorites(userId: Int): LiveData<List<Int>> {
        return localSource.getFavorites(userId)
    }

    override fun checkIfEmailExists(email: String): Boolean {
        return localSource.checkIfEmailExists(email)
    }

    override fun getPasswordByEmail(email: String): String? {
        return localSource.getPasswordByEmail(email)
    }

    override fun getUserName(userId: Int): String? {
        return localSource.getUserName(userId)
    }

    override fun addAccount(user: User) {
        localSource.addAccount(user)
    }

    override fun updateFavorites(userId: Int, favorites: List<Int>) {
        localSource.updateFavorites(userId, favorites)
    }
}