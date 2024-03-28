package com.o7services.a6wsdcollege.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.o7services.a6wsdcollege.R
import com.o7services.a6wsdcollege.databinding.AlumniPrideItemBinding
import com.o7services.a6wsdcollege.entities.PlacementsAlumniEntity

class AlumniPlacementAdapter(var context: Context,
                             var data: ArrayList<PlacementsAlumniEntity>,
                             var imgview: ViewHandler
                      ): RecyclerView.Adapter<AlumniPlacementAdapter.ViewHolder>() {
//    private var onClickListener: OnClickListener? = null

    inner class ViewHolder(var binding : AlumniPrideItemBinding): RecyclerView.ViewHolder(binding.root){
    }
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): AlumniPlacementAdapter.ViewHolder {
        var binding = AlumniPrideItemBinding.inflate(LayoutInflater.from(parent.context), parent ,false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AlumniPlacementAdapter.ViewHolder, position: Int) {
        holder.apply {

//            content://com.android.providers.media.documents/document/image%3A25
            if (data[position].image!=""){
//                binding.img.setImageURI(Uri.parse(data[position].image))

            }else
            {
                binding.img.setImageResource(R.drawable.ic_launcher_background)
            }
            binding.tvOurPride.setText(data[position].alumni?:"")
            binding.tvplacements.setText(data[position].placement)

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