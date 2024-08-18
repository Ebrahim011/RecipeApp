package com.iti_project.recipeapp.RoomFiles

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
}