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
    private var _success=MutableLiveData<Boolean>()
    private  var _loading=MutableLiveData<Boolean>()
    val loading:LiveData<Boolean> get()=_loading
    val succes:LiveData<Boolean> get()=_success
    private var _error=MutableLiveData<Boolean>()
    val error:LiveData<Boolean> get()=_error
    init {
        _success.postValue(false)
        _loading.postValue(false)
        _error.postValue(false)
    }
    fun showError(){
        _loading.postValue(!loading.value!!)
        _error.postValue(!error.value!!)
    }

    fun postAlbum(name:String,cover:String,date:String,description:String,genre:String,recordLabel:String) {
        _loading.postValue(true)
       try {
            viewModelScope.launch(Dispatchers.Default) {

                withContext(Dispatchers.IO) {
                   albumRepository.createAlbum(
                        name,
                        cover,
                        date,
                        description,
                        genre,recordLabel
                    )
                }
                _success.postValue(true)
                _loading.postValue(false)
            }
        } catch (e: java.lang.Exception) {
           Log.d("Entro", "error"+e.toString())
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