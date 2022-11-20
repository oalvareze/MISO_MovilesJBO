package com.example.vinilos.view.collectordetail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.vinilos.R
import com.example.vinilos.databinding.FragmentPerfomerListBinding
import com.example.vinilos.databinding.FragmentTrackListBinding
import com.example.vinilos.databinding.PerfomerListItemBinding
import com.example.vinilos.model.Artist
import com.example.vinilos.view.albumdetail.TrackListAdapter


class PerfomerList(var artists: List<Artist>) : Fragment() {

    private var perfomerListAdapter:PerfomerListAdapter?=null
    private  var _binding: FragmentPerfomerListBinding?=null
    private val binding get() = _binding!!
    private lateinit var recyclerView: RecyclerView
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding=FragmentPerfomerListBinding.inflate(inflater,container,false)
        val view=binding.root
        perfomerListAdapter= PerfomerListAdapter()
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        perfomerListAdapter!!.artists=artists
        recyclerView = binding.performListRV
        recyclerView.layoutManager = GridLayoutManager(context, 2)
        recyclerView.adapter = perfomerListAdapter
        super.onViewCreated(view, savedInstanceState)
    }


}