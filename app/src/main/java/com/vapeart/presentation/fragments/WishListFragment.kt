package com.vapeart.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.vapeart.R
import com.vapeart.data.room.FavoriteItem
import com.vapeart.data.room.SelectedItem
import com.vapeart.databinding.FragmentWishListBinding
import com.vapeart.presentation.adapters.WishListAdapter
import com.vapeart.presentation.utils.ItemsManagerImpl
import com.vapeart.presentation.viewmodels.WishListFragmentViewModel

class WishListFragment : Fragment() {

    private var _binding: FragmentWishListBinding? = null
    private val binding: FragmentWishListBinding
        get() = _binding ?: throw RuntimeException("WishListFragment binding is null")
    private val viewModel: WishListFragmentViewModel by activityViewModels()
    private lateinit var adapter: WishListAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentWishListBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = WishListAdapter(object : ItemsManagerImpl(){
            override fun addToCart(item: SelectedItem) {
                viewModel.addSelectedItem(item)
                showToast(getString(R.string.item_added_to_cart))
            }
            override fun deleteItem(item: FavoriteItem) {
                viewModel.deleteFavoriteItem(item)
                showToast(getString(R.string.delete_favorite_item))
            }
        })
        viewModel.favoriteItemsLiveData.observe(viewLifecycleOwner){
            adapter.submitList(it)
            setDefaultTextView(it.isEmpty())
        }
        setRecyclerView()
    }

    private fun setRecyclerView(){
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.adapter = adapter
    }

    private fun setDefaultTextView(isEmpty: Boolean){
        if(!isEmpty){
            binding.defaultTextView.visibility = View.GONE
        } else {
            binding.defaultTextView.visibility = View.VISIBLE
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