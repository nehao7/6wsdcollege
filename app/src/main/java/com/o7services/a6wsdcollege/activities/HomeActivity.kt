package com.o7services.a6wsdcollege.activities

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.o7services.a6wsdcollege.R
import com.o7services.a6wsdcollege.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {
    lateinit var binding: ActivityHomeBinding
    var status=-1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding= ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        status=intent.getIntExtra("status",-1)
        Log.e("status", "onCreatehome: $status", )
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        binding.achievements.setOnClickListener {
            startActivity(Intent(this,AchievementsActivity::class.java))
        }
        binding.alumni.setOnClickListener {
            startActivity(Intent(this,PlacementsAlumniActivity::class.java))
        }
        binding.ourObjective.setOnClickListener {
            startActivity(Intent(this,OurObjective::class.java))
        }

        binding.ourVision.setOnClickListener {
            startActivity(Intent(this,OurVisionActivity::class.java))
        }
        binding.courses.setOnClickListener {
            startActivity(Intent(this,CoursesActivity::class.java))
        }

        binding.ourObjective.setOnClickListener {
            startActivity(Intent(this,OurObjective::class.java))
        }
        binding.PhotoGallery.setOnClickListener {
            startActivity(Intent(this,PhotoGallery::class.java))
        }
        binding.contactUs.setOnClickListener {
            startActivity(Intent(this,ContactUS::class.java))
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }
}