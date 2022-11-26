package com.example.vinilos.view.artist

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.vinilos.R
import com.example.vinilos.databinding.FragmentAlbumListBinding
import com.example.vinilos.databinding.FragmentArtistListBinding
import com.example.vinilos.model.Album
import com.example.vinilos.model.Artist
import com.example.vinilos.repostories.AlbumRepository
import com.example.vinilos.repostories.ArtistRepository
import com.example.vinilos.view.albumlist.AlbumListAdapter
import com.example.vinilos.view.albumlist.AlbumListFragmentDirections
import com.example.vinilos.viewmodel.AlbumListViewModel
import com.example.vinilos.viewmodel.ArtistListViewModel

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ArtistListFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ArtistListFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private var _binding: FragmentArtistListBinding? = null
    private val binding get() = _binding!!
    private lateinit var recyclerView: RecyclerView
    private lateinit var viewModel: ArtistListViewModel
    private var viewModelAdapter: ArtistListAdapter? = null
    private lateinit var progressBar: ProgressBar
    private lateinit var genreSpinner: Spinner

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        (activity as AppCompatActivity?)!!.supportActionBar!!.title = "Vinilos"
        _binding = FragmentArtistListBinding.inflate(inflater, container, false)
        val view = binding.root
        val navController = findNavController()
        val direction = ArtistListFragmentDirections
        viewModelAdapter = ArtistListAdapter(navController, direction)
        (activity as AppCompatActivity?)!!.supportActionBar!!.show()

        // Inflate the layout for this fragment
        return view;
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment ArtistListFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ArtistListFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        recyclerView = binding.artistListRV
        recyclerView.layoutManager = GridLayoutManager(context, 2)
        recyclerView.adapter = viewModelAdapter
        viewModel = ViewModelProvider(
            this, ArtistListViewModel.Factory(
                (activity as AppCompatActivity?)!!.application,
                ArtistRepository((activity as AppCompatActivity?)!!.application)
            )
        ).get(ArtistListViewModel::class.java)
        progressBar = view.findViewById<ProgressBar>(R.id.progressBarArtist)
        viewModel.loading.observe(viewLifecycleOwner,Observer<Boolean>{
            it.apply {
                progressBar.visibility= if (it) View.VISIBLE else View.GONE
            }
        })
        viewModel.loadArtist.observe(viewLifecycleOwner, Observer<List<Artist>> {
            it.apply {
                viewModelAdapter!!.artists = this
            }
        })

        super.onViewCreated(view, savedInstanceState)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}