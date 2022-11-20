package com.example.vinilos.view.createAlbum

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.vinilos.R

import com.example.vinilos.repostories.AlbumRepository

import com.example.vinilos.viewmodel.CreateAlbumViewModel
import java.text.SimpleDateFormat

import java.util.*

class CreateAlbumFragment : Fragment() {
    // TODO: Rename and change types of parameters


    private lateinit var viewModel: CreateAlbumViewModel
    private lateinit var genre: Spinner
    private lateinit var label: Spinner
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_create_album, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel = ViewModelProvider(
            this,
            CreateAlbumViewModel.Factory(
                (activity as AppCompatActivity?)!!.application,
                AlbumRepository((activity as AppCompatActivity?)!!.application)
            )
        ).get(CreateAlbumViewModel::class.java)
        label = view.findViewById<Spinner>(R.id.spinnerLabel)
        genre = view.findViewById<Spinner>(R.id.spinnerGenre)
        var genreAdapter = ArrayAdapter<String>(
            requireContext(), android.R.layout.simple_spinner_item,
            listOf<String>("Classical", "Salsa", "Rock", "Folk")
        )
        var labelList =
            listOf<String>("Sony Music", "EMI", "Discos Fuentes", "Elektra", "Fania Records")
        var labelAdapter =
            ArrayAdapter<String>(requireContext(), android.R.layout.simple_spinner_item, labelList)
        viewModel.loading.observe(viewLifecycleOwner){
            var button=view.findViewById<Button>(R.id.creatAlbumButton)
            button.isEnabled=!it
        }
        viewModel.succes.observe(viewLifecycleOwner){
            if(it){
                showError("Album creado")
                findNavController().navigate(CreateAlbumFragmentDirections.actionCreateAlbumFragmentToAlbumListFragment())
            }
        }
        label.adapter = labelAdapter
        genre.adapter = genreAdapter
        var button = view.findViewById<Button>(R.id.creatAlbumButton)
        button.setOnClickListener {
            var name = view.findViewById<EditText>(R.id.editTextAlbumName)
            var cover = view.findViewById<EditText>(R.id.editTextAlbumCover)
            var description = view.findViewById<EditText>(R.id.editTextDescription)
            var date = view.findViewById<EditText>(R.id.editTextGenre)


            if (validateFields(
                    name.text.toString(),
                    cover.text.toString(), date.text.toString(),
                    description.text.toString()

                )
            ) {
                viewModel.postAlbum(
                    name.text.toString(),
                    cover.text.toString(),
                    date.text.toString(),
                    description.text.toString(), genre.selectedItem.toString(),
                    label.selectedItem.toString(),

                    )
            }

        }

        super.onViewCreated(view, savedInstanceState)
    }

    fun showError(
        message: String
    ) {
        var toast: Toast = Toast.makeText(context, message, Toast.LENGTH_SHORT)
        toast.show()
    }

    fun validateFields(
        name: String,
        cover: String,
        date: String,
        description: String,
    ): Boolean {
        if (name.isEmpty() || cover.isEmpty() || date.isEmpty() || description.isEmpty()) {
            showError("No todos los campos han sido llenados")
            return false
        }
        if (!cover.contains("https:/")) {
            showError("Link no valido")
            return false
        }
        Log.d("Entro", date)
        if (!date.contains("-")) {
            showError("Formato de fecha invalida")
            return false
        } else {

            var releaseDate = SimpleDateFormat("yyyy-MM-dd").parse(date.replace("/", "-"))
            Log.d("Entro", releaseDate.toString())
            var today =
                SimpleDateFormat("yyyy-MM-dd").parse(SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().time))

            if (releaseDate.compareTo(today) == 1) {
                showError("Fecha invalida")
                return false
            }


        }
        return true


    }
}