package com.example.vinilos.view.albumdetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.vinilos.R
import com.example.vinilos.databinding.FragmentTrackListBinding
import com.example.vinilos.model.Track




class TrackListFragment(val trackList: List<Track>,private val description:String) : Fragment() {
    private var trackListAdapter: TrackListAdapter? = null
    private  var _binding: FragmentTrackListBinding?=null
    private val binding get() = _binding!!
    private lateinit var recyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding= FragmentTrackListBinding.inflate(inflater,container,false)
        val view=binding.root
        trackListAdapter= TrackListAdapter()
        // Inflate the layout for this fragment
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

            trackListAdapter!!.tracks=trackList
            recyclerView = binding.trackListRV
            recyclerView.layoutManager = LinearLayoutManager(context)
            recyclerView.adapter = trackListAdapter
        val dividerItemDecoration = DividerItemDecoration(
            recyclerView.context,
            LinearLayoutManager.VERTICAL
        )
        view.findViewById<TextView>(R.id.albumDescription).text=description
        view.findViewById<TextView>(R.id.tracksMensaje).visibility= if(trackList.isEmpty()) View.VISIBLE else   View.GONE
        recyclerView.addItemDecoration(dividerItemDecoration)
            super.onViewCreated(view, savedInstanceState)

    }


}