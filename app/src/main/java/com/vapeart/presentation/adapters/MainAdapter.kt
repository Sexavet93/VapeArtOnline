package com.vapeart.presentation.adapters

import android.graphics.Bitmap
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.transition.Transition
import com.vapeart.databinding.MainAdapterItemLayoutBinding
import com.vapeart.domain.models.Item
import com.vapeart.presentation.utils.Navigator
import com.vapeart.presentation.fragments.HomeFragmentDirections
import com.vapeart.presentation.utils.GlideCustomTarget

class MainAdapter(private val navigator: Navigator, private var itemList: List<Item> = emptyList())
    : RecyclerView.Adapter<MainAdapter.MainViewHolder>() {

    fun setList(list: List<Item>){
        itemList = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainAdapter.MainViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = MainAdapterItemLayoutBinding.inflate(inflater,parent,false)
        return MainViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MainAdapter.MainViewHolder, position: Int) {
        val item = itemList[position]
        holder.binding.apply {
            Glide.with(root).asBitmap().load(item.imageUri).into(object: GlideCustomTarget<Bitmap>(){
                override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>?) {
                    progressBar.visibility = View.GONE
                    itemImage.setImageBitmap(resource)
                }
            })
            nameTextView.text = item.name
            currentPriceTextView.text = item.currentPrice
            if(item.oldPrice != "0"){
                priceGroup.visibility = View.VISIBLE
                oldPriceTextView.text = item.oldPrice
            }
        }
        holder.setOnClickListener(item)
    }

    override fun getItemCount() = itemList.size

    override fun onViewRecycled(holder: MainViewHolder) {
        holder.binding.apply {
            itemImage.setImageDrawable(null)
            progressBar.visibility = View.VISIBLE
            priceGroup.visibility = View.GONE
        }
    }

    inner class MainViewHolder(val binding: MainAdapterItemLayoutBinding): RecyclerView.ViewHolder(binding.root){

        fun setOnClickListener(item: Item){
            binding.root.setOnClickListener{
                navigator.navigate(HomeFragmentDirections.actionHomeFragmentToDetailsFragment(item))
            }
        }
    }

}