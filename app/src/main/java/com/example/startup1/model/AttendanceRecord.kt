package com.example.startup1.model

import java.util.Date

data class AttendanceRecord(
    val id: Long,
    val userId: Long,
    val date: Date,
    val timeIn: String?,
    val timeOut: String?,
    val status: String
) 