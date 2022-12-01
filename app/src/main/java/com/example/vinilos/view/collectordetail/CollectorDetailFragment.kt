package com.example.vinilos.view.collectordetail

import android.os.Bundle

import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs

import androidx.viewpager2.widget.ViewPager2
import com.example.vinilos.R
import com.example.vinilos.repostories.CollectorRepository

import com.example.vinilos.viewmodel.CollectorDetailViewModel
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator


class CollectorDetailFragment : Fragment() {
    // TODO: Rename and change types of parameters

    private  lateinit var viewModel:CollectorDetailViewModel
    private  val args by navArgs<CollectorDetailFragmentArgs>()
    private lateinit var tabLayout:TabLayout
    private lateinit var viewPager: ViewPager2

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel=ViewModelProvider(this,CollectorDetailViewModel.Factory((activity as AppCompatActivity?)!!.application,
            CollectorRepository((activity as AppCompatActivity?)!!.application),args.id.toString()
        ))[CollectorDetailViewModel::class.java]

        (activity as AppCompatActivity?)!!.supportActionBar!!.title=getString(R.string.detalle_appbar)
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_collector_detail, container, false)
    }

    fun updateUI(view: View,phone:String,email:String,name:String){
        var phoneTextView=view.findViewById<TextView>(R.id.collectorPhoneDetail)
        var collectorName=view.findViewById<TextView>(R.id.collectorDetailName)
        var collectorEmail=view.findViewById<TextView>(R.id.collectorEmailDetail)
        collectorName.text=name
        collectorEmail.text=email
        phoneTextView.text=phone
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel.collector.observe(viewLifecycleOwner){
            updateUI(view,it.telephone,it.email,it.name)
            tabLayout= view.findViewById(R.id.tabLayout2)
            viewPager=view.findViewById<ViewPager2>(R.id.collectorDetailVP)

                    viewPager.adapter= CollectorDetailPageAdapter((activity as AppCompatActivity?)!!,it.favoritePerformers!!,it.collectorAlbums!!)

            TabLayoutMediator(tabLayout,viewPager){
                    tab,index->
                tab.text=when(index){
                    0->{"Artistas favoritos"}
                    else ->"Albums"
                }
            }.attach()

        }
        super.onViewCreated(view, savedInstanceState)
    }

}