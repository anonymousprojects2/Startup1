package com.example.startup1

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText

class AddLocationActivity : AppCompatActivity() {

    private lateinit var officeNameInput: TextInputEditText
    private lateinit var talukaInput: AutoCompleteTextView
    private lateinit var latitudeInput: TextInputEditText
    private lateinit var longitudeInput: TextInputEditText
    private lateinit var radiusInput: TextInputEditText
    private lateinit var addButton: MaterialButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_location)

        // Set up toolbar
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
            title = "Add New Location"
        }

        // Initialize views
        officeNameInput = findViewById(R.id.officeNameInput)
        talukaInput = findViewById(R.id.talukaInput)
        latitudeInput = findViewById(R.id.latitudeInput)
        longitudeInput = findViewById(R.id.longitudeInput)
        radiusInput = findViewById(R.id.radiusInput)
        addButton = findViewById(R.id.addButton)

        // Set up taluka dropdown
        val talukas = arrayOf("Hingoli", "Kalamnuri", "Sengaon", "Aundha Nagnath", "Basmath")
        val adapter = ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, talukas)
        talukaInput.setAdapter(adapter)

        // Set default radius
        radiusInput.setText("100")

        // Set click listener for add button
        addButton.setOnClickListener {
            if (validateInputs()) {
                saveLocation()
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    private fun validateInputs(): Boolean {
        var isValid = true

        if (officeNameInput.text.isNullOrBlank()) {
            officeNameInput.error = "Office name is required"
            isValid = false
        }

        if (latitudeInput.text.isNullOrBlank()) {
            latitudeInput.error = "Latitude is required"
            isValid = false
        } else {
            try {
                val latitude = latitudeInput.text.toString().toDouble()
                if (latitude < -90 || latitude > 90) {
                    latitudeInput.error = "Invalid latitude value"
                    isValid = false
                }
            } catch (e: NumberFormatException) {
                latitudeInput.error = "Invalid latitude format"
                isValid = false
            }
        }

        if (longitudeInput.text.isNullOrBlank()) {
            longitudeInput.error = "Longitude is required"
            isValid = false
        } else {
            try {
                val longitude = longitudeInput.text.toString().toDouble()
                if (longitude < -180 || longitude > 180) {
                    longitudeInput.error = "Invalid longitude value"
                    isValid = false
                }
            } catch (e: NumberFormatException) {
                longitudeInput.error = "Invalid longitude format"
                isValid = false
            }
        }

        if (radiusInput.text.isNullOrBlank()) {
            radiusInput.error = "Radius is required"
            isValid = false
        } else {
            try {
                val radius = radiusInput.text.toString().toInt()
                if (radius <= 0) {
                    radiusInput.error = "Radius must be greater than 0"
                    isValid = false
                }
            } catch (e: NumberFormatException) {
                radiusInput.error = "Invalid radius format"
                isValid = false
            }
        }

        return isValid
    }

    private fun saveLocation() {
        val officeName = officeNameInput.text.toString()
        val taluka = talukaInput.text.toString()
        val latitude = latitudeInput.text.toString().toDouble()
        val longitude = longitudeInput.text.toString().toDouble()
        val radius = radiusInput.text.toString().toInt()

        // TODO: Save location to database
        
        Toast.makeText(this, "Location added successfully", Toast.LENGTH_SHORT).show()
        finish()
    }
} 