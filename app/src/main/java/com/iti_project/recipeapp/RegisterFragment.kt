package com.iti_project.recipeapp

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.text.InputType
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.iti_project.recipeapp.RoomFolder.RoomFiles.User
import com.iti_project.recipeapp.RoomFolder.UserViewModel
import com.iti_project.recipeapp.databinding.FragmentRegisterBinding
import kotlinx.coroutines.launch

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

        binding.etRegisterPassword.setOnLongClickListener {

            if (binding.etRegisterPassword.inputType == InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD) {
                binding.etRegisterPassword.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
            } else {
                binding.etRegisterPassword.inputType = InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
            }
            binding.etRegisterPassword.setSelection(binding.etRegisterPassword.text.length)
            true
        }
        binding.btnRegister.setOnClickListener {
            var email = binding.etRegisterEmail.text.toString()
            var password = binding.etRegisterPassword.text.toString()
            var userName = binding.etUserName.text.toString()

            if (userName.isNotEmpty() && email.isNotEmpty() && password.isNotEmpty()) {


                if(userViewModel.checkIfEmailExistsBoolean(email)){
                    Toast.makeText(requireContext(), "Email already exists", Toast.LENGTH_SHORT).show()
                    binding.etRegisterEmail.text.clear()
                    binding.etRegisterPassword.text.clear()
                    binding.etUserName.text.clear()
                    return@setOnClickListener
                }else {
                    val user = User(
                        userEmail = email,
                        userPassword = password,
                        userName = userName,
                        listOfFavorites = ""
                    )
                    userViewModel.addAccount(user)
                    findNavController().navigate(R.id.action_registerFragment_to_loginFragment)
                }}
                    else {
                        Toast.makeText(
                            requireContext(),
                            "Please fill in all fields",
                            Toast.LENGTH_SHORT
                        ).show()
                    }

        }
        binding.loginHere.setOnClickListener {
            findNavController().navigate(R.id.action_registerFragment_to_loginFragment)
        }
        return binding.root
    }
}