package com.example.vinilos.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.example.vinilos.model.Album
import com.example.vinilos.model.Comentario
import com.example.vinilos.model.Track
import com.example.vinilos.repostories.AlbumRepository
import com.example.vinilos.services.AlbumService
import org.json.JSONArray

class AlbumDetailViewModel(application: Application, private val albumId: Int) :
    AndroidViewModel(application) {

    private val _album = MutableLiveData<Album>()
    val album: LiveData<Album> get() = _album

    lateinit var albumRepository: AlbumRepository

    init {
        refreshDataFromNetwork()
    }

    private fun refreshDataFromNetwork() {
        Log.d("Entro", "ENTRO refreshDataFromNetwork")
        albumRepository = AlbumRepository(this.getApplication())
        albumRepository.getUniqueAlbum("albums/${albumId}",{
                                                            _album.postValue(it)

        },{
            Log.d("Entro", "ENTRO refreshDataFromNetwork")
        })

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