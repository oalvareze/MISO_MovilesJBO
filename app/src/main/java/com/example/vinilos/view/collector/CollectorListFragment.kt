package com.example.vinilos.view.collector

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import android.widget.EditText
import android.widget.ProgressBar

import androidx.appcompat.app.AppCompatActivity

import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.vinilos.R
import com.example.vinilos.databinding.FragmentCollectorListBinding

import com.example.vinilos.model.Collector

import com.example.vinilos.repostories.CollectorRepository

import com.example.vinilos.viewmodel.CollectorListViewModel



class   CollectorListFragment : Fragment() {


    private var _binding: FragmentCollectorListBinding? = null
    private val binding get() = _binding!!
    private lateinit var recyclerView: RecyclerView
    private lateinit var viewModel: CollectorListViewModel
    private var viewModelAdapter: CollectorListAdapter? = null
    private lateinit var progressBar: ProgressBar




    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        (activity as AppCompatActivity?)!!.supportActionBar!!.title=getString(R.string.colleccionistas_appbar)
        _binding = FragmentCollectorListBinding.inflate(inflater, container, false)
        val view = binding.root
        val navController = findNavController()
        val direction = CollectorListFragmentDirections
        Log.d("paso por aqui", "one")
        viewModelAdapter = CollectorListAdapter(navController, direction)
        (activity as AppCompatActivity?)!!.supportActionBar!!.show()

        // Inflate the layout for this fragment
        return view
    }




    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        Log.d("onView", "llego")
        recyclerView = binding.collectorRecycler
        recyclerView.layoutManager = GridLayoutManager(context, 1)
        recyclerView.adapter = viewModelAdapter
        viewModel = ViewModelProvider(
            this,
            CollectorListViewModel.Factory(
                (activity as AppCompatActivity?)!!.application,
                CollectorRepository((activity as AppCompatActivity?)!!.application)
            )
        )[CollectorListViewModel::class.java]

        val inputSearch: EditText = view.findViewById(R.id.inputSearch)

        viewModel.loadCollector.observe(viewLifecycleOwner, Observer<List<Collector>> {
            it.apply {
                Log.d("Load_collectors", this.toString())
                viewModelAdapter!!.collectors = this


//                if (viewModel.genres.value == null) {
//                    viewModel.fillGenres()
//                }
            }
        })

//        viewModel.filterSearchCollectors.observe(viewLifecycleOwner, Observer {
//            it.apply {
//                Log.d("filterSearchCollectors", "start")
//                if (inputSearch.text != null) {
//                    Log.d("filterSearchCollectors", "different empty")
//                    viewModelAdapter!!.collectors = this
//                }
//
//            }
//        })

//        val inputSearch: EditText = view.findViewById(R.id.inputSearch)
//        val searchButton: Button = view.findViewById(R.id.searchCollectors)
//        Log.d("antes","s")
//        searchButton.setOnClickListener {
//            viewModel.prueba(inputSearch.text.toString())
//        }

        inputSearch.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
//                viewModel.prueba(p0.toString())
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
//                viewModel.prueba(p0.toString())
            }

            override fun afterTextChanged(p0: Editable?) {
                Log.d("entro", "validacion")
                viewModel.getFilterCollector(p0.toString())
            }
        })


        progressBar = view.findViewById<ProgressBar>(R.id.collectorProgressBar)
        viewModel.loading.observe(viewLifecycleOwner) {
            progressBar.visibility = if (it) View.VISIBLE else View.GONE
        }
        Log.d("onView", "final")

        super.onViewCreated(view, savedInstanceState)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}