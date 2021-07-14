package com.example.wallpaperapiapp.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.wallpaperapiapp.R
import com.example.wallpaperapiapp.adapter.HomeAdapter
import com.example.wallpaperapiapp.databinding.FragmentHomeBinding

import com.example.wallpaperapiapp.viewModel.HomeModel


class HomeFragment : Fragment() {

    private lateinit var search: String

    private val viewModel: HomeModel by activityViewModels()

    companion object Home {
        @SuppressLint("StaticFieldLeak")
         lateinit var binding: FragmentHomeBinding
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val fragmentBinding = FragmentHomeBinding.inflate(inflater)
        binding = fragmentBinding

        return fragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        binding.lifecycleOwner=viewLifecycleOwner
        binding.model = viewModel
        binding.homeRecyclerView.adapter = HomeAdapter(this.requireParentFragment(), viewModel)

        binding.searchButton.setOnClickListener {
            search = binding.edtText.text.toString()
            if(search.isNotEmpty()) {
                viewModel.setSearch(search)
                viewModel.getQuery()
                findNavController().navigate(R.id.action_homeFragment_to_searchFragment)
            }
            else {

            }

        }


        




    }
}