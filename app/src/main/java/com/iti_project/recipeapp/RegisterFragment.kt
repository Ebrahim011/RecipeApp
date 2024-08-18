package com.iti_project.recipeapp

import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.iti_project.recipeapp.RoomFiles.User
import com.iti_project.recipeapp.ViewModels.UserViewModel
import com.iti_project.recipeapp.databinding.FragmentRegisterBinding

class RegisterFragment : Fragment() {
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var binding: FragmentRegisterBinding
    private val userViewModel: UserViewModel by viewModels()
//    private val userViewModel: UserViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        // Inflate the layout for this fragment

        binding = FragmentRegisterBinding.inflate(inflater, container, false)
        sharedPreferences = requireContext().getSharedPreferences("UserPrefs", 0)

        binding.btnRegister.setOnClickListener {
            val email = binding.etRegisterEmail.text.toString()
            val password = binding.etRegisterPassword.text.toString()
            val userName = binding.etUserName.text.toString()
            Log.e("Tag","$email..$password..$userName")

            if (userName.isNotEmpty() && email.isNotEmpty() && password.isNotEmpty()) {
//                val user = User(0,email,password,userName, emptyList())
//                userViewModel.addAccount(user)

                // Save to SharedPreferences
                val editor = sharedPreferences.edit()
                editor.putString("email", email)
                editor.putString("password", password)
                editor.apply()
                Toast.makeText(requireContext(), "Registered successfully", Toast.LENGTH_SHORT).show()

                // Navigate to LoginFragment
                findNavController().navigate(R.id.action_registerFragment_to_loginFragment)
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