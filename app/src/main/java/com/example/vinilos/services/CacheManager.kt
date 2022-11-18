package com.example.vinilos.services

import android.content.Context
import android.util.LruCache
import com.example.vinilos.model.Album

class CacheManager(context: Context) {

    companion object {
        var instance: CacheManager? = null
        fun getInstance(context: Context) = instance ?: synchronized(this) {
            instance ?: CacheManager(context).also { instance = it }
        }
    }

    private var albums: LruCache<Int, Album> = LruCache(5)
    fun addAlbum(albumId: Int, album: Album) {
        if (albums[albumId] == null) {
            albums.put(albumId, album)
        }
    }

    fun getAlbum(albumId: Int): Album? {
        return if (albums[albumId] != null) albums[albumId]!! else null
    }

}