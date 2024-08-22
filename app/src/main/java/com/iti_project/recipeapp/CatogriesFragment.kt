package com.iti_project.recipeapp

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.iti_project.recipeapp.apiFiles.catogries.Category

class CatogriesFragment : Fragment(), CategoryAdapter.OnItemClickListener {

    private val viewModel: CategoryListViewModel by viewModels()
    private lateinit var adapter: CategoryAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var btnExplore:Button
    private lateinit var btnFav:Button
    private lateinit var btnSearch:Button
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_catogries, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btnExplore = view.findViewById(R.id.btnExplore)
        btnFav=view.findViewById(R.id.btnFav)
        btnSearch=view.findViewById(R.id.btnSearch)
        recyclerView = view.findViewById(R.id.recyclerViewCatogries)
        recyclerView.layoutManager = GridLayoutManager(context, 2)

        btnExplore.setOnClickListener {
            viewModel.oneRandomMeal.observe(viewLifecycleOwner, Observer { meal ->
                if (meal != null) {
                    val action = CatogriesFragmentDirections.actionCatogriesFragment2ToRecipeDetailFragment(meal.idMeal)
                    findNavController().navigate(action)
                } else {
                    Log.e("CatogriesFragment", "Random meal is null")
                }
            })
            viewModel.fetchRandomMeal() // Ensure this method is called to fetch the random meal
        }

        btnFav.setOnClickListener {
            val bottomNavigationView = requireActivity().findViewById<BottomNavigationView>(R.id.bottom_navigation)
            bottomNavigationView.selectedItemId = R.id.favoriteFragment
        }
        btnSearch.setOnClickListener {
            val bottomNavigationView = requireActivity().findViewById<BottomNavigationView>(R.id.bottom_navigation)
            bottomNavigationView.selectedItemId = R.id.searchFragment
        }


        adapter = CategoryAdapter(emptyList(), this)
        recyclerView.adapter = adapter

        viewModel.categoryList.observe(viewLifecycleOwner, Observer { categoryList ->
            adapter = CategoryAdapter(categoryList.categories, this)
            recyclerView.adapter = adapter
        })

        viewModel.fetchCategoryList()
    }

    override fun onItemClick(category: Category) {
        val categoryName: String = category.strCategory
        val action = CatogriesFragmentDirections.actionCatogriesFragment2ToHomeFragment(categoryName)
        findNavController().navigate(action)
    }
}