package com.example.startup1.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.startup1.R
import com.example.startup1.model.AttendanceRecord
import java.text.SimpleDateFormat
import java.util.*

class AttendanceReportAdapter : RecyclerView.Adapter<AttendanceReportAdapter.ViewHolder>() {
    private var attendanceList: List<AttendanceRecord> = emptyList()
    private val dateFormatter = SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault())

    fun updateData(newList: List<AttendanceRecord>) {
        attendanceList = newList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_attendance_record, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val record = attendanceList[position]
        holder.bind(record)
    }

    override fun getItemCount(): Int = attendanceList.size

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val dateTextView: TextView = itemView.findViewById(R.id.dateTextView)
        private val timeInTextView: TextView = itemView.findViewById(R.id.timeInTextView)
        private val timeOutTextView: TextView = itemView.findViewById(R.id.timeOutTextView)
        private val statusTextView: TextView = itemView.findViewById(R.id.statusTextView)

        fun bind(record: AttendanceRecord) {
            dateTextView.text = dateFormatter.format(record.date)
            timeInTextView.text = "Time In: ${record.timeIn ?: "N/A"}"
            timeOutTextView.text = "Time Out: ${record.timeOut ?: "N/A"}"
            statusTextView.text = record.status
        }
    }
} 