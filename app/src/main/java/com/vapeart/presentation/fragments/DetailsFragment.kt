package com.vapeart.presentation.fragments

import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.vapeart.R
import com.vapeart.data.room.FavoriteItem
import com.vapeart.data.room.SelectedItem
import com.vapeart.databinding.FragmentDetailsBinding
import com.vapeart.domain.Item
import com.vapeart.presentation.utils.Assistant
import com.vapeart.presentation.viewmodels.DetailsFragmentViewModel

const val  DEFAULT_ITEM_AMOUNT_SIZE = "0"

class DetailsFragment : Fragment() {

    private val viewModel: DetailsFragmentViewModel by activityViewModels()
    private val arguments: DetailsFragmentArgs by navArgs()
    private var _binding: FragmentDetailsBinding? = null
    private val binding: FragmentDetailsBinding
        get() = _binding ?: throw RuntimeException("DetailsFragment binding is null")
    private lateinit var item: Item
    private lateinit var selectedItem: SelectedItem
    private lateinit var favoriteItem: FavoriteItem

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentDetailsBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        item = arguments.item
        viewModel.getFavoriteItem(item.id)
        viewModel.getSelectedItem(item.id)
        setViewModelsObservers()
        setFragmentContent()
        setAppendAndRemoveButtonListeners()
        setAddToCartButtonListener()
        setByFromWhatsappButtonListener()
        setAddToFavoritesButtonListener()
    }

    private fun setViewModelsObservers(){
        viewModel.favoriteItemLiveData.observe(viewLifecycleOwner){
            favoriteItem = it ?: FavoriteItem(
                item.id,
                item.name,
                item.imageUri,
                item.currentPrice,
                item.manufacturer,
                false)
            setFavoriteButtonTextAndImage()
        }
        viewModel.selectedItemLiveData.observe(viewLifecycleOwner){
            selectedItem = it ?: SelectedItem(
                item.id,
                item.name,
                item.imageUri,
                item.currentPrice,
                item.manufacturer,
                0)
        }
    }

    private fun setFragmentContent(){
        binding.apply {
            categoryNameTextView.text = item.category
            itemNameTextViewTop.text = item.name
            brendImageView.setImageResource(Assistant.brandsList.getOrDefault(item.manufacturer,R.drawable.logo))
            Glide.with(root).load(item.imageUri).into(itemImageView)
            itemNameTextView.text = item.name
            currentPriceTextView.text = item.currentPrice
            descriptionTextView.text = item.description
            if(item.oldPrice != "0"){
                oldPriceTextView.text = item.oldPrice
                priceGroup.visibility = View.VISIBLE
            }
        }
    }

    private fun setAppendAndRemoveButtonListeners(){
        binding.apply {
            appendItemButton.setOnClickListener{
                val itemAmount = itemAmountTextView.text.toString().toInt() + 1
                itemAmountTextView.text = itemAmount.toString()
            }
            removeItemButton.setOnClickListener{
                var itemAmount = itemAmountTextView.text.toString().toInt()
                if(itemAmount > 0){
                    itemAmount--
                    itemAmountTextView.text = itemAmount.toString()
                }
            }
        }
    }

    private fun setAddToCartButtonListener(){
        binding.addToCartButton.setOnClickListener{
            try {
                val itemAmount = binding.itemAmountTextView.text.toString().toInt()
                if(itemAmount > 0){
                    selectedItem.amount += itemAmount
                    viewModel.addSelectedItem(selectedItem)
                    showToast(getString(R.string.item_added_to_cart))
                    binding.itemAmountTextView.text = DEFAULT_ITEM_AMOUNT_SIZE
                }else showToast(getString(R.string.item_quantity_warning))
            } catch (exception: Exception){
                showToast(getString(R.string.warning))
            }
        }
    }

    private fun setByFromWhatsappButtonListener(){
        binding.buyFromWhatsappButton.setOnClickListener{
            try {
                val pm = requireActivity().packageManager
                pm.getPackageInfo("com.whatsapp", PackageManager.GET_ACTIVITIES)
                val uri = Uri.parse("https://api.whatsapp.com/send?phone=+994508530313&text="
                        + getString(R.string.whatsapp_message) + item.name)
                val intent = Intent(Intent.ACTION_VIEW,uri)
                startActivity(intent)
            } catch (exception: Exception){
                showToast(getString(R.string.install_whatsapp))
            }
        }

    }

    private fun setAddToFavoritesButtonListener(){
        binding.apply {
            addToFavoritesButton.setOnClickListener{
                if(!favoriteItem.isFavorite){
                    favoriteItem.isFavorite = true
                    viewModel.addFavoriteItem(favoriteItem)
                    showToast(getString(R.string.add_favorite_item))
                } else {
                    favoriteItem.isFavorite = false
                    viewModel.deleteFavoriteItem(favoriteItem)
                    showToast(getString(R.string.delete_favorite_item))
                }
            }
        }
    }

    private fun showToast(expression: String){
        Toast.makeText(requireContext(), expression, Toast.LENGTH_SHORT).show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setFavoriteButtonTextAndImage(){
        binding.apply {
            if(favoriteItem.isFavorite){
                addToFavoritesButton.setImageResource(R.drawable.ic_favorite_checked)
                addToFavoriteTextView.text = getString(R.string.favorite_button_text_true)
            }
            else {
                addToFavoritesButton.setImageResource(R.drawable.ic_favorite_unchecked)
                addToFavoriteTextView.text = getString(R.string.favorite_button_text_false)
            }
        }
    }
}