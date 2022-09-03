package com.vapeart.presentation.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.navArgs
import com.vapeart.R
import com.vapeart.databinding.FragmentDetailsBinding
import com.vapeart.presentation.utils.Assistant
import kotlin.math.exp

class DetailsFragment : Fragment() {


    private val arguments: DetailsFragmentArgs by navArgs()
    private var _binding: FragmentDetailsBinding? = null
    private val binding: FragmentDetailsBinding
        get() = _binding ?: throw RuntimeException("DetailsFragment binding is null")

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentDetailsBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setFragmentContent()
        setAppendAndRemoveButtonListeners()
        setAddToCartButtonListener()
    }

    private fun setFragmentContent(){
        val item = arguments.item
        val image = arguments.image
        binding.apply {
            categoryNameTextView.text = item.category
            itemNameTextViewTop.text = item.name
            brendImageView.setImageResource(Assistant.brandsList.getOrDefault(item.manufacturer,R.drawable.logo))
            itemImageView.setImageBitmap(image)
            itemNameTextViewBottom.text = item.name
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

        }
    }

    private fun showToast(expression: String){
        Toast.makeText(requireContext(), expression, Toast.LENGTH_SHORT).show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}