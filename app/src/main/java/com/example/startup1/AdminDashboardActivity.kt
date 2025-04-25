package com.example.startup1

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.example.startup1.adapters.ViewPagerAdapter
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import android.widget.TextView
import android.widget.ImageView
import java.text.SimpleDateFormat
import java.util.*

class AdminDashboardActivity : AppCompatActivity() {
    private lateinit var viewPager: ViewPager2
    private lateinit var tabLayout: TabLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin_dashboard)

        // Initialize views
        viewPager = findViewById(R.id.viewPager)
        tabLayout = findViewById(R.id.tabLayout)

        // Setup header
        setupHeader()
        
        // Setup click listeners
        setupClickListeners()

        // Set up ViewPager with adapter
        val adapter = ViewPagerAdapter(this)
        viewPager.adapter = adapter

        // Connect TabLayout with ViewPager
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = when (position) {
                0 -> "Regular Users"
                1 -> "Offices"
                2 -> "Reports"
                else -> null
            }
        }.attach()
    }

    private fun setupHeader() {
        // Set greeting with time-based message
        val greetingText = findViewById<TextView>(R.id.greetingText)
        val calendar = Calendar.getInstance()
        val greeting = when (calendar.get(Calendar.HOUR_OF_DAY)) {
            in 0..11 -> "Good morning"
            in 12..16 -> "Good afternoon"
            else -> "Good evening"
        }
        greetingText.text = "$greeting, Jay Jobanputra"

        // Set current date
        val dateText = findViewById<TextView>(R.id.dateText)
        val dateFormat = SimpleDateFormat("EEEE, dd MMMM yyyy", Locale.getDefault())
        dateText.text = dateFormat.format(Date())
    }

    private fun setupClickListeners() {
        // Setup logout click listener
        findViewById<ImageView>(R.id.logoutIcon).setOnClickListener {
            finish()
        }

        // Setup refresh click listener
        findViewById<ImageView>(R.id.refreshIcon).setOnClickListener {
            // TODO: Implement refresh functionality
        }

        // Setup settings click listener
        findViewById<ImageView>(R.id.settingsIcon).setOnClickListener {
            // TODO: Implement settings functionality
        }
    }
} 