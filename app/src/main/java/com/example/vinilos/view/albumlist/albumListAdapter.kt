package com.example.vinilos.view.albumlist

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.vinilos.R
import com.example.vinilos.databinding.AlbumListItemBinding
import com.example.vinilos.model.Album

class AlbumListAdapter(private val navController: NavController): RecyclerView.Adapter<AlbumListAdapter.AlbumListViewHolder>() {



    var albums :List<Album> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumListViewHolder {
        val withDataBinding: AlbumListItemBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            AlbumListViewHolder.LAYOUT,
            parent,
            false)

        val holder=AlbumListViewHolder(withDataBinding)
        holder.itemView.setOnClickListener(View.OnClickListener() {
            navController.navigate(R.id.action_albumListFragment_to_albumDetail)
        })
        return holder
    }

    class AlbumListViewHolder(val viewDataBinding:AlbumListItemBinding):
        RecyclerView.ViewHolder(viewDataBinding.root){
    companion object{
        @LayoutRes
        val LAYOUT=R.layout.album_list_item

    }}
    override fun onBindViewHolder(holder: AlbumListViewHolder, position: Int) {
        holder.viewDataBinding.also {
            it.album = albums[position]
        }

        Glide.with(holder.itemView.context,).load(albums[position].cover).into(holder.itemView.findViewById(R.id.coverImage))
    }

    override fun getItemCount(): Int {
        return albums.size
    }


}