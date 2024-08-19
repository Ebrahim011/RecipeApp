package com.iti_project.recipeapp.RoomFolder.RoomFiles

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface UserDao {

 @Query("SELECT list_of_favorites FROM user WHERE _id = :userId")
 fun getFavorites(userId: Int): String

 @Query("SELECT EXISTS(SELECT 1 FROM user WHERE user_email = :email)")
 fun checkIfEmailExists(email: String): Boolean

 @Query("SELECT user_password FROM user WHERE user_email = :email")
 fun getPasswordByEmail(email: String): String?

 @Query("SELECT _id FROM user WHERE user_email = :email")
 fun getUserId(email: String): Int?

 @Insert(onConflict = OnConflictStrategy.REPLACE)
 fun addAccount(user: User)

 @Query("UPDATE user SET list_of_favorites = :favorites WHERE _id = :userId")
 fun updateFavorites(userId: Int, favorites: String)
}