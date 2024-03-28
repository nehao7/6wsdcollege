package com.o7services.a6wsdcollege.activities

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.o7services.a6wsdcollege.R
import com.o7services.a6wsdcollege.databinding.ActivityStatusBinding

class StatusActivity : AppCompatActivity() {

    lateinit var binding: ActivityStatusBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding= ActivityStatusBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.btnTeacher.setOnClickListener {
            startActivity(Intent(this,LoginActivity::class.java).putExtra("status",1))
        }
        binding.btnStudent.setOnClickListener {
            startActivity(Intent(this,LoginActivity::class.java).putExtra("status",0))
        }
    }
}