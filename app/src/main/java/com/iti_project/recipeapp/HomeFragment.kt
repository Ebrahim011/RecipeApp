package com.iti_project.recipeapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.iti_project.recipeapp.mealCatogry.Meal
import com.iti_project.recipeapp.viewmodel.MealViewModel

class HomeFragment : Fragment(), HomeAdapter.OnItemClickListener {
    private val homeViewModel: MealViewModel by viewModels()
    private lateinit var homeAdapter: HomeAdapter
    private lateinit var recyclerView: RecyclerView
    private val args: HomeFragmentArgs by navArgs()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val category:String = args.Category
        recyclerView = view.findViewById(R.id.recyclerViewHome)
        recyclerView.layoutManager = LinearLayoutManager(context)
        homeAdapter = HomeAdapter(emptyList(), this)
        recyclerView.adapter = homeAdapter

        homeViewModel.mealsByCategory.observe(viewLifecycleOwner, Observer { meals ->
            homeAdapter = HomeAdapter(meals, this)
            recyclerView.adapter = homeAdapter
        })

        homeViewModel.fetchMealsByCategory(category) // Example category
    }

    override fun onItemClick(meal: Meal) {
        val action = HomeFragmentDirections.actionHomeFragmentToRecipeDetailFragment(meal.idMeal)
        findNavController().navigate(action)
    }
}