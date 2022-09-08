package com.vapeart.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.vapeart.data.room.SelectedItem
import com.vapeart.databinding.FragmentCartBinding
import com.vapeart.presentation.adapters.CartAdapter
import com.vapeart.presentation.utils.ItemsManagerImpl
import com.vapeart.presentation.viewmodels.CartFragmentViewModel

class CartFragment : ItemsManagerImpl() {

    private var _binding: FragmentCartBinding? = null
    private val binding: FragmentCartBinding
        get() = _binding ?: throw RuntimeException("CartFragment binding is bull")
    private val viewModel: CartFragmentViewModel by viewModels()
    private lateinit var adapter: CartAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentCartBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setRecyclerViewAndAdapter()
        setViewModelObserver()
    }

    private fun setRecyclerViewAndAdapter(){
        adapter = CartAdapter(this)
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
            adapter.submitList(it)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun addToCart(item: SelectedItem) {
        viewModel.addSelectedItem(item)
    }

    override fun deleteFromCart(item: SelectedItem) {
        viewModel.deleteSelectedItem(item)
    }

}