package com.vapeart.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.vapeart.databinding.FragmentItemsReviewBinding
import com.vapeart.presentation.adapters.ItemsReviewAdapter
import com.vapeart.presentation.utils.Navigator
import com.vapeart.presentation.viewmodels.ItemsReviewFragmentViewModel

class ItemsReviewFragment : Fragment() {

    private var _binding: FragmentItemsReviewBinding? = null
    private val binding: FragmentItemsReviewBinding
        get() = _binding ?: throw RuntimeException("ItemsReviewFragment binding is null")
    private val viewModel: ItemsReviewFragmentViewModel by viewModels()
    private lateinit var adapter: ItemsReviewAdapter
    private lateinit var query: String
    private lateinit var navigator: Navigator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        navigator = requireActivity() as Navigator
        adapter = ItemsReviewAdapter(emptyList(),navigator)
        query = arguments?.getString("query") ?: ""
        if(query.isNotEmpty()){
            viewModel.getQueryItems(query)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentItemsReviewBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setViewModelObserver()
        setRecyclerView()
    }

    private fun setViewModelObserver(){
        viewModel.queryLiveData.observe(viewLifecycleOwner){
            if(it.isNotEmpty()){
                adapter.setList(it)
                binding.defaultTextView.visibility = View.GONE
            } else {
                binding.defaultTextView.visibility = View.VISIBLE
            }
            binding.progressBar.visibility = View.GONE
        }
    }

    private fun setRecyclerView(){
        binding.recyclerView.apply {
            layoutManager = GridLayoutManager(requireContext(),2)
            adapter = this@ItemsReviewFragment.adapter
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}