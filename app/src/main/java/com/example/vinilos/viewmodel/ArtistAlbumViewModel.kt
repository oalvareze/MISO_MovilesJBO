package com.example.vinilos.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.vinilos.model.AlbumCreate


class ArtistAlbumViewModel(
    application: Application,
) : AndroidViewModel(application) {
    private val _album = MutableLiveData<List<AlbumCreate>>();

     val album: LiveData<List<AlbumCreate>> get() = _album;
    init {
refreshFromNetwork()
    }
    fun refreshFromNetwork(){
        _album.postValue(listOf<AlbumCreate>(AlbumCreate(1,"3","3","3e","e","3","c"),
            AlbumCreate(2,"3","3","3e","e","3","c")))

    }
}