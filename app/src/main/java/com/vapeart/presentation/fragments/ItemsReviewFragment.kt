package com.vapeart.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.vapeart.R
import com.vapeart.databinding.FragmentItemsReviewBinding

class ItemsReviewFragment : Fragment() {

    private var _binding: FragmentItemsReviewBinding? = null
    private val binding: FragmentItemsReviewBinding
        get() = _binding ?: throw RuntimeException("ItemsReviewFragment binding is null")

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return binding.root
    }

}