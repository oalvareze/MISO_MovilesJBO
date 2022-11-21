package com.example.vinilos.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.example.vinilos.model.Collector
import com.example.vinilos.repostories.CollectorRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CollectorListViewModel(
    application: Application,
    var collectorRepository: CollectorRepository
) : AndroidViewModel(application) {

    private val _collectors = MutableLiveData<List<Collector>>();

    private val _loadCollector = MutableLiveData<List<Collector>>();
    val loadCollector: LiveData<List<Collector>> get() = _loadCollector;
    private val _filterSearchCollectors = MutableLiveData<List<Collector>>()
    val filterSearchCollectors: MutableLiveData<List<Collector>> get() = _filterSearchCollectors

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
                    val data = collectorRepository.getCollectors()
                    _collectors.postValue(data)
                    _loadCollector.postValue(data)
                }
            }
        } catch (e: java.lang.Exception) {
            Log.d("Error", e.toString())
        }
        Log.d("loading", "off")
        _loading.postValue(false)

    }

    fun getFilterCollector(letters: String) {
        Log.d("getFilterCollector", "start")
        if (letters == "") {
            if (_collectors.value != null) {
                _loadCollector.postValue(_collectors.value)
            }
        } else {
            _loadCollector.postValue(_collectors.value?.filter { collector ->
                collector.name.uppercase().contains(
                    letters.uppercase()
                )
            })
        }
    }

    class Factory(val app: Application, val collectorRepository: CollectorRepository) :
        ViewModelProvider.Factory {

        override fun <T : ViewModel> create(modelClass: Class<T>): T {

            if (modelClass.isAssignableFrom(CollectorListViewModel::class.java)) {

                @Suppress("UNCHECKED_CAST")
                return CollectorListViewModel(app, collectorRepository) as T
            }
            throw IllegalArgumentException("unable to construct view model")
        }
    }
}