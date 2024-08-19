package com.iti_project.recipeapp.RoomFolder.RoomFiles

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
            return  data.split(",").map { it.toInt() }
        }
    }
}