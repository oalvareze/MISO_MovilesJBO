package com.example.vinilos.view.albumdetail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.vinilos.R
import com.example.vinilos.databinding.AlbumCommentItemBinding

import com.example.vinilos.model.Comentario


class AlbumCommentAdapter :RecyclerView.Adapter<AlbumCommentAdapter.AlbumCommentViewHolder>(){
    var comments :List<Comentario> = emptyList()

    class AlbumCommentViewHolder(val viewDataBinding: AlbumCommentItemBinding):
        RecyclerView.ViewHolder(viewDataBinding.root){
        companion object{
            @LayoutRes
            val LAYOUT= R.layout.album_comment_item

        }}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):AlbumCommentViewHolder {
        val withDataBinding: AlbumCommentItemBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            AlbumCommentAdapter.AlbumCommentViewHolder.LAYOUT,
            parent,
            false
        )
        return AlbumCommentViewHolder(withDataBinding);
    }

    override fun onBindViewHolder(holder: AlbumCommentViewHolder, position: Int) {
        holder.viewDataBinding.also {
            it.comment = comments[position]
        }
    }

    override fun getItemCount(): Int {
        return comments.size
    }
}