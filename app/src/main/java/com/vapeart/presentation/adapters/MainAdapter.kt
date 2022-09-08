package com.vapeart.presentation.adapters

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.transition.Transition
import com.vapeart.R
import com.vapeart.databinding.MainAdapterItemLayoutBinding
import com.vapeart.domain.Item
import com.vapeart.presentation.utils.Navigator
import com.vapeart.presentation.fragments.HomeFragmentDirections
import com.vapeart.presentation.utils.DiffCallbacks
import com.vapeart.presentation.utils.GlideCustomTarget
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream

class MainAdapter(private val navigator: Navigator):
    ListAdapter<Item, MainAdapter.MainViewHolder>(DiffCallbacks.mainAdapterDiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainAdapter.MainViewHolder {
        val view = LayoutInflater
            .from(parent.context).inflate(R.layout.main_adapter_item_layout,parent,false)
        return MainViewHolder(view)
    }

    override fun onBindViewHolder(holder: MainAdapter.MainViewHolder, position: Int) {
        val item = currentList[position]
        holder.binding.apply {
            Glide.with(root).asBitmap().load(item.imageUri).into(object: GlideCustomTarget<Bitmap>(){
                override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>?) {
                    progressBar.visibility = View.GONE
                    itemImage.setImageBitmap(resource)
                    holder.setOnClickListener(item,resource)
                }
            })
            nameTextView.text = item.name
            currentPriceTextView.text = item.currentPrice
            if(item.oldPrice != "0"){
                priceGroup.visibility = View.VISIBLE
                oldPriceTextView.text = item.oldPrice
            }
        }
    }

    override fun onViewRecycled(holder: MainViewHolder) {
        holder.binding.progressBar.visibility = View.VISIBLE
        holder.binding.priceGroup.visibility = View.GONE
    }

    inner class MainViewHolder(view: View): RecyclerView.ViewHolder(view){
        val binding: MainAdapterItemLayoutBinding = MainAdapterItemLayoutBinding.bind(view)
        var isOnClickListenerSet: Boolean = false

        fun setOnClickListener(item: Item, photo: Bitmap){
            if(!isOnClickListenerSet){
                isOnClickListenerSet = true
                binding.root.setOnClickListener{
                    navigator.navigate(HomeFragmentDirections.actionHomeFragmentToDetailsFragment(item,photo))
                }
            }
        }
    }

}