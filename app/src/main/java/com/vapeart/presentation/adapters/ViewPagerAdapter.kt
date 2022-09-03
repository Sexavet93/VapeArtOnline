package com.vapeart.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.vapeart.R

class ViewPagerAdapter(private val photoList: List<Int>): RecyclerView.Adapter<RecyclerView.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.viewpager_item,parent,false)
        return object: RecyclerView.ViewHolder(view){}
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder.itemView as ImageView).setImageResource(photoList[position])
    }

    override fun getItemCount(): Int = photoList.size
}