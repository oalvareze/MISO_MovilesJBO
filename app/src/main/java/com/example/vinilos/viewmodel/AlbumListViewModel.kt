package com.example.vinilos.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.android.volley.Response
import com.example.vinilos.model.Album
import com.example.vinilos.model.Comentario
import com.example.vinilos.model.Track
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
        albumService = AlbumService(this.getApplication())
        albumService.instance.add(
            AlbumService.getAlbumsRequest("albums",
                { response ->
                    val list = mutableListOf<Album>()
                    val listTrack = mutableListOf<Track>()
                    val listComment = mutableListOf<Comentario>()
                    for (i in 0 until response.length()) {
                        val listTrack: List<Track> = getTrack(response.getJSONObject(i).getJSONArray("tracks"))
                        val listComments: List<Comentario> = getComments(response.getJSONObject(i).getJSONArray("comments"))
                        list.add(
                            Album(
                                response.getJSONObject(i).get("id").toString().toInt(),
                                response.getJSONObject(i).get("name").toString(),
                                response.getJSONObject(i).get("cover").toString(),
                                response.getJSONObject(i).get("releaseDate").toString(),
                                response.getJSONObject(i).get("description").toString(),
                                response.getJSONObject(i).get("genre").toString(),
                                response.getJSONObject(i).get("recordLabel").toString(),
                                listTrack,
                                listComments
                            )
                        )
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

    private fun getTrack(response: JSONArray): List<Track> {
        val listTrack = mutableListOf<Track>()
        for (i in 0 until response.length()) {
            listTrack.add(
                Track(
                    response.getJSONObject(i).get("name").toString(),
                    response.getJSONObject(i).get("duration").toString()
                )
            )
        }
        return listTrack
    }

    private fun getComments(response: JSONArray): List<Comentario> {
        val listComentario = mutableListOf<Comentario>()
        for (i in 0 until response.length()) {
            listComentario.add(
                Comentario(
                    response.getJSONObject(i).get("description").toString(),
                    response.getJSONObject(i).get("rating").toString(),
                    response.getJSONObject(i).get("id").toString().toInt()
                )
            )
        }
        return listComentario
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