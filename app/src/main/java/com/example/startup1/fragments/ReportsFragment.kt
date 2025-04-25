package com.example.startup1.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import com.example.startup1.DailyReportActivity
import com.example.startup1.LocationReportActivity
import com.example.startup1.R
import com.example.startup1.UserReportActivity

class ReportsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_reports, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Set up click listeners for report cards
        view.findViewById<CardView>(R.id.userReportsCard).setOnClickListener {
            val intent = Intent(requireContext(), UserReportActivity::class.java)
            startActivity(intent)
        }

        view.findViewById<CardView>(R.id.dailyReportsCard).setOnClickListener {
            val intent = Intent(requireContext(), DailyReportActivity::class.java)
            startActivity(intent)
        }

        view.findViewById<CardView>(R.id.locationReportsCard).setOnClickListener {
            val intent = Intent(requireContext(), LocationReportActivity::class.java)
            startActivity(intent)
        }
    }

    companion object {
        fun newInstance() = ReportsFragment()
    }
} 