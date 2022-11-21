package com.example.vinilos.view.albumdetail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.vinilos.R
import com.example.vinilos.databinding.AlbumTrackItemBinding
import com.example.vinilos.model.Track

class TrackListAdapter: RecyclerView.Adapter<TrackListAdapter.TrackListViewHolder>()  {
    var tracks :List<Track> = emptyList()

    class TrackListViewHolder(val viewDataBinding: AlbumTrackItemBinding):
        RecyclerView.ViewHolder(viewDataBinding.root){
        companion object{
            @LayoutRes
            val LAYOUT= R.layout.album_track_item

        }}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrackListViewHolder {
        val withDataBinding: AlbumTrackItemBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            TrackListAdapter.TrackListViewHolder.LAYOUT,
            parent,
            false
        )
        return TrackListViewHolder(withDataBinding);
    }

    override fun onBindViewHolder(holder: TrackListViewHolder, position: Int) {
        holder.viewDataBinding.also {
            it.track = tracks[position]
        }
    }

    override fun getItemCount(): Int {
       return tracks.size
    }
}