package com.example.vinilos.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.example.vinilos.model.Album
import com.example.vinilos.model.Comentario
import com.example.vinilos.model.Track
import com.example.vinilos.repostories.AlbumRepository
import com.example.vinilos.services.AlbumService
import com.example.vinilos.view.albumdetail.AlbumDetail
import org.json.JSONArray

class AlbumDetailViewModel(application: Application, private val albumId: Int,val albumRepository: AlbumRepository) :
    AndroidViewModel(application) {

    private val _album = MutableLiveData<Album>();
    val album: LiveData<Album> get() = _album;



    init {
        refreshDataFromNetwork()
    }

    private fun refreshDataFromNetwork() {


        albumRepository.getUniqueAlbum("albums/${albumId}",{
                                                            _album.postValue(it)

        },{

        })

    }


    class Factory(val app: Application, private val albumId: Int,val albumRepository: AlbumRepository) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(AlbumDetailViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return AlbumDetailViewModel(app, albumId, albumRepository ) as T
            }
            throw IllegalArgumentException("unable to construct view model")
        }
    }
}