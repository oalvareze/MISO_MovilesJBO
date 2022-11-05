package com.example.vinilos.view.albumdetail


import android.util.Log
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.vinilos.model.Comentario
import com.example.vinilos.model.Track

class AlbumDetailPageAdapter(fragmentActivity:FragmentActivity, private var tracks: List<Track>,private var comments: List<Comentario>):FragmentStateAdapter(fragmentActivity) {
    override fun getItemCount(): Int {
        return  2
    }

    override fun createFragment(position: Int): Fragment {

        return when(position){
            0-> TrackListFragment(tracks)
            else ->AlbumCommentsFragment(comments)


        }
    }
}