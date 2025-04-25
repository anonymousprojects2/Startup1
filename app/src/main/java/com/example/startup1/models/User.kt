package com.example.startup1.models

import kotlinx.serialization.Serializable

@Serializable
data class User(
    val id: String? = null,
    val sevarthId: String,
    val firstName: String,
    val lastName: String,
    val email: String,
    val phone: String,
    val dob: String,
    val gender: String,
    val location: String,
    val role: String = "user",
    val createdAt: Long = System.currentTimeMillis()
) 