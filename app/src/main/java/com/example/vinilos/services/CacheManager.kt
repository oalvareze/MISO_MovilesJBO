package com.example.vinilos.services

import android.content.Context
import android.util.LruCache
import com.example.vinilos.model.Album
import com.example.vinilos.model.Artist
import com.example.vinilos.model.ArtistDetail
import com.example.vinilos.model.Collector

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

    private var collectors: LruCache<Int, Collector> = LruCache(5)
    fun addCollector(collectorId: Int, collector: Collector){
        if (collectors[collectorId] == null) {
            collectors.put(collectorId, collector)
        }
    }
    fun getCollector(id:Int):Collector?{
        return  if (collectors[id] != null) collectors[id]!! else null
    }
    fun getAlbum(albumId: Int): Album? {
        return if (albums[albumId] != null) albums[albumId]!! else null
    }
    private  var artists:LruCache<Int,ArtistDetail> =LruCache(5)
 fun addArtist(id:Int,artist:ArtistDetail){
     if(artists[id]==null){
         artists.put(id,artist)
     }
 }
    fun updateArtist(id: Int,artist: ArtistDetail){
        artists.put(id,artist)
    }
    fun getArtist(id:Int):ArtistDetail?{
        return if(artists[id]!=null) artists[id]!!else null
    }
} 