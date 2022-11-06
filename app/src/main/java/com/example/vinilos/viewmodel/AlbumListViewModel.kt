package com.example.vinilos.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.android.volley.Response
import com.example.vinilos.model.Album
import com.example.vinilos.model.Comentario
import com.example.vinilos.model.Track
import com.example.vinilos.repostories.AlbumRepository
import com.example.vinilos.services.AlbumService
import org.json.JSONArray

class AlbumListViewModel(application: Application,var albumRepository: AlbumRepository) : AndroidViewModel(application) {
    private val _albums = MutableLiveData<List<Album>>();

    private val _albumsFiltered = MutableLiveData<List<Album>>();
    val albumsFiltered: LiveData<List<Album>> get() = _albumsFiltered;
    private val _genres = MutableLiveData<List<String>>()
    val genres:MutableLiveData<List<String>> get()=_genres

    private val _loading=MutableLiveData<Boolean>()
    val loading:MutableLiveData<Boolean>get()=_loading
    init {
        print("AQUI")
        refreshDataFromNetwork()
       // _loading.value=true;
    }

    private fun refreshDataFromNetwork() {


        _albumsFiltered.value= albumRepository.getAlbums()


//        AlbumService.getInstance(getApplication()).getAlbums({
//            Log.d("Entro", "===")
//            _albums.postValue(it)
//
//        }, {
//
//        }) //implementar error
    }

    fun getAlbumFiltered(genre: String) {
        Log.d("Entro", "Albumes" + _albums.value.toString())
        if (genre == "Generos") {
            if(_albums.value!=null) {
                _albumsFiltered.postValue(_albums.value)
            }
        } else {
            var filterAlbums = mutableListOf<Album>()
            for (album in _albums.value!!) {
                if (genre == album.genre) {
                    filterAlbums.add(album)
                    Log.d("Entro", "Filtro" + album.genre + "Album" + album.name)
                }
            }
            _albumsFiltered.postValue(filterAlbums)
            Log.d("Entro", "Filtro" + filterAlbums.toString())
        }
    }

    fun fillGenres() {

        var genresSet = mutableSetOf<String>()
        genresSet.add("Generos")
        if(_albumsFiltered.value!=null){

            Log.d("Entro","YO")
        if (_albums.value == null) {
            for (album in _albumsFiltered.value!!) {
                genresSet.add(album.genre)
            }
        } else {

            for (album in _albums.value!!) {
                genresSet.add(album.genre)
            }
        }}
        _genres.postValue(genresSet.toList())

        _loading.value=false
    }

    class Factory(val app: Application,val albumRepository: AlbumRepository) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(AlbumListViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return AlbumListViewModel(app,albumRepository) as T
            }
            throw IllegalArgumentException("unable to construct view model")
        }
    }
}