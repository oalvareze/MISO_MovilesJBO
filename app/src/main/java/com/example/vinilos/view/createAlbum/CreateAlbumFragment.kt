package com.example.vinilos.view.createAlbum

import android.app.Application
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.vinilos.R
import com.example.vinilos.databinding.FragmentCreateAlbumBinding
import com.example.vinilos.viewmodel.AlbumListViewModel
import com.example.vinilos.viewmodel.CreateAlbumViewModel

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

/**
 * A simple [Fragment] subclass.
 * Use the [CreateAlbumFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class CreateAlbumFragment : Fragment() {
    // TODO: Rename and change types of parameters



    private lateinit var viewModel: CreateAlbumViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_album_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel= ViewModelProvider(this,CreateAlbumViewModel.Factory((activity as AppCompatActivity?)!!.application))[CreateAlbumViewModel::class.java]

        var button=view.findViewById<Button>(R.id.creatAlbumButton)
        button.setOnClickListener {
            viewModel.postAlbum()
        }
        super.onViewCreated(view, savedInstanceState)
    }
}