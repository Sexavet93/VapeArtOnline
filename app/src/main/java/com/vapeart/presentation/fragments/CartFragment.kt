package com.vapeart.presentation.fragments

import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.vapeart.R
import com.vapeart.data.room.SelectedItem
import com.vapeart.databinding.FragmentCartBinding
import com.vapeart.presentation.adapters.CartAdapter
import com.vapeart.presentation.utils.ItemsManagerImpl
import com.vapeart.presentation.viewmodels.CartFragmentViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CartFragment : Fragment() {

    private var _binding: FragmentCartBinding? = null
    private val binding: FragmentCartBinding
        get() = _binding ?: throw RuntimeException("CartFragment binding is bull")
    private val viewModel: CartFragmentViewModel by viewModels()
    private lateinit var adapter: CartAdapter
    private var itemsList: List<SelectedItem> = emptyList()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentCartBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setRecyclerViewAndAdapter()
        setViewModelObserver()
        setBottomButtonsListeners()
    }

    private fun setRecyclerViewAndAdapter(){
        adapter = CartAdapter(object : ItemsManagerImpl(){
            override fun addToCart(item: SelectedItem) {
                viewModel.addSelectedItem(item)
            }
            override fun deleteFromCart(item: SelectedItem) {
                viewModel.deleteSelectedItem(item)
            }
        })
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(requireActivity())
            adapter = this@CartFragment.adapter
        }
    }

    private fun setViewModelObserver(){
        viewModel.selectedItemsLiveData.observe(viewLifecycleOwner){
            if(it.isNotEmpty()){
                binding.apply {
                    defaultTextView.visibility = View.GONE
                    bottomButtons.visibility = View.VISIBLE
                }
            } else{
                binding.apply {
                    defaultTextView.visibility = View.VISIBLE
                    bottomButtons.visibility = View.GONE
                }
            }
            itemsList = it.sortedByDescending { item -> item.amount }
            adapter.submitList(itemsList)
        }
    }

    private fun setBottomButtonsListeners(){
        binding.clearAllCartButton.setOnClickListener{
            val dialog = AlertDialog.Builder(requireContext())
                .setPositiveButton("Ok",){ _, _ ->
                    viewModel.deleteAllItems()
                }
                .setNegativeButton("Cancel",null)
                .setMessage(R.string.delete_all_items_from_cart_dialog_message)
                .create().show()
        }

        binding.buyFromWhatsappButton.setOnClickListener{
            try {
                val pm = requireActivity().packageManager
                pm.getPackageInfo("com.whatsapp", PackageManager.GET_ACTIVITIES)
                var message = ""
                itemsList.forEach{
                    message += it.itemName + "\n"
                }
                val uri = Uri.parse("https://api.whatsapp.com/send?phone=+994508530313&text="
                        + getString(R.string.whatsapp_message) + message)
                val intent = Intent(Intent.ACTION_VIEW,uri)
                startActivity(intent)
            } catch (exception: Exception){
                showToast(getString(R.string.install_whatsapp))
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

}