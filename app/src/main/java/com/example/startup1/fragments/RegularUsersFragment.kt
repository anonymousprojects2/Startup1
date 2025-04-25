package com.example.startup1.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.startup1.AddUserActivity
import com.example.startup1.R
import com.google.android.material.floatingactionbutton.FloatingActionButton

class RegularUsersFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_regular_users, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<FloatingActionButton>(R.id.fabAddUser).setOnClickListener {
            // Launch AddUserActivity
            val intent = Intent(requireContext(), AddUserActivity::class.java)
            startActivity(intent)
        }
    }

    companion object {
        fun newInstance() = RegularUsersFragment()
    }
} 