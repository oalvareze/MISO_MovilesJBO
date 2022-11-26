package com.example.vinilos.view.artistAlbum

import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView
import com.example.vinilos.R
import com.example.vinilos.databinding.ArtistAlbumItemBinding
import com.example.vinilos.model.AlbumCreate
import com.example.vinilos.model.Collector

class ArtistAlbumListAdapter(): RecyclerView.Adapter<ArtistAlbumListAdapter.ArtistAlbumListViewHolder>() {
    var albums: List<AlbumCreate> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }
    class ArtistAlbumListViewHolder(val viewDataBinding: ArtistAlbumItemBinding) :
        RecyclerView.ViewHolder(viewDataBinding.root) {
        companion object {
            @LayoutRes
            val LAYOUT = R.layout.artist_album_item
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArtistAlbumListViewHolder {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: ArtistAlbumListViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }
}