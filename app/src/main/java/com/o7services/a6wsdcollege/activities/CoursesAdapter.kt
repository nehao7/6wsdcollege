package com.o7services.a6wsdcollege.activities

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.o7services.a6wsdcollege.R
import com.o7services.a6wsdcollege.entities.Courses

class CoursesAdapter(private val courses: List<Courses>) :
    RecyclerView.Adapter<CoursesAdapter.CourseViewHolder>() {

    inner class CourseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nameTextView: TextView = itemView.findViewById(R.id.nameTextView)
        val subCoursesTextView: TextView = itemView.findViewById(R.id.subCoursesTextView)
        val durationTextView: TextView = itemView.findViewById(R.id.durationTextView)
        val entryLevelTextView: TextView = itemView.findViewById(R.id.entryLevelTextView)
        val mediumTextView: TextView = itemView.findViewById(R.id.mediumTextView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CourseViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.courses_item, parent, false)
        return CourseViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: CourseViewHolder, position: Int) {
        val currentCourse = courses[position]
        holder.nameTextView.text = currentCourse.name
        holder.subCoursesTextView.text = currentCourse.subCourses.joinToString(", ")
        holder.durationTextView.text = currentCourse.duration
        holder.entryLevelTextView.text = currentCourse.entryLevelQualification
        holder.mediumTextView.text = currentCourse.medium
    }

    override fun getItemCount() = courses.size
}