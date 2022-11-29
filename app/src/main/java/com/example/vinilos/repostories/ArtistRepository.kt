package com.example.vinilos.repostories

import android.app.Application
import android.util.Log
import com.example.vinilos.model.AlbumCreate
import com.example.vinilos.model.Artist
import com.example.vinilos.model.ArtistDetail
import com.example.vinilos.services.ArtistService
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import org.json.JSONArray
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

class ArtistRepository(private val application: Application) {


    suspend fun getUniqueArtist(id: String) = suspendCoroutine<ArtistDetail> { cont ->
        val artistService = ArtistService.getInstance(application)
        artistService.instance.add(artistService.getUniqueArtist("/$id", {
            Log.d("ENtro",it.toString())
            var artist = ArtistDetail(
                Integer.parseInt(it.get("id").toString()),
                it.get("name").toString(),
                it.get("image").toString(),
                it.get("description").toString(),
                it.get("birthDate").toString(),
                getArtistAlbum(it.getJSONArray("albums"))
            )
            cont.resume(artist)
        }, {
            cont.resumeWithException(it)
        }))
    }

    private fun getArtistAlbum(response: JSONArray): List<AlbumCreate> {
        var albums = mutableListOf<AlbumCreate>()
        for (i in 0 until response.length()) {
            albums.add(
                Json.decodeFromString<AlbumCreate>(
                    response.getJSONObject(i).toString()
                )
            )
        }
        return albums
    }

    suspend fun getArtists() = suspendCoroutine<List<Artist>> { cont ->

        val artistService = ArtistService.getInstance(application)
        artistService.instance.add(artistService.getArtistRequest({
            val list = mutableListOf<Artist>()
            for (i in 0 until it.length()) {
                Log.d("ArtistRepository", "cicly")

//                val listTrack: List<Track> = emptyList()
//                val listComments: List<Comentario> = emptyList()

                list.add(
                    Artist(
                        Integer.parseInt(it.getJSONObject(i).get("id").toString()),
                        it.getJSONObject(i).get("name").toString(),
                        it.getJSONObject(i).get("image").toString(),
                        it.getJSONObject(i).get("description").toString(),
                        it.getJSONObject(i).get("birthDate").toString()
//                        null, null, null
                    )
                )
            }
            cont.resume(list.sortedBy { item -> item.name })
        }, {
            cont.resumeWithException(it)
        }))
    }


}