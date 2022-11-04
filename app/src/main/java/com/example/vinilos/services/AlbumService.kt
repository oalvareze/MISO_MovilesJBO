package com.example.vinilos.services

import android.content.Context
import android.util.Log
import com.example.vinilos.model.Album
import com.example.vinilos.model.Comentario
import com.example.vinilos.model.Track

class AlbumService constructor(context: Context) {
    companion object {
        const val BASE_URL = "https://backvynils-job.herokuapp.com/";
        var instance: AlbumService? = null;
        fun getInstance(context: Context) = instance ?: synchronized(this) {
            instance ?: AlbumService(context).also {
                instance = it
            }
        }

    }

    fun getAlbum(
        albumId: Int,
        onComplete: (resp: Album) -> Unit
    ) {
        val images = listOf<String>(
            "https://cms-assets.tutsplus.com/cdn-cgi/image/width=850/uploads/users/114/posts/34296/final_image/Final-image.jpg",
            "https://mir-s3-cdn-cf.behance.net/project_modules/max_1200/602f4731226337.5646928c3633f.jpg"
        );

        onComplete(
            Album(
                albumId = albumId,
                name = "Album${albumId}",
                description = "ca",
                releaseDate = "c",
                cover = if (albumId % 2 == 0) images[0] else images[1],
                genre = "C",
                recordLabel = "r",
                tracks = listOf<Track>(Track(nombre = "Track1", duracion = "3:30")),comentarios = listOf<Comentario>(
                    Comentario(
                        description = "orem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled",
                        rating = "4.0",
                        albumId = 2
                    ),
                    Comentario(
                        description = "Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled",
                        rating = "4.0",
                        albumId = 2
                    ))
            ))
    }

    fun getAlbums(onComplete: (resp: List<Album>) -> Unit, onError: (resp: List<Album>) -> Unit) {
        val images = listOf<String>(
            "https://cms-assets.tutsplus.com/cdn-cgi/image/width=850/uploads/users/114/posts/34296/final_image/Final-image.jpg",
            "https://mir-s3-cdn-cf.behance.net/project_modules/max_1200/602f4731226337.5646928c3633f.jpg"
        );
        val list = mutableListOf<Album>()

        for (i in 0 until 10) {

            list.add(
                Album(
                    albumId = i,
                    name = "Album${i}",
                    description = "ca",
                    releaseDate = "c",
                    cover = if (i % 2 == 0) images[0] else images[1],
                    genre = "C",
                    recordLabel = "r",
                    tracks = listOf<Track>(Track(nombre = "Track1", duracion = "3:30")),comentarios = listOf<Comentario>(
                        Comentario(
                            description = "orem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled",
                            rating = "4.0",
                            albumId = 2
                        ),
                        Comentario(
                            description = "Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled",
                            rating = "4.0",
                            albumId = 2
                        ))
                )
            );
        }
        onComplete(list);
    }
}