package com.example.startup1

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.startup1.databinding.ActivitySignupBinding

class SignupActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignupBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupClickListeners()
    }

    private fun setupClickListeners() {
        binding.btnRegister.setOnClickListener {
            if (validateForm()) {
                // TODO: Implement registration logic
                Toast.makeText(this, "Registration successful", Toast.LENGTH_SHORT).show()
            }
        }

        binding.btnBackToLogin.setOnClickListener {
            finish()
        }
    }

    private fun validateForm(): Boolean {
        var isValid = true

        with(binding) {
            // Organization Name
            if (etOrgName.text.toString().trim().isEmpty()) {
                etOrgName.error = "Organization name is required"
                isValid = false
            }

            // First Name
            if (etFirstName.text.toString().trim().isEmpty()) {
                etFirstName.error = "First name is required"
                isValid = false
            }

            // Last Name
            if (etLastName.text.toString().trim().isEmpty()) {
                etLastName.error = "Last name is required"
                isValid = false
            }

            // Sevarth ID
            if (etSevarthId.text.toString().trim().isEmpty()) {
                etSevarthId.error = "Sevarth ID is required"
                isValid = false
            }

            // Email
            val email = etEmail.text.toString().trim()
            if (email.isEmpty()) {
                etEmail.error = "Email is required"
                isValid = false
            } else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                etEmail.error = "Invalid email format"
                isValid = false
            }

            // Phone
            val phone = etPhone.text.toString().trim()
            if (phone.isEmpty()) {
                etPhone.error = "Contact number is required"
                isValid = false
            } else if (phone.length < 10) {
                etPhone.error = "Invalid phone number"
                isValid = false
            }

            // Password
            val password = etPassword.text.toString()
            val confirmPassword = etConfirmPassword.text.toString()
            
            if (password.isEmpty()) {
                etPassword.error = "Password is required"
                isValid = false
            } else if (password.length < 6) {
                etPassword.error = "Password must be at least 6 characters"
                isValid = false
            }

            if (confirmPassword.isEmpty()) {
                etConfirmPassword.error = "Please confirm your password"
                isValid = false
            } else if (password != confirmPassword) {
                etConfirmPassword.error = "Passwords do not match"
                isValid = false
            }
        }

        return isValid
    }
} 