package com.example.vinilos.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.android.volley.Response
import com.example.vinilos.model.Album
import com.example.vinilos.services.AlbumService
import org.json.JSONArray

class AlbumListViewModel(application: Application) : AndroidViewModel(application) {
    private val _albums = MutableLiveData<List<Album>>();
    val albums: LiveData<List<Album>> get() = _albums;

    lateinit var albumService: AlbumService

    init {
        refreshDataFromNetwork()
    }

    private fun refreshDataFromNetwork() {
        Log.d("Cpnsulta", "ENTRO refreshDataFromNetwork")
        albumService = AlbumService(this.getApplication())
        albumService.instance.add(
            AlbumService.getAlbumsRequest("albums",
                { response ->
                    val list = mutableListOf<Album>()
                    for (i in 0 until response.length()){
                        Log.d("Cpnsulta", response.get(0).toString())
                        Log.d("Longitud", response.length().toString())
                        Log.d("Imagen", response.getJSONObject(i).get("cover").toString())
                        list.add(Album(1, "prueba", response.getJSONObject(i).get("cover").toString(),
                        "a,", "a", "lorem", "uno"))
                    }
                    _albums.postValue(list)


                },
                { Log.d("TAG", it.toString()) })
        )

        Log.d("pASO", "PASO")

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