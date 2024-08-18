package com.iti_project.recipeapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.iti_project.recipeapp.apiFiles.Repo.Repo
import com.iti_project.recipeapp.apiFiles.remoteDataSource.RemoteDataSource
import com.iti_project.recipeapp.viewmodel.MealViewModel

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }
    fun finishMain(){
        super.finish()
    }
}