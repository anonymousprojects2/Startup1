package com.example.startup1

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.startup1.databinding.ActivityUserDashboardBinding
import java.text.SimpleDateFormat
import java.util.*

class UserDashboardActivity : AppCompatActivity() {
    private lateinit var binding: ActivityUserDashboardBinding
    private val dateFormat = SimpleDateFormat("EEEE, MMMM d, yyyy", Locale.getDefault())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserDashboardBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupToolbar()
        setupUserInfo()
        setupButtons()
    }

    private fun setupToolbar() {
        setSupportActionBar(binding.toolbar)
    }

    private fun setupUserInfo() {
        val userName = intent.getStringExtra("user_name") ?: "User"
        binding.welcomeText.text = "Welcome, $userName"
        binding.dateText.text = dateFormat.format(Date())
    }

    private fun setupButtons() {
        binding.checkInButton.setOnClickListener {
            // Initialize face verification for check-in
            initializeFaceVerification("check_in")
        }

        binding.checkOutButton.setOnClickListener {
            // Initialize face verification for check-out
            initializeFaceVerification("check_out")
        }

        binding.viewHistoryButton.setOnClickListener {
            // Navigate to attendance history
            // TODO: Implement navigation to attendance history screen
            Toast.makeText(this, "View History clicked", Toast.LENGTH_SHORT).show()
        }

        binding.logoutButton.setOnClickListener {
            // Navigate back to login screen
            val intent = Intent(this, LoginActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
            finish()
        }
    }

    private fun initializeFaceVerification(action: String) {
        try {
            // TODO: Initialize face verification SDK
            // This is where you would initialize your face recognition system
            // For now, we'll show an error message to match the screenshot
            Toast.makeText(
                this,
                "Error: Face recognition initialization failed",
                Toast.LENGTH_LONG
            ).show()
        } catch (e: Exception) {
            Toast.makeText(
                this,
                "Error initializing face verification: ${e.message}",
                Toast.LENGTH_LONG
            ).show()
        }
    }

    private fun handleSuccessfulVerification(action: String) {
        val currentTime = SimpleDateFormat("HH:mm", Locale.getDefault()).format(Date())
        val message = when (action) {
            "check_in" -> "Successfully checked in at $currentTime"
            "check_out" -> "Successfully checked out at $currentTime"
            else -> "Action completed successfully"
        }
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }
} 