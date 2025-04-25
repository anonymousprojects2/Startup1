package com.example.startup1.utils

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage

object FirebaseClient {
    val firestore: FirebaseFirestore = FirebaseFirestore.getInstance()
    val storage: FirebaseStorage = FirebaseStorage.getInstance()

    // Collection references
    private const val USERS_COLLECTION = "users"
    private const val LOCATIONS_COLLECTION = "locations"
    private const val ATTENDANCE_COLLECTION = "attendance"

    fun getUsersCollection() = firestore.collection(USERS_COLLECTION)
    fun getLocationsCollection() = firestore.collection(LOCATIONS_COLLECTION)
    fun getAttendanceCollection() = firestore.collection(ATTENDANCE_COLLECTION)
} 