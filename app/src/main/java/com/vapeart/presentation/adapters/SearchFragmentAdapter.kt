package com.vapeart.presentation.adapters

import android.graphics.Bitmap
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.transition.Transition
import com.vapeart.databinding.ItemReviewLayoutBinding
import com.vapeart.domain.Item
import com.vapeart.presentation.fragments.SearchFragmentDirections
import com.vapeart.presentation.utils.DiffCallbacks
import com.vapeart.presentation.utils.GlideCustomTarget
import com.vapeart.presentation.utils.Navigator

class SearchFragmentAdapter(val navigator: Navigator)
    : ListAdapter<Item, SearchFragmentAdapter.SearchAdapterHolder>(DiffCallbacks.mainAdapterDiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchAdapterHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemReviewLayoutBinding.inflate(inflater,parent,false)
        return SearchAdapterHolder(binding)
    }

    override fun onBindViewHolder(holder: SearchAdapterHolder, position: Int) {
        val item = currentList[position]
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
            navigator.navigate(SearchFragmentDirections.actionSearchFragmentToDetailsFragment(item))
        }
    }

    inner class SearchAdapterHolder(val binding: ItemReviewLayoutBinding):
        RecyclerView.ViewHolder(binding.root)

}
