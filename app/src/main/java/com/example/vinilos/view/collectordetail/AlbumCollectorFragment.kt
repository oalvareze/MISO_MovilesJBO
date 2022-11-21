package com.example.vinilos.view.collectordetail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.example.vinilos.databinding.FragmentAlbumCollectorBinding

import com.example.vinilos.model.AlbumCollector

class AlbumCollectorFragment(val albums: List<AlbumCollector>) : Fragment() {

    private var albumCollectorListAdapter:AlbumCollectorListAdapter?=null
    private  var _binding: FragmentAlbumCollectorBinding?=null
    private val binding get() = _binding!!
    private lateinit var recyclerView: RecyclerView
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding=FragmentAlbumCollectorBinding.inflate(inflater,container,false)
        val view=binding.root
        albumCollectorListAdapter= AlbumCollectorListAdapter(findNavController(),CollectorDetailFragmentDirections)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        albumCollectorListAdapter!!.albums=albums
        recyclerView=binding.albumCollectorRV
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = albumCollectorListAdapter
        super.onViewCreated(view, savedInstanceState)
    }


}