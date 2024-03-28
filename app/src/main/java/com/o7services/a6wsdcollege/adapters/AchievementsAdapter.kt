package com.o7services.a6wsdcollege.adapters

import android.content.Context
import android.net.Uri
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.o7services.a6wsdcollege.R
import com.o7services.a6wsdcollege.databinding.AchievementsItemBinding
import com.o7services.a6wsdcollege.entities.AchievementEntity

class AchievementsAdapter(var context: Context,
                          var data: ArrayList<AchievementEntity>,
                          var imgview: ViewHandler
                      ): RecyclerView.Adapter<AchievementsAdapter.ViewHolder>() {
//    private var onClickListener: OnClickListener? = null

    inner class ViewHolder(var binding : AchievementsItemBinding): RecyclerView.ViewHolder(binding.root){


    }
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): AchievementsAdapter.ViewHolder {
        var binding = AchievementsItemBinding.inflate(LayoutInflater.from(parent.context), parent ,false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AchievementsAdapter.ViewHolder, position: Int) {
        holder.apply {

//            content://com.android.providers.media.documents/document/image%3A25
            if (data[position].image!=""){
//                binding.img.setImageURI(Uri.parse(data[position].image))

            }else
            {
                binding.img.setImageResource(R.drawable.ic_launcher_background)
            }
            binding.achievement.setText(data[position].achievement?:"")
            binding.description.setText(data[position].description)

            binding.tvupdate.setOnClickListener {
                imgview.viewHandler(position, DialogClickType.update)
            }

            binding.imgDelete.setOnClickListener {
                imgview.viewHandler(position, DialogClickType.Delete)
            }
            imgview.imgView(position,binding.img)
        }

    }

    override fun getItemCount(): Int {
        return data.size

    }

    interface ViewHandler {
        fun viewHandler(position: Int, clickType: DialogClickType)
        fun imgView(position: Int,imageView: ImageView)
    }
    enum class DialogClickType{
        Image, Delete,update
    }

}