package com.example.startup1

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Button
import com.google.android.material.textfield.TextInputEditText

class AddLocationDialog(context: Context) : Dialog(context) {

    private lateinit var officeNameInput: TextInputEditText
    private lateinit var talukaInput: AutoCompleteTextView
    private lateinit var latitudeInput: TextInputEditText
    private lateinit var longitudeInput: TextInputEditText
    private lateinit var radiusInput: TextInputEditText
    private lateinit var cancelButton: Button
    private lateinit var addButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dialog_add_location)

        // Initialize views
        officeNameInput = findViewById(R.id.officeNameInput)
        talukaInput = findViewById(R.id.talukaInput)
        latitudeInput = findViewById(R.id.latitudeInput)
        longitudeInput = findViewById(R.id.longitudeInput)
        radiusInput = findViewById(R.id.radiusInput)
        cancelButton = findViewById(R.id.cancelButton)
        addButton = findViewById(R.id.addButton)

        // Set up taluka dropdown
        val talukas = arrayOf("Hingoli", "Kalamnuri", "Sengaon", "Aundha Nagnath", "Basmath")
        val adapter = ArrayAdapter(context, android.R.layout.simple_dropdown_item_1line, talukas)
        talukaInput.setAdapter(adapter)

        // Set default radius
        radiusInput.setText("100")

        // Set click listeners
        cancelButton.setOnClickListener {
            dismiss()
        }

        addButton.setOnClickListener {
            if (validateInputs()) {
                val officeName = officeNameInput.text.toString()
                val taluka = talukaInput.text.toString()
                val latitude = latitudeInput.text.toString().toDouble()
                val longitude = longitudeInput.text.toString().toDouble()
                val radius = radiusInput.text.toString().toInt()

                locationAddedListener?.onLocationAdded(
                    officeName,
                    taluka,
                    latitude,
                    longitude,
                    radius
                )
                dismiss()
            }
        }
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

    interface OnLocationAddedListener {
        fun onLocationAdded(
            officeName: String,
            taluka: String,
            latitude: Double,
            longitude: Double,
            radius: Int
        )
    }

    private var locationAddedListener: OnLocationAddedListener? = null

    fun setOnLocationAddedListener(listener: OnLocationAddedListener) {
        locationAddedListener = listener
    }
} 