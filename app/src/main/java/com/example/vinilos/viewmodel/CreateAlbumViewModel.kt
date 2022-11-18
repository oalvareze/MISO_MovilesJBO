package com.example.vinilos.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.example.vinilos.repostories.AlbumRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class CreateAlbumViewModel(application: Application, var albumRepository: AlbumRepository) :
    AndroidViewModel(application) {
    private val _name = MutableLiveData<String>()
    private val _cover = MutableLiveData<String>()
    private val _date = MutableLiveData<String>()
    private val _description = MutableLiveData<String>()
    private val _label = MutableLiveData<String>()
    val name: LiveData<String> get() = _name
    val cover: LiveData<String> get() = _cover
    val date: LiveData<String> get() = _date
    val description: LiveData<String> get() = _description
    val label: LiveData<String> get() = _label

    init {
        _name.postValue("")
        _cover.postValue("")
        _date.postValue("")
        _description.postValue("")
        _label.postValue("")
    }

    fun postAlbum() {
        Log.d("Entro", name.value!!)
        try {
            viewModelScope.launch(Dispatchers.Default) {
                withContext(Dispatchers.IO) {
                    var data = albumRepository.createAlbum(
                        name.toString(),
                        cover.toString(),
                        date.toString(),
                        description.toString(),
                        "Salsa","Sony Music"
                    )
                }
            }
        } catch (e: java.lang.Exception) {
            Log.d("Error", e.toString())
        }
    }

    class Factory(val app: Application, val albumRepository: AlbumRepository) :
        ViewModelProvider.Factory {

        override fun <T : ViewModel> create(modelClass: Class<T>): T {

            if (modelClass.isAssignableFrom(CreateAlbumViewModel::class.java)) {

                @Suppress("UNCHECKED_CAST")
                return CreateAlbumViewModel(app, albumRepository) as T
            }
            throw IllegalArgumentException("unable to construct view model")
        }
    }

}