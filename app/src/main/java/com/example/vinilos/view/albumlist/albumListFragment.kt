package com.example.vinilos.view.albumlist

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
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.vinilos.R
import com.example.vinilos.databinding.FragmentAlbumListBinding
import com.example.vinilos.model.Album
import com.example.vinilos.repostories.AlbumRepository
import com.example.vinilos.viewmodel.AlbumListViewModel

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [AlbumListFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class AlbumListFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private  var _binding:FragmentAlbumListBinding?=null
    private val binding get() = _binding!!
    private lateinit var recyclerView: RecyclerView
    private lateinit var viewModel: AlbumListViewModel
    private var viewModelAdapter: AlbumListAdapter? = null
    private lateinit var progressBar:ProgressBar
    private lateinit var genreSpinner:Spinner
    override fun onCreate(savedInstanceState: Bundle?) {
        (activity as AppCompatActivity?)!!.supportActionBar!!.title="Vinilos"
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        (activity as AppCompatActivity?)!!.supportActionBar!!.title="Vinilos"
        _binding=FragmentAlbumListBinding.inflate(inflater, container,false)
        val view=binding.root
        val navController=findNavController()
        val direction=AlbumListFragmentDirections
        viewModelAdapter= AlbumListAdapter(navController,direction)
        (activity as AppCompatActivity?)!!.supportActionBar!!.show()

        goCollectors(view, navController, direction)

        // Inflate the layout for this fragment
        return view;
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        recyclerView = binding.albumListRV
        recyclerView.layoutManager = GridLayoutManager(context, 2)
        recyclerView.adapter = viewModelAdapter
        viewModel = ViewModelProvider(this, AlbumListViewModel.Factory((activity as AppCompatActivity?)!!.application,AlbumRepository((activity as AppCompatActivity?)!!.application)   )).get(AlbumListViewModel::class.java)
        progressBar=view.findViewById<ProgressBar>(R.id.progressBar)

        viewModel.albumsFiltered.observe(viewLifecycleOwner,Observer<List<Album>>{

            it.apply {

                viewModelAdapter!!.albums=this

                if(viewModel.genres.value==null){
                    viewModel.fillGenres()
                }
            }


        })
        viewModel.genres.observe(viewLifecycleOwner,Observer<List<String>>{
            Log.d("Entro","Aqui")
            genreSpinner=view.findViewById<Spinner>(R.id.genresSpinner)
            var adapter=ArrayAdapter<String>(requireContext(),android.R.layout.simple_spinner_item,it.toList())
            genreSpinner.adapter=adapter
            genreSpinner.onItemSelectedListener=object:AdapterView.OnItemSelectedListener,
                AdapterView.OnItemClickListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    filterByGender(genreSpinner.selectedItem.toString())

                }

                override fun onNothingSelected(parent: AdapterView<*>?) {

                }

                override fun onItemClick(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    TODO("Not yet implemented")
                }

            }

        })
        viewModel.loading.observe(viewLifecycleOwner) {
            progressBar.visibility = if (it) View.VISIBLE else View.GONE
            val fab: View = view.findViewById(R.id.albumFloatingActionButton)
            fab.visibility=if(it)View.GONE  else View.VISIBLE

            fab.setOnClickListener{
                findNavController().navigate(AlbumListFragmentDirections.actionAlbumListFragmentToCreateAlbumFragment())
            }
        }
        super.onViewCreated(view, savedInstanceState)
    }

    fun filterByGender(genre: String) {
        this.viewModel.getAlbumFiltered(genre)
    }

//    fun createAlbum(view: View) {
//        Log.d("entro", "view")
//        val postButton: Button = view.findViewById(R.id.create_album)
//        postButton.setOnClickListener { this.viewModel.createAlbum() }
//    }

    fun goCollectors(view: View, navController: NavController, navDirections: AlbumListFragmentDirections.Companion) {
        Log.d("entro", "ir a collectors")
        val btnGoCollectors: Button = view.findViewById(R.id.btnColeccionista)
        btnGoCollectors.setOnClickListener {
            navController.navigate(navDirections.actionAlbumListFragmentToCollectorListFragment())
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}