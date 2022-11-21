package com.example.vinilos.view.collectordetail

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Button
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.vinilos.R
import com.example.vinilos.databinding.AlbumCollectorListItemBinding
import com.example.vinilos.model.AlbumCollector


class AlbumCollectorListAdapter(var navController: NavController,var navDirrections:CollectorDetailFragmentDirections.Companion) : RecyclerView.Adapter<AlbumCollectorListAdapter.AlbumCollectorListViewHolder>(){
    var albums :List<AlbumCollector> = emptyList()

    class AlbumCollectorListViewHolder(val viewDataBinding: AlbumCollectorListItemBinding):
    RecyclerView.ViewHolder(viewDataBinding.root){
    companion object{
        @LayoutRes
        val LAYOUT= R.layout.album_collector_list_item
    }}

    override fun getItemCount(): Int {
        return albums.size
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): AlbumCollectorListViewHolder {
        val withDataBinding: AlbumCollectorListItemBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            AlbumCollectorListAdapter.AlbumCollectorListViewHolder.LAYOUT,
            parent,
            false
        )


        return AlbumCollectorListViewHolder(withDataBinding)
    }

    override fun onBindViewHolder(holder: AlbumCollectorListViewHolder, position: Int) {
        holder.viewDataBinding.also {
            it.albumCollector= albums[position]
        }
        var button=holder.itemView.findViewById<Button>(R.id.detailButtonCollector)
        button.setOnClickListener(){
            navController.navigate(navDirrections.actionCollectorDetailFragmentToAlbumDetail(albums[position].album.id))
        }
        Glide.with(holder.itemView.context,).load(albums[position].album.cover).into(holder.itemView.findViewById(R.id.albumCoverCollector))
    }
}