package com.example.vinilos.view.artist


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.core.net.toUri
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.example.vinilos.R
import com.example.vinilos.databinding.AlbumListItemBinding
import com.example.vinilos.databinding.ArtistListItemBinding
import com.example.vinilos.model.Artist
import com.example.vinilos.view.albumlist.AlbumListFragmentDirections

class ArtistListAdapter(
    private val navController: NavController,
    val navDirections: AlbumListFragmentDirections.Companion
) : RecyclerView.Adapter<ArtistListAdapter.ArtistListViewHolder>() {


    var artists: List<Artist> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArtistListViewHolder {
        val withDataBinding: ArtistListItemBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            ArtistListViewHolder.LAYOUT,
            parent,
            false
        )
        val holder = ArtistListViewHolder(withDataBinding)

//        holder.itemView.setOnClickListener(View.OnClickListener() {
//                this.navController.navigate(navDirections.actionAlbumListFragmentToAlbumDetail(albums[holder.adapterPosition].albumId))
//
//            })
        return holder
    }

    class ArtistListViewHolder(val viewDataBinding: ArtistListItemBinding) :
        RecyclerView.ViewHolder(viewDataBinding.root) {
        companion object {
            @LayoutRes
            val LAYOUT = R.layout.artist_list_item

        }
    }

    override fun onBindViewHolder(holder: ArtistListViewHolder, position: Int) {
        holder.viewDataBinding.also {
            it.artist = artists[position]
        }

        Glide.with(holder.itemView.context)
            .load(artists[position].image.toUri().buildUpon().scheme("https").build()).apply(
            RequestOptions()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
        ).into(holder.itemView.findViewById(R.id.coverImage))
    }

    override fun getItemCount(): Int {
        return artists.size
    }

}