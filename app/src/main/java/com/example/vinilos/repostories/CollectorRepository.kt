package com.example.vinilos.repostories

import android.app.Application
import android.util.Log
import com.example.vinilos.model.AlbumCollector
import com.example.vinilos.model.AlbumCreate
import com.example.vinilos.model.Artist
import com.example.vinilos.model.Collector
import com.example.vinilos.services.CacheManager
import com.example.vinilos.services.CollectoService
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

class CollectorRepository(private val application: Application) {


    suspend fun getCollector(id: String) = suspendCoroutine<Collector> { cont ->
        val potentialResp = CacheManager.getInstance(application.applicationContext)
            .getCollector(Integer.parseInt(id))
        if (potentialResp == null) {
            val collectoService = CollectoService.getInstance(application)
            collectoService.instance.add(collectoService.getUniqueCollectorRequest("/$id", {

                val col = Collector(
                    Integer.parseInt(it.get("id").toString()),
                    it.get("name").toString(),
                    it.get("telephone").toString(),
                    it.get("email").toString(), null, null, null
                )
                CacheManager.getInstance(application.applicationContext).addCollector(Integer.parseInt(id),col)
                cont.resume(col)
            }, {
                cont.resumeWithException(it)
            }))
        } else {
            cont.resume(potentialResp)
        }
    }

    suspend fun getCollectorAlbums(id: String) = suspendCoroutine<List<AlbumCollector>> { cont ->
        val collectoService = CollectoService.getInstance(application)
        collectoService.instance.add(collectoService.getCollectorDetailLists("/$id/albums", {
            val list = mutableListOf<AlbumCollector>()
            for (i in 0 until it.length()) {
                list.add(
                    AlbumCollector(
                        it.getJSONObject(i).get("price").toString(),
                        it.getJSONObject(i).get("status").toString(),
                        Integer.parseInt(it.getJSONObject(i).get("id").toString()),
                        Json.decodeFromString<AlbumCreate>(
                            it.getJSONObject(i).get("album").toString()
                        )

                    )

                )
            }
            cont.resume(list)
        }, { cont.resumeWithException(it) }))

    }

    suspend fun getCollectorFavoriteArtist(id: String) = suspendCoroutine<List<Artist>> { cont ->
        val collectoService = CollectoService.getInstance(application)
        collectoService.instance.add(collectoService.getCollectorDetailLists("/$id/performers", {
            val list = mutableListOf<Artist>()
            for (i in 0 until it.length()) {
                Log.d("Entro", it.getJSONObject(i).toString())

                list.add(
                    Artist(
                        Integer.parseInt(it.getJSONObject(i).get("id").toString()),
                        it.getJSONObject(i).get("name").toString(),
                        it.getJSONObject(i).get("image").toString(),
                        it.getJSONObject(i).get("description").toString(),
                        if (!it.getJSONObject(i).has("birthDate")) it.getJSONObject(i)
                            .get("creationDate").toString() else it.getJSONObject(i)
                            .get("birthDate").toString()
                    )
                )
            }
            cont.resume(list)
        }, { cont.resumeWithException(it) }))


    }

    suspend fun getCollectors() = suspendCoroutine<List<Collector>> { cont ->

        val collectoService = CollectoService.getInstance(application)
        collectoService.instance.add(collectoService.getCollectorsRequest({
            val list = mutableListOf<Collector>()
            for (i in 0 until it.length()) {
                Log.d("CollectorRepository", "cicly")

//                val listTrack: List<Track> = emptyList()
//                val listComments: List<Comentario> = emptyList()

                list.add(
                    Collector(
                        Integer.parseInt(it.getJSONObject(i).get("id").toString()),
                        it.getJSONObject(i).get("name").toString(),
                        it.getJSONObject(i).get("telephone").toString(),
                        it.getJSONObject(i).get("email").toString(),
                        null, null, null

                    )
                )
            }
            cont.resume(list.sortedBy { item -> item.name })
        }, {
            cont.resumeWithException(it)
        }))
    }


}