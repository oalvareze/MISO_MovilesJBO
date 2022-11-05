package com.example.vinilos.view.albumdetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.vinilos.databinding.FragmentTrackListBinding
import com.example.vinilos.model.Track


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class TrackListFragment(val trackList: List<Track>) : Fragment() {
    private var trackListAdapter: TrackListAdapter? = null
    private  var _binding: FragmentTrackListBinding?=null
    private val binding get() = _binding!!
    private lateinit var recyclerView: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

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
        recyclerView.addItemDecoration(dividerItemDecoration)
            super.onViewCreated(view, savedInstanceState)

    }


}