package com.example.wallpaperapiapp.viewModel

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.wallpaperapiapp.fragments.HomeFragment
import com.example.wallpaperapiapp.fragments.SearchFragment
import com.example.wallpaperapiapp.model.Items
import com.example.wallpaperapiapp.network.API
import com.example.wallpaperapiapp.network.Rest
import com.example.wallpaperapiapp.network.RestAPI
import kotlinx.coroutines.launch
import java.sql.Struct

class HomeModel: ViewModel() {

    private lateinit var likes: String
    private lateinit var downloads: String
    private lateinit var views: String
    private lateinit var url: String
    private lateinit var query: String

    private val list = mutableListOf("backgrounds", "fashion", "nature", "science", "education", "feelings", "health", "people",
    "religion", "places", "animals", "industry", "computer", "food", "sports", "transportation", "travel")



    private val _search = MutableLiveData<List<Items>>()
    val search: LiveData<List<Items>> = _search

    private val _data = MutableLiveData<List<Items>>()
    val data: LiveData<List<Items>> = _data

    init {
        getData()
    }


    private fun getData() {
        viewModelScope.launch {
            try {
                _data.value = API.retrofitService.getImages(list.random(), "photo").hits
            }
               catch (e: Exception) {
                   HomeFragment.binding.error.visibility = View.VISIBLE
                   HomeFragment.binding.pB.visibility = View.INVISIBLE
               }
        }
    }

    fun setSearch(search: String) {
         query = search
    }

     fun getQuery() {
        viewModelScope.launch {
            try {
                _search.value = Rest.retrofitService.getSearch(query, "photo").hits
            }
            catch (e: Exception) {
                SearchFragment.binding.internetError.visibility = View.VISIBLE
                SearchFragment.binding.pb.visibility = View.INVISIBLE
            }

        }
    }

    fun setUrl(url: String) {
        this.url = url
    }
    fun getUrl(): String {
        return url
    }

    fun setLikes(likes: String) {
        this.likes = likes
    }
    fun getLikes(): String {
        return likes
    }
    fun setDownloads(downloads: String) {
        this.downloads = downloads
    }
    fun getDownloads(): String {
        return downloads
    }
    fun setViews(views: String) {
        this.views = views
    }
    fun getViews(): String {
        return views
    }




}