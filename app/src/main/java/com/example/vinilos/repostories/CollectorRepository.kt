package com.example.vinilos.repostories

import android.app.Application
import android.util.Log
import com.example.vinilos.model.Album
import com.example.vinilos.model.Collector
import com.example.vinilos.model.Comentario
import com.example.vinilos.model.Track
import com.example.vinilos.services.CollectoService
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

class CollectorRepository(private val application: Application) {

    init {
        Log.d("Start consume", "CollectorRepository")
    }

    suspend fun getCollectors() = suspendCoroutine<List<Collector>> { cont ->
        Log.d("CollectorRepository", "start")
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