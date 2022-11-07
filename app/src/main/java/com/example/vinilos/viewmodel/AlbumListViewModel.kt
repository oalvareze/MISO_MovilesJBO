package com.example.vinilos.viewmodel

import android.app.Application
import androidx.lifecycle.*
import com.example.vinilos.model.Album
import com.example.vinilos.repostories.AlbumRepository

class AlbumListViewModel(application: Application, var albumRepository: AlbumRepository) :
    AndroidViewModel(application) {

    private val _albums = MutableLiveData<List<Album>>();

    private val _albumsFiltered = MutableLiveData<List<Album>>();
    val albumsFiltered: LiveData<List<Album>> get() = _albumsFiltered;
    private val _genres = MutableLiveData<List<String>>()
    val genres: MutableLiveData<List<String>> get() = _genres

    private val _loading = MutableLiveData<Boolean>()
    val loading: MutableLiveData<Boolean> get() = _loading

    init {
        refreshDataFromNetwork()
        _loading.value = true;
    }

    private fun refreshDataFromNetwork() {
        albumRepository.getAlbums({
            _albumsFiltered.postValue(it)
            _albums.postValue(it)
        }, {
            print("entro")
        })
    }

    fun getAlbumFiltered(genre: String) {

        if (genre == "Generos") {
            if (_albums.value != null) {
                _albumsFiltered.postValue(_albums.value)
            }
        } else {
            var filterAlbums = mutableListOf<Album>()
            for (album in _albums.value!!) {
                if (genre == album.genre) {
                    filterAlbums.add(album)

                }
            }
            _albumsFiltered.postValue(filterAlbums)

        }
    }

    fun fillGenres() {
        var genresSet = mutableSetOf<String>()
        genresSet.add("Generos")
        if (_albumsFiltered.value != null) {
            for (album in _albumsFiltered.value!!) {
                genresSet.add(album.genre)
            }
        }
        _genres.postValue(genresSet.toList())
        _loading.value = false
    }

    class Factory(val app: Application, val albumRepository: AlbumRepository) :
        ViewModelProvider.Factory {

        override fun <T : ViewModel> create(modelClass: Class<T>): T {

            if (modelClass.isAssignableFrom(AlbumListViewModel::class.java)) {

                @Suppress("UNCHECKED_CAST")
                return AlbumListViewModel(app, albumRepository) as T
            }
            throw IllegalArgumentException("unable to construct view model")
        }
    }
}