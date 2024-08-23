package com.iti_project.recipeapp

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
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
            val updatedUserEmail = binding.editEmail.text.toString()
            val updatedPassword = binding.editPassword.text.toString()

            val emailRegex = Regex("^[a-zA-Z0-9_]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$")
            val passwordRegex = Regex("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{6,12}$")

            var isValid = true

            if (!emailRegex.matches(updatedUserEmail)) {
                binding.emailHelperText.visibility = View.VISIBLE
                binding.emailHelperText.text = "Invalid email format"
                isValid = false
            } else {
                binding.emailHelperText.visibility = View.GONE
            }

            if (!passwordRegex.matches(updatedPassword)) {
                binding.passwordHelperText.visibility = View.VISIBLE
                binding.passwordHelperText.text = "Password must be 6-12 characters long, with at least one uppercase, one lowercase letter, and one number."
                isValid = false
            } else {
                binding.passwordHelperText.visibility = View.GONE
            }

            if (isValid) {
                userViewModel.getUserByEmail(email).observe(viewLifecycleOwner) { user ->
                    if (user != null) {
                        val updatedUser = User(
                            _id = user._id,
                            userEmail = updatedUserEmail,
                            userPassword = updatedPassword,
                            userName = user.userName,
                            listOfFavorites = user.listOfFavorites
                        )
                        userViewModel.updateProfile(updatedUser)
                        Toast.makeText(requireContext(), "Profile updated", Toast.LENGTH_SHORT).show()

                        val editor = sharedPreferences.edit()
                        editor.clear().apply()

                        val intent = Intent(context, MainActivity::class.java)
                        startActivity(intent)
                        requireActivity().finish()
                    } else {
                        Toast.makeText(requireContext(), "User not found", Toast.LENGTH_SHORT).show()
                    }
                }
            } else {
                Toast.makeText(requireContext(), "Please correct the errors", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
