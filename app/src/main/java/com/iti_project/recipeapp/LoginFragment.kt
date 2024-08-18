package com.iti_project.recipeapp

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.iti_project.recipeapp.ViewModels.UserViewModel
import com.iti_project.recipeapp.databinding.FragmentLoginBinding

class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding
    private val userViewModel: UserViewModel by viewModels()
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        sharedPreferences = requireContext().getSharedPreferences("UserPrefs", 0)

        binding.btnLogin.setOnClickListener {
            val email = binding.etEmail.text.toString()
            val password = binding.etPassword.text.toString()

            if (email.isNotEmpty() && password.isNotEmpty()) {
                val savedEmail = sharedPreferences.getString("email", null)
                val savedPassword = sharedPreferences.getString("password", null)
                Log.e("Tag","$savedEmail+$savedPassword")

                if (email == savedEmail && password == savedPassword) {
                    // Email and password match, navigate to HomeFragment
                    val intent = Intent(requireActivity(), RecipeActivity::class.java)
                    startActivity(intent)
                    requireActivity().finish()
                } else {
                    Toast.makeText(requireContext(), "Invalid email or password", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(requireContext(), "Please fill in all fields", Toast.LENGTH_SHORT).show()
            }
        }
        binding.registerHere.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_registerFragment)

        }
        return binding.root

    }


}