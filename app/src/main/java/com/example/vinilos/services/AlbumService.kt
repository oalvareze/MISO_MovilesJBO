package com.example.vinilos.services

import android.content.Context
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.Response.ErrorListener
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import org.json.JSONArray
import org.json.JSONObject

class AlbumService constructor(context: Context) {

    var instance: RequestQueue = Volley.newRequestQueue(context.applicationContext)

    companion object {
        const val BASE_URL = "https://backvynils-job.herokuapp.com/"
        var instance: AlbumService? = null
        fun getInstance(context: Context) = instance ?: synchronized(this) {
            instance ?: AlbumService(context).also {
                instance = it
            }
        }
    }

    fun getAlbumsRequest(
        path: String, responseListener: Response.Listener<JSONArray>,
        errorListener: Response.ErrorListener
    ): JsonArrayRequest {
        return JsonArrayRequest(
            Request.Method.GET, BASE_URL + path, null,
            responseListener, errorListener
        )
    }

    fun getUniqueAlbumsRequest(
        path: String, responseListener: Response.Listener<JSONObject>,
        errorListener: Response.ErrorListener
    ): JsonObjectRequest {
        return JsonObjectRequest(
            Request.Method.GET, BASE_URL + path, null,
            responseListener, errorListener
        )
    }

    fun createAlbum(
        path: String, request: JSONObject,
        responseListener: Response.Listener<JSONObject>,
        errorListener: ErrorListener
    ): JsonObjectRequest {
        return JsonObjectRequest(
            Request.Method.POST,
            BASE_URL + path,
            request,
            responseListener,
            errorListener
        )
    }

//    fun getAlbum(
//        albumId: Int,
//        onComplete: (resp: Album) -> Unit
//    ) {
//        val images = listOf<String>(
//            "https://cms-assets.tutsplus.com/cdn-cgi/image/width=850/uploads/users/114/posts/34296/final_image/Final-image.jpg",
//            "https://mir-s3-cdn-cf.behance.net/project_modules/max_1200/602f4731226337.5646928c3633f.jpg"
//        );
//
//        onComplete(
//            Album(
//                albumId = albumId,
//                name = "Album${albumId}",
//                description = "ca",
//                releaseDate = "c",
//                cover = if (albumId % 2 == 0) images[0] else images[1],
//                genre = "C",
//                recordLabel = "r",
//                tracks = listOf<Track>(Track(nombre = "Track1", duracion = "3:30")),comentarios = listOf<Comentario>(
//                    Comentario(
//                        description = "orem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled",
//                        rating = "4.0",
//                        albumId = 2
//                    ),
//                    Comentario(
//                        description = "Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled",
//                        rating = "4.0",
//                        albumId = 2
//                    ))
//            ))
//    }

//    fun getAlbums(onComplete: (resp: List<Album>) -> Unit, onError: (resp: List<Album>) -> Unit) {
//        val images = listOf<String>(
//            "https://cms-assets.tutsplus.com/cdn-cgi/image/width=850/uploads/users/114/posts/34296/final_image/Final-image.jpg",
//            "https://mir-s3-cdn-cf.behance.net/project_modules/max_1200/602f4731226337.5646928c3633f.jpg"
//        );
//        val list = mutableListOf<Album>()
//
//        for (i in 0 until 10) {
//
//            list.add(
//                Album(
//                    albumId = i,
//                    name = "Album${i}",
//                    description = "ca",
//                    releaseDate = "c",
//                    cover = if (i % 2 == 0) images[0] else images[1],
//                    genre = "C",
//                    recordLabel = "r",
//                    tracks = listOf<Track>(Track(nombre = "Track1", duracion = "3:30")),comentarios = listOf<Comentario>(
//                        Comentario(
//                            description = "orem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled",
//                            rating = "4.0",
//                            albumId = 2
//                        ),
//                        Comentario(
//                            description = "Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled",
//                            rating = "4.0",
//                            albumId = 2
//                        ))
//                )
//            );
//        }
//        onComplete(list);
//    }
}