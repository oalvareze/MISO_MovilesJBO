package com.example.vinilos.view.artistAlbum

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.CheckBox
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.vinilos.R
import com.example.vinilos.databinding.AlbumListItemBinding
import com.example.vinilos.databinding.ArtistAlbumItemBinding
import com.example.vinilos.model.Album
import com.example.vinilos.model.AlbumCreate
import com.example.vinilos.model.Collector
import com.example.vinilos.view.artistDetail.ArtistDetailAlbumAdapter

class ArtistAlbumListAdapter() :
    RecyclerView.Adapter<ArtistAlbumListAdapter.ArtistAlbumListViewHolder>() {
    var albums: List<AlbumCreate> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }
    var albumsSelected = mutableSetOf<Int>()
    class ArtistAlbumListViewHolder(val viewDataBinding: ArtistAlbumItemBinding) :
        RecyclerView.ViewHolder(viewDataBinding.root) {
        companion object {
            @LayoutRes
            val LAYOUT = R.layout.artist_album_item
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArtistAlbumListViewHolder {
        val withDataBinding: ArtistAlbumItemBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            ArtistAlbumListViewHolder.LAYOUT,
            parent,
            false
        )

        return ArtistAlbumListViewHolder(withDataBinding)
    }

    override fun onBindViewHolder(holder: ArtistAlbumListViewHolder, position: Int) {
        holder.viewDataBinding.also {
            it.album = albums[position]
        }
        var checkbox = holder.itemView.findViewById<CheckBox>(R.id.checkBox)
        checkbox.setOnCheckedChangeListener{ _, _ ->
            if(checkbox.isChecked){
              albumsSelected.add(albums[position].id)
            }else{
               albumsSelected.remove( albums[position].id)
            }
        }

    }

    override fun getItemCount(): Int {
        return albums.size
    }
}