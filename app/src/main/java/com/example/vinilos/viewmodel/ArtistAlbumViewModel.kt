package com.example.vinilos.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.example.vinilos.model.AlbumCreate
import com.example.vinilos.repostories.AlbumRepository
import com.example.vinilos.repostories.ArtistRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class ArtistAlbumViewModel(
    application: Application,var albumRepository: AlbumRepository,var artistRepository: ArtistRepository,var id:String
) : AndroidViewModel(application) {
    private val _album = MutableLiveData<List<AlbumCreate>>();
    private val _loading=MutableLiveData<Boolean>()
    val loading:LiveData<Boolean> get() = _loading
    private  val _error=MutableLiveData<Boolean>()
    private  val _success=MutableLiveData<Boolean>()
    val success:LiveData<Boolean>get() = _success
    val error:LiveData<Boolean> get()=_error
    private  val _artistAlbum = MutableLiveData<Set<Int>>()
     val album: LiveData<List<AlbumCreate>> get() = _album;
    init {
refreshFromNetwork()
    _loading.postValue(false)
        _success.postValue(false)
        _error.postValue(false)
    }
    fun artistAlbum():Set<Int>{

        return if(_artistAlbum==null) emptySet() else _artistAlbum!!.value!!
    }
    fun updateAlbums(albumsSelectedId:Set<Int>){
        _loading.postValue(true)
        var list= mutableListOf<AlbumCreate>()
        for (set in albumsSelectedId){
           var albumSelectedObject= _album.value!!.filter { albumCreate ->albumCreate.id==set  }
            list.add(albumSelectedObject.first())
        }
        try {
            viewModelScope.launch(Dispatchers.Default) {
                withContext(Dispatchers.IO) {
                    try {

                        artistRepository.updateArtistAlbum("/${id}/albums",list)
                        _success.postValue(true)

                    } catch (ex: Exception) {
                        _error.postValue(true)
                        Log.d("Error", "es" + ex.message)
                        _loading.postValue(false)
                    }

                }
            }
        } catch (e: Exception) {
            Log.d("Error", e.toString())
        }


    }
    fun refreshFromNetwork(){
        try {
            viewModelScope.launch(Dispatchers.Default) {
                withContext(Dispatchers.IO) {
                    try {

                        var albumArtist = artistRepository.getArtistAlbum(id)
                        var albumList=albumRepository.getAlbums()

                        _artistAlbum.postValue(albumArtist)



                        _album.postValue(albumList)
                    } catch (ex: Exception) {
                        Log.d("Error", "es" + ex.message)
                    }

                }
            }
        } catch (e: Exception) {
            Log.d("Error", e.toString())
        }

    }
    class Factory(var application: Application,var artistRepository: ArtistRepository,var albumRepository: AlbumRepository,var id:String):ViewModelProvider.Factory{
        override fun <T : ViewModel> create(modelClass: Class<T>): T {

            if (modelClass.isAssignableFrom(ArtistAlbumViewModel::class.java)) {

                @Suppress("UNCHECKED_CAST")
                return ArtistAlbumViewModel(application,albumRepository,artistRepository,id) as T
            }
            throw IllegalArgumentException("unable to construct view model")
        }

    }
}