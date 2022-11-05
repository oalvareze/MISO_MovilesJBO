package com.example.vinilos.viewmodel

import android.app.Application
import androidx.lifecycle.*
import com.example.vinilos.model.Album
import com.example.vinilos.services.AlbumService
import com.example.vinilos.view.albumdetail.AlbumDetail

class AlbumDetailViewModel(application: Application,private val albumId:Int) : AndroidViewModel(application){
    private val _album= MutableLiveData<Album>();
    val album: LiveData<Album> get() = _album;
    init{
        refreshDataFromNetwork()
    }
    private  fun refreshDataFromNetwork(){
        AlbumService.getInstance(getApplication()).getAlbum(albumId=albumId, onComplete = {

            _album.postValue(it)

        }) //implementar error
    }
    class Factory(val app: Application, private val albumId: Int) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if(modelClass.isAssignableFrom(AlbumDetailViewModel::class.java)){
                @Suppress("UNCHECKED_CAST")
                return AlbumDetailViewModel(app, albumId) as T
            }
            throw IllegalArgumentException("unable to construct view model")
        }
    }}