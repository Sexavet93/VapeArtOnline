package com.vapeart.presentation.adapters

import android.graphics.Bitmap
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.transition.Transition
import com.vapeart.R
import com.vapeart.data.room.FavoriteItem
import com.vapeart.data.room.SelectedItem
import com.vapeart.databinding.WishListItemLayoutBinding
import com.vapeart.presentation.fragments.DEFAULT_ITEM_AMOUNT_SIZE
import com.vapeart.presentation.utils.Assistant
import com.vapeart.presentation.utils.DiffCallbacks
import com.vapeart.presentation.utils.GlideCustomTarget
import com.vapeart.presentation.utils.ItemsManager

class WishListAdapter(
    private val callback: ItemsManager
) : ListAdapter<FavoriteItem, WishListAdapter.WishListHolder>(DiffCallbacks.wishListDiffCallback) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WishListAdapter.WishListHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = WishListItemLayoutBinding.inflate(inflater,parent,false)
        return WishListHolder(binding)
    }

    override fun onBindViewHolder(holder: WishListAdapter.WishListHolder, position: Int) {
        val item = getItem(position)
        holder.binding.apply {
            Glide.with(root).asBitmap().load(item.imageUri).into(object : GlideCustomTarget<Bitmap>() {
                    override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>?) {
                        progressBar.visibility = View.GONE
                        itemImageView.setImageBitmap(resource)
                    }
                })
            brandImageView.setImageResource(Assistant.brandsList.getOrDefault(item.manufacturer,R.drawable.logo))
            itemNameTextView.text = item.itemName
            currentPriceTextView.text = item.currentPrice
        }

        holder.setOnAddToCartButtonListener(item)
        holder.setOnDeleteButtonListener(item)
        holder.setAppendAndRemoveButtonListeners()
    }

    override fun onViewRecycled(holder: WishListAdapter.WishListHolder) {
        holder.binding.progressBar.visibility = View.VISIBLE
    }

    inner class WishListHolder(val binding: WishListItemLayoutBinding):
        RecyclerView.ViewHolder(binding.root) {

        fun setOnAddToCartButtonListener(item: FavoriteItem) {
            binding.addToCartButton.setOnClickListener {
                var itemQuantity: Int = try {
                    binding.itemAmountTextView.text.toString().toInt()
                } catch (exception: Exception) {
                    0
                }
                if (itemQuantity > 0) {
                    val selectedItem = SelectedItem(
                        item.id,
                        item.itemName,
                        item.imageUri,
                        item.currentPrice,
                        item.manufacturer,
                        itemQuantity
                    )
                    callback.addToCart(selectedItem)
                    binding.itemAmountTextView.text = DEFAULT_ITEM_AMOUNT_SIZE
                }
            }
        }

        fun setAppendAndRemoveButtonListeners() {
            binding.apply {
                appendItemButton.setOnClickListener {
                    val itemAmount = itemAmountTextView.text.toString().toInt() + 1
                    itemAmountTextView.text = itemAmount.toString()
                }
                removeItemButton.setOnClickListener {
                    var itemAmount = itemAmountTextView.text.toString().toInt()
                    if (itemAmount > 0) {
                        itemAmount--
                        itemAmountTextView.text = itemAmount.toString()
                    }
                }
            }
        }

        fun setOnDeleteButtonListener(item: FavoriteItem) {
            binding.deleteButton.setOnClickListener {
                callback.deleteItem(item)
            }
        }
    }
}