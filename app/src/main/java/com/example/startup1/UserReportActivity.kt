package com.example.startup1

import android.app.DatePickerDialog
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import androidx.appcompat.app.AppCompatActivity
import com.example.startup1.databinding.ActivityUserReportBinding
import com.google.android.material.textfield.TextInputEditText
import java.text.SimpleDateFormat
import java.util.*

class UserReportActivity : AppCompatActivity() {
    private lateinit var binding: ActivityUserReportBinding
    private val calendar = Calendar.getInstance()
    private val dateFormatter = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserReportBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "User Attendance Report"

        setupUserSpinner()
        setupDatePickers()
        setupGenerateButton()
    }

    private fun setupUserSpinner() {
        // TODO: Replace with actual user list from database
        val users = listOf("John Doe", "Jane Smith", "Mike Johnson")
        val adapter = ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, users)
        (binding.userSpinner as? AutoCompleteTextView)?.setAdapter(adapter)
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
            // TODO: Implement report generation logic
            // 1. Validate inputs
            // 2. Query attendance data from database
            // 3. Generate and display report in RecyclerView
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
} 