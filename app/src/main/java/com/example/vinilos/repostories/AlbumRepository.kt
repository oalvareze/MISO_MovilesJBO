package com.example.vinilos.repostories

import android.app.Application
import android.util.Log
import com.example.vinilos.model.Album
//import com.example.vinilos.model.AlbumCreate
import com.example.vinilos.model.Comentario
import com.example.vinilos.model.Track
import com.example.vinilos.services.AlbumService
import com.example.vinilos.services.CacheManager
import org.json.JSONArray
import org.json.JSONObject
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

class AlbumRepository(private val application: Application) {

    init {
        print("Inicia el consumo AlbumRepository")
    }

    suspend fun getAlbums() = suspendCoroutine<List<Album>> { cont ->
        val albumService = AlbumService.getInstance(application)
        albumService.instance.add(albumService.getAlbumsRequest("albums", {
            val list = mutableListOf<Album>()
            for (i in 0 until it.length()) {


                list.add(
                    Album(
                        Integer.parseInt(it.getJSONObject(i).get("id").toString()),
                        it.getJSONObject(i).get("name").toString(),
                        it.getJSONObject(i).get("cover").toString(),
                        "a,",
                        "a",
                        it.getJSONObject(i).get("genre").toString(),
                        "uno",
                        emptyList(),
                        emptyList(),
                        "Artista"
                    )
                )
            }
            cont.resume(list)
        }, {
            cont.resumeWithException(it)
        }))
    }

    suspend fun getUniqueAlbum(url: String) = suspendCoroutine<Album> { cont ->
        val idAlbum = url.split("/")[1].toInt()
        val potentialResp = CacheManager.getInstance(application.applicationContext)
            .getAlbum(idAlbum)
        if (potentialResp == null) {
            Log.d("Cache decision", "get from network")
            val albumService = AlbumService.getInstance(application)

            albumService.instance.add(albumService.getUniqueAlbumsRequest(url, {



                val album = Album(
                    it.get("id").toString().toInt(),
                    it.get("name").toString(),
                    it.get("cover").toString(),
                    it.get("releaseDate").toString().split("T").toTypedArray().get(0),
                    it.get("description").toString(),
                    it.get("genre").toString(),
                    it.get("recordLabel").toString(),
                    getTrack(it.getJSONArray("tracks")),
                    getComments(it.getJSONArray("comments")),getArtist(it.getJSONArray("performers"))
                )


                CacheManager.getInstance(application.applicationContext)
                    .addAlbum(url.split("/")[1].toInt(), album)


                cont.resume(album)
            }, {
                cont.resumeWithException(it)
            }))
        } else {
            Log.d("Cache decision", "get from CachedManager")
            cont.resume(potentialResp)
        }
    }

    suspend fun createAlbum(name: String,
                            cover: String,
                            releaseDate: String,
                            description: String,
                            genre: String,
                            recordLabel: String) = suspendCoroutine<String> { cont ->
        val albumService = AlbumService.getInstance(application)
        Log.d("Entro","name:${name},cover:${cover},date:${releaseDate},description:${description},genre:${genre},RL:${recordLabel}")
        val album = mapOf(
            "name" to name,
            "cover" to cover,
            "releaseDate" to releaseDate,
            "description" to description,
            "genre" to genre,
            "recordLabel" to recordLabel
        )
        Log.d("Entro",JSONObject(album).toString())
        albumService.instance.add(albumService.createAlbum("albums", JSONObject(album), {
          it.toString()

            cont.resume(it.toString())
        }, {
            Log.d("Entro","Error")
          cont.resumeWithException(it) }))
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

    private fun getArtist(response: JSONArray): String {
        if (response.length() > 0) {
            return response.getJSONObject(0).get("name").toString()
        } else
            return "Este album no tiene artista asignado"
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
}


