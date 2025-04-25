package com.example.startup1

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.startup1.databinding.ActivityLoginBinding
import com.example.startup1.repositories.AuthRepository
import com.example.startup1.repositories.UserRepository
import kotlinx.coroutines.launch

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private val authRepository = AuthRepository()
    private val userRepository = UserRepository()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupClickListeners()
        setupRadioGroup()
        
        // Initially hide/show sign-up button based on default selection
        updateSignUpButtonVisibility(binding.adminRadioButton.isChecked)
    }

    private fun setupRadioGroup() {
        binding.roleRadioGroup.setOnCheckedChangeListener { _, checkedId ->
            val isAdmin = checkedId == binding.adminRadioButton.id
            updateSignUpButtonVisibility(isAdmin)
        }
    }

    private fun updateSignUpButtonVisibility(isAdmin: Boolean) {
        binding.signUpLink.visibility = if (isAdmin) View.VISIBLE else View.GONE
    }

    private fun setupClickListeners() {
        binding.loginButton.setOnClickListener {
            val sevarthId = binding.sevarthIdInput.text.toString()
            val password = binding.passwordInput.text.toString()
            val isAdmin = binding.adminRadioButton.isChecked

            if (sevarthId.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            lifecycleScope.launch {
                try {
                    // Get user details from Firebase
                    userRepository.getUser(sevarthId).onSuccess { user ->
                        if (user == null) {
                            Toast.makeText(this@LoginActivity, "User not found", Toast.LENGTH_SHORT).show()
                            return@onSuccess
                        }

                        // Verify role
                        if (isAdmin && user.role != "admin") {
                            Toast.makeText(this@LoginActivity, "Invalid admin credentials", Toast.LENGTH_SHORT).show()
                            return@onSuccess
                        }

                        // Authenticate with Supabase
                        authRepository.signIn(user.email, password).onSuccess {
                            if (isAdmin) {
                                startActivity(Intent(this@LoginActivity, AdminDashboardActivity::class.java))
                            } else {
                                val intent = Intent(this@LoginActivity, SelectLocationActivity::class.java).apply {
                                    putExtra("user_name", "${user.firstName} ${user.lastName}")
                                }
                                startActivity(intent)
                            }
                            finish()
                        }.onFailure { e ->
                            Toast.makeText(this@LoginActivity, "Invalid credentials", Toast.LENGTH_SHORT).show()
                        }
                    }.onFailure { e ->
                        Toast.makeText(this@LoginActivity, "Error: ${e.message}", Toast.LENGTH_SHORT).show()
                    }
                } catch (e: Exception) {
                    Toast.makeText(this@LoginActivity, "Error: ${e.message}", Toast.LENGTH_SHORT).show()
                }
            }
        }

        binding.forgotPasswordText.setOnClickListener {
            lifecycleScope.launch {
                val sevarthId = binding.sevarthIdInput.text.toString()
                if (sevarthId.isEmpty()) {
                    Toast.makeText(this@LoginActivity, "Please enter your Sevarth ID", Toast.LENGTH_SHORT).show()
                    return@launch
                }

                userRepository.getUser(sevarthId).onSuccess { user ->
                    if (user == null) {
                        Toast.makeText(this@LoginActivity, "User not found", Toast.LENGTH_SHORT).show()
                        return@onSuccess
                    }

                    authRepository.resetPassword(user.email).onSuccess {
                        Toast.makeText(this@LoginActivity, "Password reset email sent", Toast.LENGTH_SHORT).show()
                    }.onFailure { e ->
                        Toast.makeText(this@LoginActivity, "Failed to send reset email: ${e.message}", Toast.LENGTH_SHORT).show()
                    }
                }.onFailure { e ->
                    Toast.makeText(this@LoginActivity, "Error: ${e.message}", Toast.LENGTH_SHORT).show()
                }
            }
        }

        binding.signUpLink.setOnClickListener {
            startActivity(Intent(this, SignupActivity::class.java))
        }
    }
} 