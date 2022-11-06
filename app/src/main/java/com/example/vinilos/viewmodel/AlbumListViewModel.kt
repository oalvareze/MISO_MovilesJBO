package com.example.vinilos.viewmodel

import android.app.Application
import androidx.lifecycle.*
import com.android.volley.VolleyError
import com.example.vinilos.model.Album
import com.example.vinilos.repostories.AlbumRepository

class AlbumListViewModel(application: Application) : AndroidViewModel(application) {
    private val _albums = MutableLiveData<List<Album>>();
    val albums: LiveData<List<Album>> get() = _albums;

    lateinit var albumRepository: AlbumRepository

    init {
        refreshDataFromNetwork()
    }

    private fun refreshDataFromNetwork() {
        albumRepository = AlbumRepository(this.getApplication())
        albumRepository.getAlbums({
            _albums.postValue(it)
        }, {
            VolleyError("No se ha encontrado informacion")
        })

//        AlbumService.getInstance(getApplication()).getAlbums({
//            Log.d("Entro", "===")
//            _albums.postValue(it)
//
//        }, {
//
//        }) //implementar error
    }

    class Factory(val app: Application) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(AlbumListViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return AlbumListViewModel(app) as T
            }
            throw IllegalArgumentException("unable to construct view model")
        }
    }
}