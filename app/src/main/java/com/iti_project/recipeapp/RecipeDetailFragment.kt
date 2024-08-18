package com.iti_project.recipeapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.iti_project.recipeapp.viewmodel.MealViewModel

class RecipeDetailFragment : Fragment() {
    private val args: RecipeDetailFragmentArgs by navArgs()
    private val mealViewModel: MealViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_recipe_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val mealImage: ImageView = view.findViewById(R.id.mealImage)
        val mealTitle: TextView = view.findViewById(R.id.mealTitle)
        val mealCategory: TextView = view.findViewById(R.id.mealCategory)
        val mealArea: TextView = view.findViewById(R.id.mealArea)
        val mealInstructions: TextView = view.findViewById(R.id.mealInstructions)
        val ingredientsRecyclerView: RecyclerView = view.findViewById(R.id.ingredientsRecyclerView)
        val webView: WebView = view.findViewById(R.id.webView)
        val webSettings: WebSettings = webView.settings

        ingredientsRecyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)

        mealViewModel.mealDetails.observe(viewLifecycleOwner, Observer { mealDetailsResponse ->
            val meal = mealDetailsResponse.meals.firstOrNull() ?: return@Observer

            Glide.with(this).load(meal.strMealThumb).into(mealImage)
            mealTitle.text = meal.strMeal
            mealCategory.text = meal.strCategory
            mealArea.text = meal.strArea
            mealInstructions.text = meal.strInstructions

            webSettings.javaScriptEnabled = true
            webView.webViewClient = WebViewClient()

            // Convert the YouTube URL to the embed format
            val youtubeVideoUrl = meal.strYoutube.replace("watch?v=", "embed/")
            webView.loadUrl(youtubeVideoUrl)

            val ingredients = listOf(
                meal.strIngredient1, meal.strIngredient2, meal.strIngredient3, meal.strIngredient4,
                meal.strIngredient5, meal.strIngredient6, meal.strIngredient7, meal.strIngredient8,
                meal.strIngredient9, meal.strIngredient10, meal.strIngredient11, meal.strIngredient12,
                meal.strIngredient13, meal.strIngredient14, meal.strIngredient15
            ).filter { !it.isNullOrEmpty() }

            val measures = listOf(
                meal.strMeasure1, meal.strMeasure2, meal.strMeasure3, meal.strMeasure4,
                meal.strMeasure5, meal.strMeasure6, meal.strMeasure7, meal.strMeasure8,
                meal.strMeasure9, meal.strMeasure10, meal.strMeasure11, meal.strMeasure12,
                meal.strMeasure13, meal.strMeasure14, meal.strMeasure15
            ).filter { !it.isNullOrEmpty() }

            ingredientsRecyclerView.adapter = IngredientsAdapter(ingredients, measures)
        })

        mealViewModel.fetchMealDetailsById(args.MealID)
        }
}