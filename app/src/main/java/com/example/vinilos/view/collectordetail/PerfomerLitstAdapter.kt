package com.example.vinilos.view.collectordetail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.vinilos.R
import com.example.vinilos.databinding.PerfomerListItemBinding
import com.example.vinilos.model.Artist

class PerfomerListAdapter : RecyclerView.Adapter<PerfomerListAdapter.PeformerListViewHolder>()  {
    var artists :List<Artist> = emptyList()


    class PeformerListViewHolder(val viewDataBinding: PerfomerListItemBinding):
        RecyclerView.ViewHolder(viewDataBinding.root){
        companion object{
            @LayoutRes
            val LAYOUT= R.layout.perfomer_list_item
        }}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PeformerListViewHolder {
        val withDataBinding: PerfomerListItemBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            PerfomerListAdapter.PeformerListViewHolder.LAYOUT,
            parent,
            false
        )


        return PeformerListViewHolder(withDataBinding)
    }

    override fun onBindViewHolder(holder: PeformerListViewHolder, position: Int) {
        holder.viewDataBinding.also {
            it.performer = artists[position]
        }
        Glide.with(holder.itemView.context,).load(artists[position].image).into(holder.itemView.findViewById(R.id.performerImage))
    }

    override fun getItemCount(): Int {
        return artists.size
    }
}