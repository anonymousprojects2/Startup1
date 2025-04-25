package com.example.startup1

import android.app.Application
import com.google.firebase.FirebaseApp

class StartupApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        // Initialize Firebase
        FirebaseApp.initializeApp(this)
    }
} 