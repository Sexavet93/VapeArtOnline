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
import com.vapeart.data.room.SelectedItem
import com.vapeart.databinding.CartListItemLayoutBinding
import com.vapeart.presentation.utils.Assistant
import com.vapeart.presentation.utils.DiffCallbacks
import com.vapeart.presentation.utils.GlideCustomTarget
import com.vapeart.presentation.utils.ItemsManager

class CartAdapter(private var itemList: List<SelectedItem>,private val itemsManager: ItemsManager)
    : RecyclerView.Adapter<CartAdapter.CartViewHolder>(){

    fun setItemsList(list: List<SelectedItem>){
        itemList = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartAdapter.CartViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.cart_list_item_layout,parent,false)
        return CartViewHolder(view)
    }

    override fun onBindViewHolder(holder: CartAdapter.CartViewHolder, position: Int) {
        val item = itemList[position]
        holder.binding.apply {
            Glide.with(root).asBitmap().load(item.imageUri).into(object : GlideCustomTarget<Bitmap>() {
                override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>?) {
                    progressBar.visibility = View.GONE
                    itemImageView.setImageBitmap(resource)
                }
            })
            brendImageView.setImageResource(Assistant.brandsList.getOrDefault(item.manufacturer,R.drawable.logo))
            itemNameTextView.text = item.itemName
            itemAmountTextView.text = item.amount.toString()
            val totalPrice: Double = try {
                item.currentPrice.toDouble() * item.amount
            }catch(e: Exception){ 0.00 }
            currentPriceTextView.text = String.format("%.2f", totalPrice)
        }
        holder.setButtonListeners(item)
    }

    override fun getItemCount() = itemList.size

    inner class CartViewHolder(view: View): RecyclerView.ViewHolder(view){

        val binding = CartListItemLayoutBinding.bind(view)

        fun setButtonListeners(item: SelectedItem){
            binding.apply {
                deleteButton.setOnClickListener{
                    itemsManager.deleteFromCart(item)
                }
                appendItemButton.setOnClickListener {
                    val itemAmount = itemAmountTextView.text.toString().toInt() + 1
                    itemAmountTextView.text = itemAmount.toString()
                    item.amount = itemAmount
                    itemsManager.addToCart(item)
                }
                removeItemButton.setOnClickListener {
                    var itemAmount = itemAmountTextView.text.toString().toInt()
                    if (itemAmount > 1) {
                        itemAmount--
                        itemAmountTextView.text = itemAmount.toString()
                        item.amount = itemAmount
                        itemsManager.addToCart(item)
                    }
                }
            }
        }
    }
}






















//    ListAdapter<SelectedItem, CartAdapter.CartViewHolder>(DiffCallbacks.cartListDiffCallback) {
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartAdapter.CartViewHolder {
//        val view = LayoutInflater.from(parent.context).inflate(R.layout.cart_list_item_layout,parent,false)
//        return CartViewHolder(view)
//    }
//
//    override fun onBindViewHolder(holder: CartAdapter.CartViewHolder, position: Int) {
//        val item = currentList[position]
//        holder.binding.apply {
//            Glide.with(root).asBitmap().load(item.imageUri).into(object : GlideCustomTarget<Bitmap>() {
//                override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>?) {
//                    progressBar.visibility = View.GONE
//                    itemImageView.setImageBitmap(resource)
//                }
//            })
//            brendImageView.setImageResource(Assistant.brandsList.getOrDefault(item.manufacturer,R.drawable.logo))
//            itemNameTextView.text = item.itemName
//            itemAmountTextView.text = item.amount.toString()
//            val totalPrice: Double = try {
//                item.currentPrice.toDouble() * item.amount
//            }catch(e: Exception){ 0.00 }
//            currentPriceTextView.text = String.format("%.2f", totalPrice)
//        }
//        holder.setButtonListeners(item)
//    }
//
//    inner class CartViewHolder(view: View): RecyclerView.ViewHolder(view){
//
//        val binding = CartListItemLayoutBinding.bind(view)
//
//        fun setButtonListeners(item: SelectedItem){
//            binding.apply {
//                deleteButton.setOnClickListener{
//                    itemsManager.deleteFromCart(item)
//                }
//                appendItemButton.setOnClickListener {
//                    val itemAmount = itemAmountTextView.text.toString().toInt() + 1
//                    itemAmountTextView.text = itemAmount.toString()
//                    item.amount = itemAmount
//                    itemsManager.addToCart(item)
//                }
//                removeItemButton.setOnClickListener {
//                    var itemAmount = itemAmountTextView.text.toString().toInt()
//                    if (itemAmount > 1) {
//                        itemAmount--
//                        itemAmountTextView.text = itemAmount.toString()
//                        item.amount = itemAmount
//                        itemsManager.addToCart(item)
//                    }
//                }
//            }
//        }
//    }
//}