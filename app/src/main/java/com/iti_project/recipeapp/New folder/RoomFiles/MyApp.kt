package com.iti_project.recipeapp.RoomFiles

import android.app.Application


class MyApp(): Application() {
    override fun onCreate() {
        super.onCreate()
        RoomDataBase.getInstance(applicationContext)
    }
}