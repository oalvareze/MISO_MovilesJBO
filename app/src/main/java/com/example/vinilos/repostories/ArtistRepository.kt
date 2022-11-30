package com.example.vinilos.repostories

import android.app.Application
import android.util.Log
import com.example.vinilos.model.*
import com.example.vinilos.services.AlbumService
import com.example.vinilos.services.ArtistService
import com.example.vinilos.services.CacheManager
import com.example.vinilos.services.CollectoService
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine
import kotlinx.serialization.json.Json
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import org.json.JSONArray
import org.json.JSONObject
import java.util.IllegalFormatCodePointException

class ArtistRepository(private val application: Application) {


    suspend fun getUniqueArtist(id: String) = suspendCoroutine<ArtistDetail> { cont ->
        val potentialResp = CacheManager.getInstance(application.applicationContext)
            .getArtist(Integer.parseInt(id))
        if (potentialResp == null) {
            val artistService = ArtistService.getInstance(application)
            artistService.instance.add(artistService.getUniqueArtist("/$id", {

                var artist = ArtistDetail(
                    Integer.parseInt(it.get("id").toString()),
                    it.get("name").toString(),
                    it.get("image").toString(),
                    it.get("description").toString(),
                    it.get("birthDate").toString(),
                    getArtistAlbumList(it.getJSONArray("albums"))
                )
                CacheManager.getInstance(application.applicationContext)
                    .addArtist(id.toInt(), artist)
                cont.resume(artist)
            }, {
                cont.resumeWithException(it)
            }))
        } else {
            cont.resume(potentialResp)
        }
    }

    suspend fun getArtistAlbum(id: String) = suspendCoroutine<Set<Int>> { cont ->
        val potentialResp = CacheManager.getInstance(application.applicationContext)
            .getArtist(Integer.parseInt(id))
        if (potentialResp != null) {
            var setId= mutableSetOf<Int>()
            for(album in potentialResp.albums){
                setId.add(album.id)
            }
            cont.resume(setId)
        } else {

            val artistService = ArtistService.getInstance(application)
            artistService.instance.add(artistService.getUniqueArtist("/$id", {
                var list=getArtistAlbumList(it.getJSONArray("albums"))
                var artist = ArtistDetail(
                    Integer.parseInt(it.get("id").toString()),
                    it.get("name").toString(),
                    it.get("image").toString(),
                    it.get("description").toString(),
                    it.get("birthDate").toString(),
                    list
                )
                CacheManager.getInstance(application.applicationContext)
                    .addArtist(id.toInt(), artist)
                var setId= mutableSetOf<Int>()
                for(album in list){
                    setId.add(album.id)
                }
                cont.resume(setId)
            }, {
                cont.resumeWithException(it)
            }))

        }
    }
    suspend fun updateArtistAlbum(id:String,albums:List<AlbumCreate>)= suspendCoroutine<String> {cont->
        val artistService= ArtistService.getInstance(application)
        var list= mutableListOf<JSONObject>()
        for (album in albums){
                list.add( JSONObject(Json.encodeToString(album)))
        }

        artistService.instance.add(artistService.udpateArtistAlbum(id, JSONArray(list), {
           Log.d("Entro","Funciono")
            var list=getArtistAlbumList(it.getJSONArray("albums"))
            var artist = ArtistDetail(
                Integer.parseInt(it.get("id").toString()),
                it.get("name").toString(),
                it.get("image").toString(),
                it.get("description").toString(),
                it.get("birthDate").toString(),
                list
            )

            CacheManager.getInstance(application.applicationContext)
                .updateArtist(id.split("/")[1].toInt(), artist)

            cont.resume(it.toString())
        }, {
            Log.d("Entro","Error")
            cont.resumeWithException(it) }))

    }
    private fun getArtistAlbumList(response: JSONArray): List<AlbumCreate> {
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

                list.add(
                    Artist(
                        Integer.parseInt(it.getJSONObject(i).get("id").toString()),
                        it.getJSONObject(i).get("name").toString(),
                        it.getJSONObject(i).get("image").toString(),
                        it.getJSONObject(i).get("description").toString(),
                        it.getJSONObject(i).get("birthDate").toString()

                    )
                )
            }
            cont.resume(list.sortedBy { item -> item.name })
        }, {
            cont.resumeWithException(it)
        }))
    }


}