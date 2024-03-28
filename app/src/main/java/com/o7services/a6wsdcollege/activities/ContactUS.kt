package com.o7services.a6wsdcollege.activities

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.o7services.a6wsdcollege.R
import com.o7services.a6wsdcollege.databinding.ActivityContactUsBinding

class ContactUS : AppCompatActivity() {
    lateinit var binding:ActivityContactUsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding= ActivityContactUsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.tvphone1.setOnClickListener {
            val phoneNumber = "9914641645" // Replace this with the desired phone number
            val intent = Intent(Intent.ACTION_DIAL)
            intent.data = Uri.parse("tel:$phoneNumber")
            this.startActivity(intent)
        }
        binding.tvinsta.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://pcmsdcollege.com/"))
            startActivity(intent)
        }

        binding.tvfacebook.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://pcmsdcollege.com/"))
            startActivity(intent)
        }
    }
}