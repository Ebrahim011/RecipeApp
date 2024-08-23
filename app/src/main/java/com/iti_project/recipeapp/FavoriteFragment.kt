package com.iti_project.recipeapp

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.iti_project.recipeapp.RoomFolder.UserViewModel
import com.iti_project.recipeapp.mealCatogry.Meal
import com.iti_project.recipeapp.viewmodel.MealViewModel
import kotlinx.coroutines.launch

class FavoriteFragment : Fragment(), FavoriteAdapter.OnItemClickListener {
    private val userViewModel: UserViewModel by viewModels()
    private val mealViewModel: MealViewModel by viewModels()
    private lateinit var recyclerView: RecyclerView
    private
    lateinit var favoriteAdapter: FavoriteAdapter

    override fun onStart() {
        super.onStart()

        userViewModel.getFavorites(
            requireContext().getSharedPreferences(
                "UserPrefs",
                Context.MODE_PRIVATE
            ).getInt("userId", -1)
        )
    }
    override fun onResume() {
        super.onResume()
        userViewModel.getFavorites(
            requireContext().getSharedPreferences(
                "UserPrefs",
                Context.MODE_PRIVATE
            ).getInt("userId", -1)
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_favorite, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView = view.findViewById(R.id.recyclerViewFav)
        recyclerView.layoutManager = GridLayoutManager(context, 2)
        favoriteAdapter = FavoriteAdapter(emptyList(),this)
        recyclerView.adapter = favoriteAdapter

        val userId = requireContext().getSharedPreferences("UserPrefs", Context.MODE_PRIVATE).getInt("userId", -1)
        userViewModel.getFavorites(userId)
            userViewModel.favorites.observe(viewLifecycleOwner) { favList ->
                favoriteAdapter.updateMeals(emptyList())

                if (favList.isNotEmpty()) {
                    fetchFavoriteMeals(favList)
                }else{
                    favoriteAdapter.updateMeals(emptyList())
                }



        }
    }

    private fun fetchFavoriteMeals(favList: List<Int>) {
        val mealSet = mutableSetOf<Meal>()
        for (mealId in favList) {
            mealViewModel.fetchMealDetailsById(mealId.toString())
        }
        mealViewModel.mealDetails.observe(viewLifecycleOwner, Observer { mealDetailsResponse ->
            val mealFD = mealDetailsResponse.meals.firstOrNull()
            if (mealFD != null) {
                val meal=Meal(mealFD.idMeal,mealFD.strMeal,mealFD.strMealThumb)
                mealSet.add(meal)
                favoriteAdapter.updateMeals(mealSet.toList())
            }
        })
    }

    override fun onItemClick(meal: Meal) {
        val action = FavoriteFragmentDirections.actionFavoriteFragmentToRecipeDetailFragment(meal.idMeal)
        findNavController().navigate(action)

    }




}



