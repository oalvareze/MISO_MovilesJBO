package com.example.vinilos.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.example.vinilos.model.Artist
import com.example.vinilos.repostories.ArtistRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ArtistListViewModel(
    application: Application,
    var artistRepository: ArtistRepository
) : AndroidViewModel(application) {

    private val _artists = MutableLiveData<List<Artist>>();

    private val _loadArtist = MutableLiveData<List<Artist>>();
    val loadArtist: LiveData<List<Artist>> get() = _loadArtist;
//    private val _filterSearchCollectors = MutableLiveData<List<Collector>>()
//    val filterSearchCollectors: MutableLiveData<List<Collector>> get() = _filterSearchCollectors

    private val _loading = MutableLiveData<Boolean>()
    val loading: MutableLiveData<Boolean> get() = _loading

    init {
        refreshDataFromNetwork()
        _loading.value = true;
    }

    private fun refreshDataFromNetwork() {
        try {
            viewModelScope.launch(Dispatchers.Default) {
                withContext(Dispatchers.IO) {
                    val data = artistRepository.getArtists()
                    _artists.postValue(data)
                    _loadArtist.postValue(data)
                    Log.d("entro",data.toString()       )
                }
            }
        } catch (e: java.lang.Exception) {
            Log.d("Error", e.toString())
        }
        Log.d("loading", "off")
        _loading.postValue(false)

    }

//    fun getFilterCollector(letters: String) {
//        Log.d("getFilterCollector", "start")
//        if (letters == "") {
//            if (_collectors.value != null) {
//                _loadCollector.postValue(_collectors.value)
//            }
//        } else {
//            _loadCollector.postValue(_collectors.value?.filter { collector ->
//                collector.name.uppercase().contains(
//                    letters.uppercase()
//                )
//            })
//        }
//    }

    class Factory(val app: Application, val artistRepository: ArtistRepository) :
        ViewModelProvider.Factory {

        override fun <T : ViewModel> create(modelClass: Class<T>): T {

            if (modelClass.isAssignableFrom(ArtistListViewModel::class.java)) {

                @Suppress("UNCHECKED_CAST")
                return ArtistListViewModel(app, artistRepository) as T
            }
            throw IllegalArgumentException("unable to construct view model")
        }
    }
}