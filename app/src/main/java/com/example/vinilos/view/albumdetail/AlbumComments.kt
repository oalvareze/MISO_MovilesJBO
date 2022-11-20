package com.example.vinilos.view.albumdetail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.vinilos.R
import com.example.vinilos.databinding.FragmentAlbumCommentsBinding

import com.example.vinilos.model.Comentario



class AlbumCommentsFragment(private val comments:List<Comentario>) : Fragment() {
    private var commentAdapter: AlbumCommentAdapter? = null
    private  var _binding: FragmentAlbumCommentsBinding?=null
    private val binding get() = _binding!!
    private lateinit var recyclerView: RecyclerView



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding= FragmentAlbumCommentsBinding.inflate(inflater,container,false)
        val view=binding.root
        commentAdapter= AlbumCommentAdapter()
        view.findViewById<TextView>(R.id.mensajeComentarios).visibility= if(comments.isEmpty()) View.VISIBLE else   View.GONE
        // Inflate the layout for this fragment
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        commentAdapter!!.comments=comments
        recyclerView = binding.albumCommentRV
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = commentAdapter
        val dividerItemDecoration = DividerItemDecoration(
            recyclerView.context,
            LinearLayoutManager.VERTICAL
        )
        recyclerView.addItemDecoration(dividerItemDecoration)
        super.onViewCreated(view, savedInstanceState)
    }
}