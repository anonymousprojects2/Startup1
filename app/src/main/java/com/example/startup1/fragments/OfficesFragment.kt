package com.example.startup1.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.startup1.AddLocationActivity
import com.example.startup1.R
import com.google.android.material.floatingactionbutton.FloatingActionButton

class OfficesFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_offices, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<FloatingActionButton>(R.id.fabAddOffice).setOnClickListener {
            val intent = Intent(requireContext(), AddLocationActivity::class.java)
            startActivity(intent)
        }
    }

    companion object {
        fun newInstance() = OfficesFragment()
    }
}