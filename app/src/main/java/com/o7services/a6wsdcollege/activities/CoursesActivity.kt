package com.o7services.a6wsdcollege.activities

import android.os.Bundle
import android.widget.ArrayAdapter
import android.window.OnBackInvokedDispatcher
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.o7services.a6wsdcollege.R
import com.o7services.a6wsdcollege.databinding.ActivityCoursesBinding
import com.o7services.a6wsdcollege.entities.Courses

class CoursesActivity : AppCompatActivity() {
    lateinit var binding:ActivityCoursesBinding
    lateinit var coursesAdapter: CoursesAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding= ActivityCoursesBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.image1.setOnClickListener {
            finish()
        }
        val courses = listOf(
            Courses(
                "Computer Science",
                listOf("Data Structures", "Algorithms", "Database Management"),
                "4 years",
                "High School Diploma",
                "English"
            ),
            Courses(
                "Electrical Engineering",
                listOf("Circuit Analysis", "Electromagnetics", "Digital Electronics"),
                "4 years",
                "High School Diploma",
                "English"
            ),
            Courses(
                "Psychology",
                listOf("Behavioral Psychology", "Cognitive Psychology", "Social Psychology"),
                "4 years",
                "High School Diploma",
                "English"
            ),
            Courses(
                "Web Designing Diploma",
                listOf("HTML", "CSS", "JavaScript"),
                "1 years",
                "High School Diploma",
                "English"
            ),
            Courses(
                "Computer Science Diploma",
                listOf("Programming", "Database Management", "Networking"),
                "2 years",
                "High School Diploma",
                "English"
            )


            // Add more courses as needed
        )

        coursesAdapter = CoursesAdapter(courses)
        binding.recyclerlist.adapter = coursesAdapter
        binding.recyclerlist.layoutManager = LinearLayoutManager(this)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }
}