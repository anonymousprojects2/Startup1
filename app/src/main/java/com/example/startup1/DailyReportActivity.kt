package com.example.startup1

import android.app.DatePickerDialog
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.startup1.databinding.ActivityDailyReportBinding
import java.text.SimpleDateFormat
import java.util.*

class DailyReportActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDailyReportBinding
    private val calendar = Calendar.getInstance()
    private val dateFormatter = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDailyReportBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Daily Attendance Summary"

        setupDatePicker()
        setupGenerateButton()
        setupRecyclerView()
    }

    private fun setupDatePicker() {
        binding.dateInput.setOnClickListener {
            showDatePicker()
        }
    }

    private fun showDatePicker() {
        val datePickerDialog = DatePickerDialog(
            this,
            { _, year, month, day ->
                calendar.set(year, month, day)
                binding.dateInput.setText(dateFormatter.format(calendar.time))
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
            // 1. Validate date input
            // 2. Query attendance data from database for selected date
            // 3. Update RecyclerView with results
        }
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