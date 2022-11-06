package com.example.vinilos.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.example.vinilos.model.Album
import com.example.vinilos.repostories.AlbumRepository

class AlbumDetailViewModel(application: Application, private val albumId: Int) :
    AndroidViewModel(application) {

    private val _album = MutableLiveData<Album>()
    val album: LiveData<Album> get() = _album

    lateinit var albumRepository: AlbumRepository

    init {
        refreshDataFromNetwork()
    }

    private fun refreshDataFromNetwork() {
        Log.d("Entro", "ENTRO refreshDataFromNetwork")
        albumRepository = AlbumRepository(this.getApplication())
        albumRepository.getUniqueAlbum("albums/${albumId}", {
            _album.postValue(it)
        }, {
            Log.d("Entro", "ENTRO refreshDataFromNetwork")
        })

//        AlbumService.getUniqueAlbumsRequest(getApplication()).getAlbum(albumId = albumId, onComplete = {
//
//            _album.postValue(it)
//
//        }) //implementar error
    }

    class Factory(val app: Application, private val albumId: Int) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(AlbumDetailViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return AlbumDetailViewModel(app, albumId) as T
            }
            throw IllegalArgumentException("unable to construct view model")
        }
    }
}