package com.example.vinilos.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.example.vinilos.model.Collector
import com.example.vinilos.repostories.CollectorRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CollectorDetailViewModel(
    application: Application,
    var collectorRepository: CollectorRepository,
    val id: String
) : AndroidViewModel(application) {
    private val _collector = MutableLiveData<Collector>();
    val collector: LiveData<Collector> get() = _collector

    init {
        refreshDataFromNetwork()
    }

    fun refreshDataFromNetwork() {
        try {
            viewModelScope.launch(Dispatchers.Default) {
                withContext(Dispatchers.IO) {
                    try {

                        val artistList = collectorRepository.getCollectorFavoriteArtist(id)
                        var albumCollector=collectorRepository.getCollectorAlbums(id)
                        val data = collectorRepository.getCollector(id).copy(favoritePerformers = artistList, collectorAlbums = albumCollector)


                        _collector.postValue(data)
                    } catch (ex: Exception) {
                        Log.d("Error", "es" + ex.message)
                    }

                }
            }
        } catch (e: Exception) {
            Log.d("Error", e.toString())
        }
    }

    class Factory(
        val app: Application,
        private val collectorRepository: CollectorRepository,
        val id: String
    ) :
        ViewModelProvider.Factory {

        override fun <T : ViewModel> create(modelClass: Class<T>): T {

            if (modelClass.isAssignableFrom(CollectorDetailViewModel::class.java)) {

                @Suppress("UNCHECKED_CAST")
                return CollectorDetailViewModel(app, collectorRepository, id) as T
            }
            throw IllegalArgumentException("unable to construct view model")
        }
    }
}