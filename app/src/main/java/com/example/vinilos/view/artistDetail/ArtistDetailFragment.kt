package com.example.vinilos.view.artistDetail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.vinilos.R
import com.example.vinilos.databinding.FragmentArtistDetailBinding
import com.example.vinilos.databinding.FragmentCollectorListBinding
import com.example.vinilos.model.AlbumCreate
import com.example.vinilos.model.Artist
import com.example.vinilos.model.ArtistDetail
import com.example.vinilos.repostories.AlbumRepository
import com.example.vinilos.repostories.ArtistRepository
import com.example.vinilos.view.albumlist.AlbumListAdapter

import com.example.vinilos.view.artistAlbum.ArtistAlbumListAdapter
import com.example.vinilos.viewmodel.AlbumListViewModel
import com.example.vinilos.viewmodel.ArtistDetailViewModel


class ArtistDetailFragment : Fragment() {

    private  val args by navArgs<ArtistDetailFragmentArgs>()
    private  lateinit var viewModel:ArtistDetailViewModel
    private var _binding: FragmentArtistDetailBinding? = null
    private val binding get() = _binding!!
    private lateinit var recyclerView: RecyclerView
    private var viewModelAdapter: ArtistDetailAlbumAdapter? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel=ViewModelProvider(this,ArtistDetailViewModel.Factory(((activity as AppCompatActivity?)!!.application),ArtistRepository(((activity as AppCompatActivity?)!!.application)),args.id))[ArtistDetailViewModel::class.java]
       _binding= FragmentArtistDetailBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment
        viewModelAdapter= ArtistDetailAlbumAdapter()
        (activity as AppCompatActivity?)!!.supportActionBar!!.title=getString(R.string.detalle_appbar)
        return binding.root

    }
    fun updateUI(view:View,artistDetail: ArtistDetail){
        view.findViewById<TextView>(R.id.artistDetailName).text=artistDetail.name
        view.findViewById<TextView>(R.id.artistDetailDescription).text=artistDetail.description
        Glide.with(view.context,).load(artistDetail.image).into(view.findViewById<ImageView>(R.id.artistDetailImage))
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        recyclerView = binding.artistAlbumRV
        recyclerView.layoutManager = GridLayoutManager(context, 2)
        recyclerView.adapter = viewModelAdapter


        viewModel.artist.observe(viewLifecycleOwner, Observer<ArtistDetail>{

            it.apply {
                updateUI(view,it)
                viewModelAdapter!!.albums=it.albums

            }


        })
        var button=view.findViewById<Button>(R.id.albumArtistButton)
        button.setOnClickListener {
            findNavController().navigate(ArtistDetailFragmentDirections.actionArtistDetailFragmentToArtistAlbumFragment(args.id))
        }
        super.onViewCreated(view, savedInstanceState)
    }

}


