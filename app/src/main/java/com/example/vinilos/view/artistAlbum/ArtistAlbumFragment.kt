package com.example.vinilos.view.artistAlbum

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.vinilos.R
import com.example.vinilos.databinding.FragmentArtistAlbumBinding
import com.example.vinilos.databinding.FragmentArtistDetailBinding
import com.example.vinilos.view.artistDetail.ArtistDetailFragment

import com.example.vinilos.view.collectordetail.CollectorDetailFragmentArgs
import com.example.vinilos.viewmodel.ArtistAlbumViewModel
import com.example.vinilos.viewmodel.ArtistDetailViewModel
import com.example.vinilos.viewmodel.CollectorListViewModel


class ArtistAlbumFragment : Fragment() {

    private lateinit var viewModel: ArtistAlbumViewModel
    private  var albumArtistAdapter:ArtistAlbumListAdapter?=null

    private var _binding: FragmentArtistAlbumBinding? = null
    private val binding get() = _binding!!
    private lateinit var recyclerView: RecyclerView
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
    viewModel=ViewModelProvider(this,ArtistAlbumViewModel.Factory(((activity as AppCompatActivity?)!!.application)))[ArtistAlbumViewModel::class.java]
        _binding=FragmentArtistAlbumBinding.inflate(inflater,container,false)

        albumArtistAdapter= ArtistAlbumListAdapter()
        return  binding.root


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel.album.observe(viewLifecycleOwner){
            it.apply {
                Log.d("Entro2",it.toString())
                albumArtistAdapter!!.albums=it
                var button=view.findViewById<Button>(R.id.artistAlbumButton)
                button.setOnClickListener {
                    Log.d("Entro",albumArtistAdapter!!.albumsSelected.toString())
                }
            }
        }
        recyclerView=binding.artistAlbumRV
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = albumArtistAdapter

        super.onViewCreated(view, savedInstanceState)
    }

}