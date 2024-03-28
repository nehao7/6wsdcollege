package com.o7services.a6wsdcollege.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.o7services.a6wsdcollege.databinding.LayoutOurpridePageBinding
import com.o7services.a6wsdcollege.entities.AchievementEntity
import com.o7services.a6wsdcollege.entities.OurPrideEntity

class Ourpride_Adapter (var context: Context,
                        var data: ArrayList<OurPrideEntity>,
                        var imgview: ViewHandler
):RecyclerView.Adapter<Ourpride_Adapter.ViewHolder>(){
   inner class ViewHolder (var binding: LayoutOurpridePageBinding):RecyclerView.ViewHolder(binding.root){
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):Ourpride_Adapter. ViewHolder{
        val binding=LayoutOurpridePageBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return  data.size
    }

    override fun onBindViewHolder(holder: Ourpride_Adapter.ViewHolder, position: Int) {
        holder.apply {
            binding.tvOurPride.setText(data[position].text)
            binding.tvupdate.setOnClickListener {
                imgview.viewHandler(position, DialogClickType.update)
            }
            binding.imgDelete.setOnClickListener {
                imgview.viewHandler(position, DialogClickType.Delete)
            }
            imgview.imgView(position,binding.img)
        }
    }

    interface ViewHandler {
        fun viewHandler(position: Int, clickType: DialogClickType)
        fun imgView(position: Int,imageView: ImageView)
    }
    enum class DialogClickType{
        Image, Delete,update
    }

}