package com.example.startup1

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.startup1.databinding.ActivitySelectLocationBinding

class SelectLocationActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySelectLocationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySelectLocationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Get user name from intent
        val userName = intent.getStringExtra("user_name") ?: "User"
        binding.welcomeText.text = "Welcome, $userName"

        setupLocationSelection()
        setupProceedButton()
    }

    private fun setupLocationSelection() {
        // Handle card clicks
        binding.saiCityCard.setOnClickListener {
            binding.saiCityRadio.isChecked = true
        }

        binding.headOfficeCard.setOnClickListener {
            binding.headOfficeRadio.isChecked = true
        }

        // Handle radio button changes
        binding.locationRadioGroup.setOnCheckedChangeListener { _, checkedId ->
            when (checkedId) {
                binding.saiCityRadio.id -> {
                    binding.saiCityCard.strokeWidth = 4
                    binding.headOfficeCard.strokeWidth = 2
                }
                binding.headOfficeRadio.id -> {
                    binding.saiCityCard.strokeWidth = 2
                    binding.headOfficeCard.strokeWidth = 4
                }
            }
        }
    }

    private fun setupProceedButton() {
        binding.proceedButton.setOnClickListener {
            when {
                binding.saiCityRadio.isChecked -> proceedToUserDashboard("sai city", 100)
                binding.headOfficeRadio.isChecked -> proceedToUserDashboard("Head Office", 10)
                else -> Toast.makeText(this, "Please select a location", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun proceedToUserDashboard(location: String, radius: Int) {
        val intent = Intent(this, UserDashboardActivity::class.java).apply {
            putExtra("selected_location", location)
            putExtra("location_radius", radius)
            putExtra("user_name", intent.getStringExtra("user_name"))
        }
        startActivity(intent)
        finish() // Close this activity so user can't go back
    }
} 