package com.example.vinilos.view.artistDetail

import com.example.vinilos.view.albumlist.AlbumListAdapter
import com.example.vinilos.view.albumlist.AlbumListFragmentDirections



import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.core.net.toUri
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController


import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.example.vinilos.R
import com.example.vinilos.databinding.AlbumListItemBinding
import com.example.vinilos.model.Album
import com.example.vinilos.model.AlbumCreate

class ArtistDetailAlbumAdapter(): RecyclerView.Adapter<ArtistDetailAlbumAdapter.AlbumListViewHolder>()  {



    var albums :List<AlbumCreate> = emptyList()
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

        return AlbumListViewHolder(withDataBinding)
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

        Glide.with(holder.itemView.context,).load(albums[position].cover.toUri().buildUpon().scheme("https").build()).apply(
            RequestOptions()
                .diskCacheStrategy(DiskCacheStrategy.ALL)).into(holder.itemView.findViewById(R.id.coverImage))
    }

    override fun getItemCount(): Int {
        return albums.size
    }}


