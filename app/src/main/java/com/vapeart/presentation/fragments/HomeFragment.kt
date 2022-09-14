package com.vapeart.presentation.fragments

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.tabs.TabLayoutMediator
import com.vapeart.databinding.FragmentHomeBinding
import com.vapeart.presentation.adapters.MainAdapter
import com.vapeart.presentation.adapters.ViewPagerAdapter
import com.vapeart.presentation.utils.Assistant
import com.vapeart.presentation.utils.Navigator
import com.vapeart.presentation.viewmodels.HomeFragmentViewModel
import dagger.hilt.android.AndroidEntryPoint

private const val DELAY = 3500L

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private lateinit var navigator: Navigator
    private lateinit var viewPagerAdapter: ViewPagerAdapter
    private lateinit var viewPagerTask: Runnable
    private lateinit var adapterForBestSellersRv: MainAdapter
    private lateinit var adapterForNewItemRv: MainAdapter
    private lateinit var adapterForDiscountsRv: MainAdapter
    private val viewModel: HomeFragmentViewModel by viewModels()
    private val handler = Handler(Looper.getMainLooper())
    private var _binding: FragmentHomeBinding? = null
    private val binding: FragmentHomeBinding
        get() = _binding ?: throw RuntimeException("HomeFragment binding is null")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        navigator = requireActivity() as Navigator
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentHomeBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setViewPager()
        setAdapters()
        setViewModelObservers()
    }

    private fun setViewPager() {
        val imageList = Assistant.viewPagerImagesList
        viewPagerAdapter = ViewPagerAdapter(imageList)
        binding.viewPager.adapter = viewPagerAdapter
        TabLayoutMediator(binding.tabLayout, binding.viewPager) { _, _ -> }.attach()
        setImageScrolling(1, imageList.size)
    }

    private fun setAdapters(){
        adapterForBestSellersRv = MainAdapter(navigator)
        adapterForNewItemRv = MainAdapter(navigator)
        adapterForDiscountsRv = MainAdapter(navigator)
        binding.apply {
            bestSellersRV.layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL,false)
            newProductsRV.layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL,false)
            discountsRV.layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL,false)
            bestSellersRV.adapter = adapterForBestSellersRv
            newProductsRV.adapter = adapterForNewItemRv
            discountsRV.adapter = adapterForDiscountsRv
        }
    }

    private fun setViewModelObservers(){
        viewModel.bestSellersResponseLiveData.observe(viewLifecycleOwner){
            adapterForBestSellersRv.submitList(it)
            progressBarVisibility()
        }
        viewModel.newItemsResponseLiveData.observe(viewLifecycleOwner){
            adapterForNewItemRv.submitList(it)
            progressBarVisibility()
        }
        viewModel.discountsResponseLiveData.observe(viewLifecycleOwner){
            adapterForDiscountsRv.submitList(it)
            progressBarVisibility()
        }
    }

    private fun setImageScrolling(position: Int, imageSize: Int) {
        viewPagerTask = Runnable{
            binding.viewPager.currentItem = position
            if(position == imageSize - 1){
                setImageScrolling(0,imageSize)
            } else setImageScrolling(position + 1,imageSize)
        }
        handler.postDelayed(viewPagerTask, DELAY)
    }

    private fun progressBarVisibility(){
        binding.progressBar.apply {
            if(visibility == View.VISIBLE){
                visibility = View.GONE
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        handler.removeCallbacks(viewPagerTask)
        _binding = null
    }

}