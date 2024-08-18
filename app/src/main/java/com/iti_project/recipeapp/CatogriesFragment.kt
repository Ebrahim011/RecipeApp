package com.iti_project.recipeapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.iti_project.recipeapp.apiFiles.catogries.Category

class CatogriesFragment : Fragment(), CategoryAdapter.OnItemClickListener {

    private val viewModel: CategoryListViewModel by viewModels()
    private lateinit var adapter: CategoryAdapter
    private lateinit var recyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_catogries, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView = view.findViewById(R.id.recyclerViewCatogries)
        recyclerView.layoutManager = LinearLayoutManager(context)

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