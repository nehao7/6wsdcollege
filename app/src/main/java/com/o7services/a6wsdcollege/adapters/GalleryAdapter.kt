package com.o7services.a6wsdcollege.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.o7services.a6wsdcollege.databinding.GalleryViewBinding

class GalleryAdapter(var context: Context, var arrayList : ArrayList<Int>): RecyclerView.Adapter<GalleryAdapter.ViewHolder>() {
    inner class ViewHolder (var binding: GalleryViewBinding): RecyclerView.ViewHolder(binding.root){

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):GalleryAdapter.ViewHolder {
        val binding=GalleryViewBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: GalleryAdapter.ViewHolder, position: Int) {
        holder.apply {
            binding.tvimage.setImageResource(arrayList[position])
        }
    }

    override fun getItemCount(): Int {
        return arrayList.size
    }
}