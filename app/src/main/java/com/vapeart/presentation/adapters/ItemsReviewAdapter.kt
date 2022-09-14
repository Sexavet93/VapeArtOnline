package com.vapeart.presentation.adapters

import android.app.Notification
import android.graphics.Bitmap
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.transition.Transition
import com.vapeart.R
import com.vapeart.databinding.ItemReviewLayoutBinding
import com.vapeart.domain.Item
import com.vapeart.presentation.fragments.ItemsReviewFragmentDirections
import com.vapeart.presentation.utils.GlideCustomTarget
import com.vapeart.presentation.utils.Navigator

class ItemsReviewAdapter(
    @JvmField var itemList: List<Item>, val navigator: Navigator) :
    RecyclerView.Adapter<ItemsReviewAdapter.ItemsViewHolder>() {

    fun setList(itemList: List<Item>){
        this.itemList = itemList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemsViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemReviewLayoutBinding.inflate(inflater,parent,false)
        return  ItemsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ItemsViewHolder, position: Int) {
        val item = itemList[position]
        holder.binding.apply {
            Glide.with(root).asBitmap().load(item.imageUri)
                .into(object : GlideCustomTarget<Bitmap>() {
                    override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>?) {
                        progressBar.visibility = View.GONE
                        itemImageView.setImageBitmap(resource)
                    }
                })
            categoryNameTextView.text = item.category
            itemNameTextView.text = item.name
            if (item.oldPrice != "0") {
                viewGroup.visibility = View.VISIBLE
                oldPriceTextView.text = item.oldPrice
            }
            currentPriceTextView.text = item.currentPrice
        }
        holder.binding.root.setOnClickListener{
            navigator.navigate(ItemsReviewFragmentDirections.actionItemsReviewFragmentToDetailsFragment(item))
        }
    }

    override fun getItemCount() = itemList.size

    override fun onViewRecycled(holder: ItemsViewHolder) {
        holder.binding.progressBar.visibility = View.VISIBLE
        holder.binding.viewGroup.visibility = View.GONE
    }


    inner class ItemsViewHolder(val binding: ItemReviewLayoutBinding)
        : RecyclerView.ViewHolder(binding.root)
}