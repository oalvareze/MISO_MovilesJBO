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
        genreSpinner=view.findViewById<Spinner>(R.id.genresSpinner)
        var adapter=ArrayAdapter<String>(requireContext(),android.R.layout.simple_spinner_item,
            listOf<String>("Generos","Rock","Classical", "Salsa", "Rock", "Folk"))
        viewModel.albumsFiltered.observe(viewLifecycleOwner,Observer<List<Album>>{

            it.apply {

                viewModelAdapter!!.albums=this

            }


        })


        viewModel.loading.observe(viewLifecycleOwner) {
            progressBar.visibility = if (it) View.VISIBLE else View.GONE
            val fab: View = view.findViewById(R.id.albumFloatingActionButton)
            fab.visibility=if(it)View.GONE  else View.VISIBLE


            genreSpinner.visibility=if(it)View.GONE  else View.VISIBLE
            Log.d("Entro",genreSpinner.visibility.toString())
            fab.setOnClickListener{
                findNavController().navigate(AlbumListFragmentDirections.actionAlbumListFragmentToCreateAlbumFragment())
            }
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


        }
        super.onViewCreated(view, savedInstanceState)
    }

    fun filterByGender(genre: String) {

        this.viewModel.getAlbumFiltered(genre)
    }



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