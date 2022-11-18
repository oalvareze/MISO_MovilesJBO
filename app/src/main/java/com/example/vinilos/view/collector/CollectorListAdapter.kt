package com.example.vinilos.view.collector

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.recyclerview.widget.RecyclerView
import com.example.vinilos.R
import com.example.vinilos.databinding.CollectorListItemBinding
import com.example.vinilos.model.Collector
import com.example.vinilos.view.albumlist.AlbumListFragmentDirections

class CollectorListAdapter(private val navController: NavController,
                           val navDirections: AlbumListFragmentDirections.Companion): RecyclerView.Adapter<CollectorListAdapter.CollectionListViewHolder>()  {

    var collectors :List<Collector> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CollectionListViewHolder {
        val withDataBinding: CollectorListItemBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            CollectionListViewHolder.LAYOUT,
            parent,
            false)
        Log.d("createView", "llego")

        val holder=CollectionListViewHolder(withDataBinding)

//        holder.itemView.setOnClickListener(View.OnClickListener() {
//                this.navController.navigate(navDirections.actionAlbumListFragmentToAlbumDetail(albums[holder.adapterPosition].albumId))
//
//            })
        return holder
    }

    class CollectionListViewHolder(val viewDataBinding:CollectorListItemBinding):
        RecyclerView.ViewHolder(viewDataBinding.root){
    companion object{
        @LayoutRes
        val LAYOUT=R.layout.collector_list_item
    }}

    override fun onBindViewHolder(holder: CollectionListViewHolder, position: Int) {
        Log.d("onBind", "llego")
        holder.viewDataBinding.also {
            it.collector = collectors[position]
        }


//         holder.itemView.findViewById<TextView>(R.id.collectorName).setText("hhhh")
        //texto.setText("hola")
        //        holder.itemView.findViewById<TextView>(R.id.collectorName).text = "prueba"
//        holder.itemView.findViewById(R.id.collectorName)
    }

    override fun getItemCount(): Int {
        Log.d("cantidad","cantidad"+collectors.size)
        return collectors.size
    }

}