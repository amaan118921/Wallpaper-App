package com.example.wallpaperapiapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.example.wallpaperapiapp.R
import com.example.wallpaperapiapp.adapter.HomeAdapter
import com.example.wallpaperapiapp.databinding.FragmentSearchBinding
import com.example.wallpaperapiapp.viewModel.HomeModel

class SearchFragment : Fragment() {

    private val model: HomeModel by activityViewModels()

    companion object Search {
         lateinit var binding: FragmentSearchBinding
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val fragmentBinding = FragmentSearchBinding.inflate(inflater)
        binding = fragmentBinding
        return fragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.model = model
        binding.lifecycleOwner = viewLifecycleOwner
        binding.seachRecyclerView.adapter = HomeAdapter(this.requireParentFragment(), model)
    }

}