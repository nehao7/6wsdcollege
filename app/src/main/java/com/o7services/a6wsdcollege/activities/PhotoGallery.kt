package com.o7services.a6wsdcollege.activities

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.o7services.a6wsdcollege.R
import com.o7services.a6wsdcollege.adapters.GalleryAdapter
import com.o7services.a6wsdcollege.databinding.ActivityPhotoGalleryBinding

class PhotoGallery : AppCompatActivity() {
    lateinit var binding: ActivityPhotoGalleryBinding
    lateinit var galleryAdapterClass: GalleryAdapter
    var arrayList = arrayListOf(
        R.drawable.pcmsd,
        R.drawable.pcmsd2,
        R.drawable.pcmsd3,
        R.drawable.pcmsd4,
        R.drawable.motto
    )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding= ActivityPhotoGalleryBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        galleryAdapterClass = GalleryAdapter(this, arrayList)
        binding.recycler.layoutManager = LinearLayoutManager(this)
        binding.recycler.adapter = galleryAdapterClass


    }
}