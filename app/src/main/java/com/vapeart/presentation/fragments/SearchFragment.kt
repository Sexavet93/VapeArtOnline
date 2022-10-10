package com.vapeart.presentation.fragments

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.vapeart.databinding.FragmentSearchBinding
import com.vapeart.domain.models.Item
import com.vapeart.presentation.adapters.SearchFragmentAdapter
import com.vapeart.presentation.utils.Navigator
import com.vapeart.presentation.utils.OnQueryTextListenerImpl
import com.vapeart.presentation.viewmodels.SearchFragmentViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchFragment : Fragment() {

    private val viewModel: SearchFragmentViewModel by viewModels()
    private var _binding: FragmentSearchBinding? = null
    private val binding: FragmentSearchBinding
        get() = _binding ?: throw RuntimeException("SearchFragment binding is null")
    private lateinit var adapter: SearchFragmentAdapter
    private lateinit var navigator: Navigator
    private val timer: Handler = Handler(Looper.getMainLooper())
    private var itemList: List<Item> = emptyList()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        navigator = requireActivity() as Navigator
        adapter = SearchFragmentAdapter(navigator)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setSearchViewListener()
        setViewModelObserver()
        binding.recyclerView.apply {
            layoutManager = GridLayoutManager(requireContext(),2)
            adapter = this@SearchFragment.adapter
        }
    }

    private fun setSearchViewListener() {
        binding.searchView.apply {
            setOnQueryTextListener(object : OnQueryTextListenerImpl() {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    binding.defaultTextView.visibility = View.GONE
                    timer.postDelayed({
                        if(itemList.isEmpty()){
                            binding.defaultTextView.visibility = View.VISIBLE
                            binding.progressBar.visibility = View.GONE
                        }
                    },3000)
                    itemList = emptyList()
                    adapter.submitList(itemList)
                    binding.progressBar.visibility = View.VISIBLE
                    query?.let {viewModel.getSearchItems(it)}
                    clearFocus()
                    onActionViewCollapsed()
                    setQuery(query,false)
                    return true
                }
            })
        }
    }

    private fun setViewModelObserver(){
        viewModel.requestItemList.observe(viewLifecycleOwner){
            if(it.isNotEmpty()){
                itemList = it
                binding.progressBar.visibility = View.GONE
                adapter.submitList(it)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}