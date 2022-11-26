package com.example.vinilos.view.artistAlbum

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.vinilos.R
import com.example.vinilos.view.artistDetail.ArtistDetailFragment

import com.example.vinilos.view.collectordetail.CollectorDetailFragmentArgs
import com.example.vinilos.viewmodel.ArtistAlbumViewModel
import com.example.vinilos.viewmodel.CollectorListViewModel


class ArtistAlbumFragment : Fragment() {


    private lateinit var viewModel: ArtistAlbumViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_artist_album, container, false)


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        var button=view.findViewById<Button>(R.id.albumArtistButton)

        super.onViewCreated(view, savedInstanceState)
    }

}