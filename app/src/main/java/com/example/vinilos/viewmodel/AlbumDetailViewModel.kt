package com.example.vinilos.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.example.vinilos.model.Album
import com.example.vinilos.model.Comentario
import com.example.vinilos.model.Track
import com.example.vinilos.services.AlbumService
import com.example.vinilos.view.albumdetail.AlbumDetail
import org.json.JSONArray

class AlbumDetailViewModel(application: Application, private val albumId: Int) :
    AndroidViewModel(application) {

    private val _album = MutableLiveData<Album>();
    val album: LiveData<Album> get() = _album;

    lateinit var albumService: AlbumService

    init {
        refreshDataFromNetwork()
    }

    private fun refreshDataFromNetwork() {
        Log.d("Cpnsulta", "ENTRO refreshDataFromNetwork")
        albumService = AlbumService(this.getApplication())
        albumService.instance.add(
            AlbumService.getUniqueAlbumsRequest("albums",
                { response ->
                    Log.d("Cpnsulta", response.get("id").toString())
                    Log.d("Longitud", response.length().toString())
                    Log.d("Imagen", response.get("cover").toString())
                    val listTrack: List<Track> = getTrack(response.getJSONArray("track"))
                    val listComments: List<Comentario> = getComments(response.getJSONArray("comments"))
                    _album.postValue(
                        Album(
                            1, "prueba", response.get("cover").toString(),
                            "a,", "a", "lorem", "uno",
                            listTrack, listComments
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
                    response.getJSONObject(i).get("nombre").toString(),
                    response.getJSONObject(i).get("duracion").toString()
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