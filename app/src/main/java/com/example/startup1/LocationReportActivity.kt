package com.example.startup1

import android.app.DatePickerDialog
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.startup1.databinding.ActivityLocationReportBinding
import com.google.android.material.textfield.TextInputEditText
import java.text.SimpleDateFormat
import java.util.*

class LocationReportActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLocationReportBinding
    private val calendar = Calendar.getInstance()
    private val dateFormatter = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLocationReportBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Location Attendance Report"

        setupLocationSpinner()
        setupDatePickers()
        setupGenerateButton()
        setupRecyclerView()
    }

    private fun setupLocationSpinner() {
        // TODO: Replace with actual location list from database
        val locations = listOf("Hingoli", "Kalamnuri", "Sengaon", "Aundha Nagnath", "Basmath")
        val adapter = ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, locations)
        (binding.locationSpinner as? AutoCompleteTextView)?.setAdapter(adapter)
    }

    private fun setupDatePickers() {
        binding.startDateInput.setOnClickListener { showDatePicker(it as TextInputEditText) }
        binding.endDateInput.setOnClickListener { showDatePicker(it as TextInputEditText) }
    }

    private fun showDatePicker(dateInput: TextInputEditText) {
        val datePickerDialog = DatePickerDialog(
            this,
            { _, year, month, day ->
                calendar.set(year, month, day)
                dateInput.setText(dateFormatter.format(calendar.time))
            },
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        )
        datePickerDialog.show()
    }

    private fun setupGenerateButton() {
        binding.generateReportButton.setOnClickListener {
            if (validateInputs()) {
                generateReport()
            }
        }
    }

    private fun validateInputs(): Boolean {
        var isValid = true

        if (binding.locationSpinner.text.isNullOrEmpty()) {
            binding.locationSpinner.error = "Please select a location"
            isValid = false
        }

        if (binding.startDateInput.text.isNullOrEmpty()) {
            binding.startDateInput.error = "Please select start date"
            isValid = false
        }

        if (binding.endDateInput.text.isNullOrEmpty()) {
            binding.endDateInput.error = "Please select end date"
            isValid = false
        }

        return isValid
    }

    private fun generateReport() {
        // TODO: Implement report generation logic
        // 1. Get selected location and date range
        // 2. Query attendance data from database
        // 3. Update RecyclerView with results
    }

    private fun setupRecyclerView() {
        binding.reportRecyclerView.layoutManager = LinearLayoutManager(this)
        // TODO: Set adapter once created
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
} 