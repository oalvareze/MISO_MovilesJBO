package com.example.vinilos.view.createAlbum

import android.R.bool
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.signature.MediaStoreSignature
import com.example.vinilos.MainActivity
import com.example.vinilos.R
import com.example.vinilos.repostories.AlbumRepository
import com.example.vinilos.viewmodel.CreateAlbumViewModel
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*


class CreateAlbumFragment : Fragment() {
    // TODO: Rename and change types of parameters


    private lateinit var viewModel: CreateAlbumViewModel
    private lateinit var genre: Spinner
    private lateinit var label: Spinner
    private lateinit var messageText:TextView
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        (activity as AppCompatActivity?)!!.supportActionBar!!.title = "Crear album"
        return inflater.inflate(R.layout.fragment_create_album, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        messageText=view.findViewById(R.id.errorString)
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
        viewModel.error.observe(viewLifecycleOwner){
            messageText.visibility=if (it) View.VISIBLE else View.GONE
        }
        viewModel.succes.observe(viewLifecycleOwner){
            if(it){
                showMessage("Album creado",true)
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

    fun showMessage(
        message: String,finished:Boolean
    ) {
        messageText.text=message


        messageText.setTextColor(if (!finished)Color.parseColor("#FF0000")else Color.parseColor("#4BB543"))
        viewModel.showError()
        if(!finished){
        Handler(Looper.getMainLooper()).postDelayed({

           viewModel.showError()
        }, 1500) };
    }

    fun validateFields(
        name: String,
        cover: String,
        date: String,
        description: String,
    ): Boolean {
        if (name.isEmpty() || cover.isEmpty() || date.isEmpty() || description.isEmpty()) {
            showMessage("No todos los campos han sido llenados",false)
            return false
        }
        if (!cover.contains("https:/")) {
            showMessage("Link no valido",false)
            return false
        }
        Log.d("Entro", date)
        var dateL=date.split("-")
        if (!date.contains("-") || dateL.size!=3) {
            showMessage("Formato de fecha invalido",false)
            return false
        } else {

            try {
                if(!checkIfDateisValid(Integer.parseInt(dateL[0]),Integer.parseInt(dateL[1]),Integer.parseInt(dateL[2]))){
                    showMessage("Fecha invalida",false)
                    return false
                }
                var sdf=SimpleDateFormat("yyyy-MM-dd")
                sdf.isLenient=true
                var releaseDate = sdf.parse(date.replace("/", "-"))

                var today =
                    sdf.parse(sdf.format(Calendar.getInstance().time))

                if (releaseDate.compareTo(today) == 1) {
                    showMessage("Fecha invalida",false)
                    return false
                }

           }catch (e:ParseException){
                showMessage("Formato de fecha invalida",false)

                return false
           }


        }
        return true


    }
    fun isLeap(year: Int): Boolean {

        return (((year % 4 == 0) &&
                (year % 100 != 0)) ||
                (year % 400 == 0));
    }

    fun checkIfDateisValid(y:Int,m:Int,d:Int):Boolean{

            // If year, month and day
            // are not in given range
            if (
                y < 1800)
                return false;
            if (m < 1 || m > 12)
                return false;
            if (d < 1 || d > 31)
                return false;

            // Handle February month
            // with leap year
            if (m == 2)
            {
                if (isLeap(y))
                    return (d <= 29);
                else
                    return (d <= 28);
            }

            // Months of April, June,
            // Sept and Nov must have
            // number of days less than
            // or equal to 30.
            if (m == 4 || m == 6 ||
                m == 9 || m == 11)
                return (d <= 30);

            return true;
        }


}