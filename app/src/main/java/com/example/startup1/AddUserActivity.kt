package com.example.startup1

import android.app.DatePickerDialog
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.startup1.models.User
import com.example.startup1.repositories.AuthRepository
import com.example.startup1.repositories.UserRepository
import com.google.android.material.textfield.TextInputEditText
import kotlinx.coroutines.launch
import java.util.*

class AddUserActivity : AppCompatActivity() {
    private lateinit var sevarthIdInput: TextInputEditText
    private lateinit var firstNameInput: TextInputEditText
    private lateinit var lastNameInput: TextInputEditText
    private lateinit var dobInput: TextInputEditText
    private lateinit var phoneInput: TextInputEditText
    private lateinit var emailInput: TextInputEditText
    private lateinit var locationInput: AutoCompleteTextView
    private lateinit var passwordInput: TextInputEditText

    private val authRepository = AuthRepository()
    private val userRepository = UserRepository()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_user)

        // Initialize views
        initializeViews()
        
        // Setup date picker for DOB
        setupDatePicker()
        
        // Setup location dropdown
        setupLocationDropdown()
        
        // Setup button clicks
        setupButtons()
    }

    private fun initializeViews() {
        sevarthIdInput = findViewById(R.id.sevarthIdInput)
        firstNameInput = findViewById(R.id.firstNameInput)
        lastNameInput = findViewById(R.id.lastNameInput)
        dobInput = findViewById(R.id.dobInput)
        phoneInput = findViewById(R.id.phoneInput)
        emailInput = findViewById(R.id.emailInput)
        locationInput = findViewById(R.id.locationInput)
        passwordInput = findViewById(R.id.passwordInput)
    }

    private fun setupDatePicker() {
        dobInput.setOnClickListener {
            val calendar = Calendar.getInstance()
            val year = calendar.get(Calendar.YEAR)
            val month = calendar.get(Calendar.MONTH)
            val day = calendar.get(Calendar.DAY_OF_MONTH)

            DatePickerDialog(this, { _, selectedYear, selectedMonth, selectedDay ->
                dobInput.setText("$selectedDay/${selectedMonth + 1}/$selectedYear")
            }, year, month, day).show()
        }
    }

    private fun setupLocationDropdown() {
        // Example locations - replace with your actual location data
        val locations = arrayOf("Location 1", "Location 2", "Location 3")
        val adapter = ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, locations)
        locationInput.setAdapter(adapter)
    }

    private fun setupButtons() {
        findViewById<android.widget.Button>(R.id.cancelButton).setOnClickListener {
            finish()
        }

        findViewById<android.widget.Button>(R.id.nextButton).setOnClickListener {
            if (validateForm()) {
                createUser()
            }
        }
    }

    private fun createUser() {
        val user = User(
            sevarthId = sevarthIdInput.text.toString().trim(),
            firstName = firstNameInput.text.toString().trim(),
            lastName = lastNameInput.text.toString().trim(),
            email = emailInput.text.toString().trim(),
            phone = phoneInput.text.toString().trim(),
            dob = dobInput.text.toString().trim(),
            gender = if (findViewById<android.widget.RadioButton>(R.id.maleRadioButton).isChecked) "male" else "female",
            location = locationInput.text.toString().trim()
        )

        lifecycleScope.launch {
            try {
                // Create authentication account with Supabase
                authRepository.signUp(user.email, passwordInput.text.toString()).onSuccess {
                    // Store user data in Firebase
                    userRepository.createUser(user).onSuccess { userId ->
                        Toast.makeText(this@AddUserActivity, "User created successfully", Toast.LENGTH_SHORT).show()
                        finish()
                    }.onFailure { e ->
                        Toast.makeText(this@AddUserActivity, "Failed to store user data: ${e.message}", Toast.LENGTH_SHORT).show()
                    }
                }.onFailure { e ->
                    Toast.makeText(this@AddUserActivity, "Failed to create auth account: ${e.message}", Toast.LENGTH_SHORT).show()
                }
            } catch (e: Exception) {
                Toast.makeText(this@AddUserActivity, "Error: ${e.message}", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun validateForm(): Boolean {
        var isValid = true

        // Validate Sevarth ID
        if (sevarthIdInput.text.toString().trim().isEmpty()) {
            sevarthIdInput.error = "Sevarth ID is required"
            isValid = false
        }

        // Validate Date of Birth
        if (dobInput.text.toString().trim().isEmpty()) {
            dobInput.error = "Date of Birth is required"
            isValid = false
        }

        // Validate Phone Number
        if (phoneInput.text.toString().trim().isEmpty()) {
            phoneInput.error = "Phone Number is required"
            isValid = false
        }

        // Validate Email
        val email = emailInput.text.toString().trim()
        if (email.isEmpty()) {
            emailInput.error = "Email Address is required"
            isValid = false
        } else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            emailInput.error = "Invalid email format"
            isValid = false
        }

        // Validate Location
        if (locationInput.text.toString().trim().isEmpty()) {
            locationInput.error = "Location is required"
            isValid = false
        }

        // Validate Password
        if (passwordInput.text.toString().trim().isEmpty()) {
            passwordInput.error = "Password is required"
            isValid = false
        } else if (passwordInput.text.toString().length < 6) {
            passwordInput.error = "Password must be at least 6 characters"
            isValid = false
        }

        return isValid
    }
} 