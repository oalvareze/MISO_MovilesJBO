package com.example.vinilos.view.collectordetail
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.vinilos.model.AlbumCollector
import com.example.vinilos.model.Artist

class CollectorDetailPageAdapter(fragmentActivity:FragmentActivity,private var artists:List<Artist>,private  var albums:List<AlbumCollector>) : FragmentStateAdapter(fragmentActivity){
    override fun getItemCount(): Int {
        return  2
    }

    override fun createFragment(position: Int): Fragment {

        return when(position){
            0-> PerfomerList(artists)
            else -> AlbumCollectorFragment(albums)


        }
    }
}