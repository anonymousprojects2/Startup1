package com.example.startup1.utils

import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.gotrue.GoTrue
import io.github.jan.supabase.postgrest.Postgrest

object SupabaseClient {
    // TODO: Replace these values with your Supabase project settings
    // Go to: Project Settings > API
    // Project URL example: https://xxxxxxxxxxxx.supabase.co
    private const val SUPABASE_URL = "https://dsddqdxmtmclzysvedkz.supabase.co"
    
    // anon/public key example: eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.xxx
    private const val SUPABASE_KEY = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6ImRzZGRxZHhtdG1jbHp5c3ZlZGt6Iiwicm9sZSI6ImFub24iLCJpYXQiOjE3NDU1ODkyMzcsImV4cCI6MjA2MTE2NTIzN30.wrICdP09P8LNKFFnanaXwWxwJFIL6JrJRMfEISaDJv4"

    val client = createSupabaseClient(
        supabaseUrl = SUPABASE_URL,
        supabaseKey = SUPABASE_KEY
    ) {
        install(GoTrue)
        install(Postgrest)
    }
} 