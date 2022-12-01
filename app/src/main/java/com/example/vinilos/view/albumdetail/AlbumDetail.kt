package com.example.vinilos.view.albumdetail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.example.vinilos.R
import com.example.vinilos.model.Album
import com.example.vinilos.repostories.AlbumRepository
import com.example.vinilos.viewmodel.AlbumDetailViewModel
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator


class AlbumDetail : Fragment() {
    private  val args by navArgs<AlbumDetailArgs>()
    private lateinit var viewModel: AlbumDetailViewModel
    private lateinit var tabLayout:TabLayout
    private  lateinit var viewPager:ViewPager2

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        (activity as AppCompatActivity?)!!.supportActionBar!!.title=getString(R.string.detalle_appbar)
        return inflater.inflate(R.layout.fragment_album_detail, container, false)
    }
    fun updateUi(view:View,album: Album){


        view.findViewById<TextView>(R.id.albumDetailTitle).text = album.name
        view.findViewById<TextView>(R.id.albumDetailArtist).text=album.artist
        view.findViewById<TextView>(R.id.albumDate).text=album.releaseDate
        Glide.with(view.context,).load(album.cover).centerCrop().into(view.findViewById<ImageView>(R.id.albumDetailCover))
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {


        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this, AlbumDetailViewModel.Factory((activity as AppCompatActivity?)!!.application,args.albumId, AlbumRepository((activity as AppCompatActivity?)!!.application)))[AlbumDetailViewModel::class.java]



        viewModel.album.observe(viewLifecycleOwner, Observer<Album> {

            it.apply {
                updateUi(view,it)
                tabLayout= view.findViewById(R.id.tabLayout)
                viewPager=view.findViewById(R.id.pageViewer)

                viewPager.adapter= AlbumDetailPageAdapter((activity as AppCompatActivity?)!!,it.tracks,it.comentarios,it.description)

                TabLayoutMediator(tabLayout,viewPager){
                        tab,index->
                    tab.text=when(index){
                        0->{"Tracks"}
                        else ->"Comentarios"
                    }
                }.attach()                }

        })


    }
    companion object {

        @JvmStatic
        fun newInstance() =
            AlbumDetail().apply {
                arguments = Bundle().apply {

                }
            }
    }
}