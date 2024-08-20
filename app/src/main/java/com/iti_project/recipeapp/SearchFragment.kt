package com.iti_project.recipeapp

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.iti_project.recipeapp.mealCatogry.Meal

class SearchFragment : Fragment(), SearchAdapter.OnItemClickListener {

    private val searchViewModel: SearchViewModel by viewModels()
    private lateinit var searchAdapter: SearchAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var searchEditText: EditText

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_search, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        searchEditText = view.findViewById(R.id.searchEditText)
        recyclerView = view.findViewById(R.id.recyclerViewSearch)
        recyclerView.layoutManager = GridLayoutManager(context, 2)
        searchAdapter = SearchAdapter(emptyList(), this)
        recyclerView.adapter = searchAdapter

        searchViewModel.searchResults.observe(viewLifecycleOwner, Observer { meals ->
            searchAdapter = SearchAdapter(meals, this)
            recyclerView.adapter = searchAdapter
        })

        searchEditText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val query = s.toString()
                if (query.isNotEmpty()) {
                    searchViewModel.searchMealsByName(query)
                } else {
                    searchAdapter = SearchAdapter(emptyList(), this@SearchFragment)
                    recyclerView.adapter = searchAdapter
                }
            }

            override fun afterTextChanged(s: Editable?) {}
        })
    }

    override fun onItemClick(meal: Meal) {
        val action = SearchFragmentDirections.actionSearchFragmentToRecipeDetailFragment(meal.idMeal)
        findNavController().navigate(action)
    }
}