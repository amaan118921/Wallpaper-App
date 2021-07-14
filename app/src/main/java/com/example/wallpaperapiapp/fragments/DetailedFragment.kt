package com.example.wallpaperapiapp.fragments

import android.app.DownloadManager
import android.app.WallpaperManager
import android.content.Context
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.net.toUri
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.wallpaperapiapp.R
import com.example.wallpaperapiapp.databinding.FragmentDetailedBinding
import com.example.wallpaperapiapp.viewModel.HomeModel
import com.squareup.picasso.Picasso
import java.io.File
import java.io.IOException


class DetailedFragment : Fragment() {

    private lateinit var binding: FragmentDetailedBinding
    private val model: HomeModel by activityViewModels()

    private lateinit var uri: Uri
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val fragmentBinding = FragmentDetailedBinding.inflate(inflater)
        binding = fragmentBinding
        return fragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        Picasso.get().load(model.getUrl()).placeholder(R.drawable.loading_animation).into(binding.detailImgView)
        binding.downloads.text = model.getDownloads()
        binding.views.text = model.getViews()
        binding.likes.text = model.getLikes()

        fun downloadImage(imageUrl: String) {
            try {
                var downloadManager: DownloadManager? = null
                downloadManager = context?.getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
                uri = imageUrl.toUri()

                val request: DownloadManager.Request = DownloadManager.Request(uri)
                request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_MOBILE).setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI)
                    .setAllowedOverRoaming(false)
                    .setTitle("My Backgrounds")
                    .setMimeType("image/jpeg")
                    .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
                    .setDestinationInExternalPublicDir(Environment.DIRECTORY_PICTURES, File.separator+"myBackgrounds.jpg")
                downloadManager.enqueue(request)


            }
            catch (e: Exception) {

            }
        }
        fun setImage(imgUrl: String) {

//            val uiHandler = Handler(Looper.getMainLooper())
//            uiHandler.post(Runnable {
//
//            })

            val thread = Thread {
                val result: Bitmap = Picasso.get().load(imgUrl).get()



                val wallpaperManager = WallpaperManager.getInstance(context)
                try {
                    wallpaperManager.setBitmap(result)
                } catch (ex: IOException) {
                    ex.printStackTrace()
                }
            }
            thread.start()

        }

        binding.download.setOnClickListener {
            downloadImage(model.getUrl())
            Toast.makeText(this.requireContext(), "Download Started", Toast.LENGTH_SHORT).show()

        }
        binding.set.setOnClickListener {
            binding.pb.visibility = View.VISIBLE
            setImage(model.getUrl())
            Handler(Looper.getMainLooper()).postDelayed({
                binding.pb.visibility = View.INVISIBLE
                Toast.makeText(this.requireContext(), "Wallpaper Applied", Toast.LENGTH_SHORT).show()
            }, 2000)

        }

        binding.home.setOnClickListener {
            findNavController().navigate(R.id.action_detailedFragment_to_homeFragment)
        }
        binding.back.setOnClickListener {
            findNavController().navigate(R.id.action_detailedFragment_to_homeFragment)
        }




    }

}