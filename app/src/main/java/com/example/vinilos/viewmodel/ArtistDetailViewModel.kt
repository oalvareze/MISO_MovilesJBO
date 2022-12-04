package com.example.vinilos.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.example.vinilos.model.Artist
import com.example.vinilos.model.ArtistDetail
import com.example.vinilos.repostories.ArtistRepository
import com.example.vinilos.repostories.CollectorRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ArtistDetailViewModel(var id:String, application: Application,var artistRepository: ArtistRepository): AndroidViewModel(application) {
    private var _artist=MutableLiveData<ArtistDetail>()
    val artist:LiveData<ArtistDetail>  get()=_artist
    init {
refreshFromNetwork()
    }
    fun refreshFromNetwork(){
        try {
            viewModelScope.launch(Dispatchers.Default) {
                withContext(Dispatchers.IO) {
                    val data = artistRepository.getUniqueArtist(id)
                    _artist.postValue(data)

                }
            }
        } catch (e: java.lang.Exception) {
            Log.d("Error", e.toString())
        }
    }
    class Factory(
        val app: Application,
        private val artistRepository: ArtistRepository,
        val id: String
    ) :
        ViewModelProvider.Factory {

        override fun <T : ViewModel> create(modelClass: Class<T>): T {

            if (modelClass.isAssignableFrom(ArtistDetailViewModel::class.java)) {

                @Suppress("UNCHECKED_CAST")
                return ArtistDetailViewModel(id,app, artistRepository) as T
            }
            throw IllegalArgumentException("unable to construct view model")
        }
    }
}