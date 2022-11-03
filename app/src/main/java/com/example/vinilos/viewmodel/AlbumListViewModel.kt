package com.example.vinilos.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.example.vinilos.model.Album
import com.example.vinilos.services.AlbumService

class AlbumListViewModel(application: Application) :AndroidViewModel(application){
    private val _albums= MutableLiveData<List<Album>>();
    val albums:LiveData<List<Album>> get() = _albums;
    init{
        refreshDataFromNetwork()
    }
    private  fun refreshDataFromNetwork(){
        AlbumService.getInstance(getApplication()).getAlbums({

        _albums.postValue(it)

        },{

        }) //implementar error
    }
    class Factory(val app: Application) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if(modelClass.isAssignableFrom(AlbumListViewModel::class.java)){
                @Suppress("UNCHECKED_CAST")
                return AlbumListViewModel(app) as T
            }
            throw IllegalArgumentException("unable to construct view model")
        }
    }
}