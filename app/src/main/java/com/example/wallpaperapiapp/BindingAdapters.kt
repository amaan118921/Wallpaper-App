package com.example.wallpaperapiapp

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.wallpaperapiapp.adapter.HomeAdapter
import com.example.wallpaperapiapp.model.Items


@BindingAdapter("listData")
fun bindData(recyclerView: RecyclerView, data:List<Items>?) {
    val adapter = recyclerView.adapter as HomeAdapter
    adapter.submitList(data)
}

@BindingAdapter("searchData")
fun bindSearch(recyclerView: RecyclerView, data:List<Items>?) {
    val adapter = recyclerView.adapter as HomeAdapter
    adapter.submitList(data)
}