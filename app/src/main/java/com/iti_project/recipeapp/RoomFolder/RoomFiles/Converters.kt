package com.iti_project.recipeapp.RoomFolder.RoomFiles

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.TypeConverter

class Converters {
    @TypeConverter
    fun fromListOfInt(list: List<Int>): String {
        return list.joinToString(separator = ",")
    }

    @TypeConverter
    fun toListOfInt(data: String): List<Int> {
        return if (data.isEmpty()) {
            emptyList()
        } else {
            data.split(",").map { it.toInt() }
        }
    }

    fun toLiveDataListOfInt(data: String): LiveData<List<Int>> {
        val list = toListOfInt(data)
        return MutableLiveData(list)
    }
}