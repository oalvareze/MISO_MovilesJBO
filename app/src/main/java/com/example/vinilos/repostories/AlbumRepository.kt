package com.example.vinilos.repostories

import android.app.Application
import android.util.Log
import com.android.volley.VolleyError
import com.example.vinilos.model.Album
import com.example.vinilos.model.Comentario
import com.example.vinilos.model.Track
import com.example.vinilos.services.AlbumService
import org.json.JSONArray

class AlbumRepository(private val application: Application) {
    fun getAlbums(callback: (List<Album>) -> Unit, onError: (VolleyError) -> Unit) {

        val albumService=AlbumService.getInstance(application)
        albumService.instance.add(albumService.getAlbumsRequest("albums", {

            val list = mutableListOf<Album>()

            for (i in 0 until it.length()) {

                val listTrack: List<Track> = emptyList()
                val listComments: List<Comentario> = emptyList()

                list.add(
                    Album(
                        Integer.parseInt(it.getJSONObject(i).get("id").toString()), it.getJSONObject(i).get("name").toString(), it.getJSONObject(i).get("cover").toString(),
                        "a,", "a", it.getJSONObject(i).get("genre").toString(), "uno", listTrack,
                        listComments,"Artista"
                    )
                )
            }
            callback(list)
        }, onError))
    }
    fun getUniqueAlbum(url: String  ,callback: (Album) -> Unit, onError: (VolleyError) -> Unit){
        val albumService=AlbumService.getInstance(application)

     albumService.instance.add(albumService.getUniqueAlbumsRequest(url,{

            val listTrack: List<Track> = getTrack(it.getJSONArray("tracks"))
            val listComments: List<Comentario> = getComments(it.getJSONArray("comments"))
            val artist=getArtist(it.getJSONArray("performers"))

         callback(Album(
                it.get("id").toString().toInt(),
                it.get("name").toString(),
                it.get("cover").toString(),
                it.get("releaseDate").toString().split("T").toTypedArray().get(0),
                it.get("description").toString(),
               it.get("genre").toString(),
                it.get("recordLabel").toString(),
                listTrack,
                listComments,artist
            ))},onError))
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
    private fun getArtist(response: JSONArray):String{
        if(response.length()>0){
            return  response.getJSONObject(0).get("name").toString()
        }
        else
            return "Artista"
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


