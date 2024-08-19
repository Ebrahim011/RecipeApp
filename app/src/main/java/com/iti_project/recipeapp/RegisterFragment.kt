package com.iti_project.recipeapp

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.iti_project.recipeapp.RoomFolder.RoomFiles.User
import com.iti_project.recipeapp.RoomFolder.UserViewModel
import com.iti_project.recipeapp.databinding.FragmentRegisterBinding

class RegisterFragment : Fragment() {
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var binding: FragmentRegisterBinding
    private val userViewModel: UserViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRegisterBinding.inflate(inflater, container, false)
        sharedPreferences = requireContext().getSharedPreferences("UserPrefs", Context.MODE_PRIVATE)

        binding.btnRegister.setOnClickListener {
            val email = binding.etRegisterEmail.text.toString()
            val password = binding.etRegisterPassword.text.toString()
            val userName = binding.etUserName.text.toString()

            if (userName.isNotEmpty() && email.isNotEmpty() && password.isNotEmpty()) {
                val user = User(userEmail = email, userPassword = password, userName = userName, listOfFavorites ="")
                userViewModel.addAccount(user)

                userViewModel.getUserId(email)
                userViewModel.userId.observe(viewLifecycleOwner, Observer { userId ->
                    if (userId != null) {
                        val editor = sharedPreferences.edit()
                        editor.putInt("userId", userId)
                        editor.apply()
                        Toast.makeText(requireContext(), "Registered successfully", Toast.LENGTH_SHORT).show()
                        findNavController().navigate(R.id.action_registerFragment_to_loginFragment)
                    } else {
                        Toast.makeText(requireContext(), "Registration failed", Toast.LENGTH_SHORT).show()
                    }
                })
            } else {
                Toast.makeText(requireContext(), "Please fill in all fields", Toast.LENGTH_SHORT).show()
            }
        }
        binding.loginHere.setOnClickListener {
            findNavController().navigate(R.id.action_registerFragment_to_loginFragment)
        }
        return binding.root
    }
}