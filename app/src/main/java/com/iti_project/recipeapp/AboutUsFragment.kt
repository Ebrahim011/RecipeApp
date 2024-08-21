package com.iti_project.recipeapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerview.SecondListAdapter
import com.iti_project.recipeapp.databinding.FragmentAboutUsBinding

class AboutUsFragment : Fragment() {
    private lateinit var recycleViewName: RecyclerView
    private lateinit var secondRecycleView: RecyclerView
    private lateinit var pages: ArrayList<String>
    private lateinit var contacts: ArrayList<String>
    private lateinit var binding: FragmentAboutUsBinding
    private lateinit var secondAdapter: SecondListAdapter
    private lateinit var image: ArrayList<Int>

    private lateinit var pageAdapter: NamesAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAboutUsBinding.inflate(inflater, container, false)

        recycleViewName = binding.rvName
        secondRecycleView = binding.rvName

        pages = arrayListOf(
            "Home",
            "Login",
            "Register",
            "Favourites",
            "Sign Up",
            "Search"
        )
        contacts = arrayListOf(
            "025879556",
            "Recipe App",
            "Application",
            "recipeApp@gmail.com",
        )
        image = arrayListOf(
            R.drawable.baseline_local_phone_24,
            R.drawable.facebook,
            R.drawable.twitter,
            R.drawable.baseline_email_24
        )

        pageAdapter = NamesAdapter(pages)
        recycleViewName.layoutManager = LinearLayoutManager(requireContext())
        recycleViewName.adapter = pageAdapter

        secondAdapter = SecondListAdapter(contacts, image)
        secondRecycleView.layoutManager = LinearLayoutManager(requireContext())
        secondRecycleView.adapter = secondAdapter

        return binding.root
    }
}