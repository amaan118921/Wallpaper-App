package com.example.wallpaperapiapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.wallpaperapiapp.R
import com.example.wallpaperapiapp.databinding.FragmentHomeBinding
import com.example.wallpaperapiapp.databinding.ItemViewBinding
import com.example.wallpaperapiapp.model.Info
import com.example.wallpaperapiapp.model.Items
import com.example.wallpaperapiapp.viewModel.HomeModel
import com.squareup.picasso.Picasso

class HomeAdapter(private val fragment: Fragment, private val Model: HomeModel): ListAdapter<Items, HomeAdapter.HomeViewHolder>(DiffCallback) {

    class HomeViewHolder(var binding: ItemViewBinding): RecyclerView.ViewHolder(binding.root) {
        val imgView: ImageView = binding.itemView
        fun bind() {
            binding.executePendingBindings()
        }
    }

    companion object DiffCallback: DiffUtil.ItemCallback<Items>() {
        override fun areItemsTheSame(oldItem: Items, newItem: Items): Boolean {
            return oldItem.user==newItem.user
        }

        override fun areContentsTheSame(oldItem: Items, newItem: Items): Boolean {
            return oldItem.id==newItem.id
        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        val adapterLayout = ItemViewBinding.inflate(LayoutInflater.from(parent.context))
        return HomeViewHolder(adapterLayout)
    }

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        val data = getItem(position)
        Picasso.get().load(data.webUrl).placeholder(R.drawable.loading_animation).into(holder.imgView)
        holder.itemView.setOnClickListener {
            Model.setLikes(data.likes)
            Model.setDownloads(data.downloads)
            Model.setViews(data.views)
            Model.setUrl(data.largeImageURL)
            try {
                fragment.findNavController().navigate(R.id.action_homeFragment_to_detailedFragment)
            }
            catch (e:Exception) {

            }
            try {
                fragment.findNavController().navigate(R.id.action_searchFragment_to_detailedFragment)
            }
            catch (e:Exception) {

            }
        }
        holder.bind()
    }


}