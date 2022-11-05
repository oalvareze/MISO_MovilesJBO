package com.example.vinilos.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.example.vinilos.model.Album
import com.example.vinilos.model.Comentario
import com.example.vinilos.model.Track
import com.example.vinilos.services.AlbumService
import org.json.JSONArray

class AlbumDetailViewModel(application: Application, private val albumId: Int) :
    AndroidViewModel(application) {

    private val _album = MutableLiveData<Album>()
    val album: LiveData<Album> get() = _album

    lateinit var albumService: AlbumService

    init {
        refreshDataFromNetwork()
    }

    private fun refreshDataFromNetwork() {
        Log.d("Cpnsulta Detalle album", "ENTRO refreshDataFromNetwork")
        albumService = AlbumService(this.getApplication())
        albumService.instance.add(
            AlbumService.getUniqueAlbumsRequest(
                "albums/$albumId",
                { response ->
                    Log.d("Consulta", response.get("id").toString())
                    Log.d("Longitud", response.length().toString())
                    Log.d("Imagen", response.get("cover").toString())
                    val listTrack: List<Track> = getTrack(response.getJSONArray("tracks"))
                    val listComments: List<Comentario> = getComments(response.getJSONArray("comments"))
                    _album.postValue(
                        Album(
                            response.get("id").toString().toInt(),
                            response.get("name").toString(),
                            response.get("cover").toString(),
                            response.get("releaseDate").toString(),
                            response.get("description").toString(),
                            response.get("genre").toString(),
                            response.get("recordLabel").toString(),
                            listTrack,
                            listComments
                        )
                    )
                },
                { Log.d("TAG", it.toString()) })
        )

//        AlbumService.getUniqueAlbumsRequest(getApplication()).getAlbum(albumId = albumId, onComplete = {
//
//            _album.postValue(it)
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

    class Factory(val app: Application, private val albumId: Int) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(AlbumDetailViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return AlbumDetailViewModel(app, albumId) as T
            }
            throw IllegalArgumentException("unable to construct view model")
        }
    }
}