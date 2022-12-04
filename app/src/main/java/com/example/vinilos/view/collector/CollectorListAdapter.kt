package com.example.vinilos.view.collector

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.recyclerview.widget.RecyclerView
import com.example.vinilos.R
import com.example.vinilos.databinding.CollectorListItemBinding
import com.example.vinilos.model.Collector


class CollectorListAdapter(
    private val navController: NavController,
    val navDirections: CollectorListFragmentDirections.Companion
) : RecyclerView.Adapter<CollectorListAdapter.CollectionListViewHolder>() {

    var collectors: List<Collector> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CollectionListViewHolder {
        val withDataBinding: CollectorListItemBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            CollectionListViewHolder.LAYOUT,
            parent,
            false
        )
        val holder = CollectionListViewHolder(withDataBinding)

        holder.itemView.setOnClickListener(View.OnClickListener() {
            this.navController.navigate(
                navDirections.actionCollectorListFragmentToCollectorDetailFragment(
                    collectors[holder.adapterPosition].id
                )
            )
        })
        return holder
    }

    class CollectionListViewHolder(val viewDataBinding: CollectorListItemBinding) :
        RecyclerView.ViewHolder(viewDataBinding.root) {
        companion object {
            @LayoutRes
            val LAYOUT = R.layout.collector_list_item
        }
    }

    override fun onBindViewHolder(holder: CollectionListViewHolder, position: Int) {
        holder.viewDataBinding.also {
            it.collector = collectors[position]
        }
    }

    override fun getItemCount(): Int {
        return collectors.size
    }

}