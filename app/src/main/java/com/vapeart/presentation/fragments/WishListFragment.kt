package com.vapeart.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.vapeart.R
import com.vapeart.databinding.FragmentWishListBinding

class WishListFragment : Fragment() {


    private var _binding: FragmentWishListBinding? = null
    private val binding: FragmentWishListBinding
        get() = _binding ?: throw RuntimeException("WishListFragment binding is null")

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentWishListBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}