package com.example.vinilos.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.example.vinilos.model.AlbumCreate


class ArtistAlbumViewModel(
    application: Application,
) : AndroidViewModel(application) {
    private val _album = MutableLiveData<List<AlbumCreate>>();

     val album: LiveData<List<AlbumCreate>> get() = _album;
    init {
refreshFromNetwork()
        Log.d("entro",_album.value.toString())
    }
    fun refreshFromNetwork(){
        var albumL=listOf<AlbumCreate>(AlbumCreate(1,"3","3","3e","e","3","c"),
            AlbumCreate(2,"3","3","3e","e","3","c"))

        _album.postValue(albumL)


    }
    class Factory(var application: Application):ViewModelProvider.Factory{
        override fun <T : ViewModel> create(modelClass: Class<T>): T {

            if (modelClass.isAssignableFrom(ArtistAlbumViewModel::class.java)) {

                @Suppress("UNCHECKED_CAST")
                return ArtistAlbumViewModel(application) as T
            }
            throw IllegalArgumentException("unable to construct view model")
        }

    }
}