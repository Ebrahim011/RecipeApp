package com.iti_project.recipeapp

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton
import androidx.fragment.app.viewModels
import com.iti_project.recipeapp.RoomFolder.RoomFiles.User
import com.iti_project.recipeapp.RoomFolder.UserViewModel
import com.iti_project.recipeapp.databinding.FragmentProfileBinding

class ProfileFragment : Fragment() {

    private lateinit var binding: FragmentProfileBinding
    private val userViewModel: UserViewModel by viewModels()
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentProfileBinding.inflate(inflater, container, false)
        sharedPreferences = requireContext().getSharedPreferences("UserPrefs", Context.MODE_PRIVATE)

        // Retrieve the current user's email from SharedPreferences
        val currentEmail = sharedPreferences.getString("userEmail", null)

        if (currentEmail != null) {
            loadUserData(currentEmail)
        } else {
            Toast.makeText(requireContext(), "User email not found", Toast.LENGTH_SHORT).show()
        }

        binding.btnUpdateAndSignOut.setOnClickListener {
            updateUserData(currentEmail)
            val editor = sharedPreferences.edit()
            editor.clear().apply()

            val intent = Intent(context, MainActivity::class.java)
            startActivity(intent)
            requireActivity().finish()
        }

        return binding.root
    }

    private fun loadUserData(email: String) {
        userViewModel.getUserByEmail(email).observe(viewLifecycleOwner) { user ->
            if (user != null) {
                binding.editUsername.setText(user.userName)
                binding.editEmail.setText(user.userEmail)
                binding.editPassword.setText(user.userPassword)
            } else {
                Toast.makeText(requireContext(), "User not found", Toast.LENGTH_SHORT).show()
            }
        }
    }
    private fun updateUserData(email: String?) {
        if (email != null) {
            val updatedUserName = binding.editUsername.text.toString()
            val updatedUserEmail = binding.editEmail.text.toString()
            val updatedPassword = binding.editPassword.text.toString()

            if (updatedUserName.isNotEmpty() && updatedPassword.isNotEmpty()) {
                userViewModel.getUserByEmail(email).observe(viewLifecycleOwner) { user ->
                    if (user != null) {
                        val updatedUser = User(
                            _id = user._id, // Assuming `id` is the primary key
                            userEmail = updatedUserEmail,
                            userPassword = updatedPassword,
                            userName = updatedUserName,
                            listOfFavorites = user.listOfFavorites // Retain the existing favorites
                        )
                        userViewModel.updateProfile(updatedUser)
                        Toast.makeText(requireContext(), "Profile updated", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(requireContext(), "User not found", Toast.LENGTH_SHORT).show()
                    }
                }
            } else {
                Toast.makeText(requireContext(), "Please fill in all fields", Toast.LENGTH_SHORT).show()
            }
        }
    }

}