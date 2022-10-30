package com.example.vinilos.services

import android.content.Context
import android.util.Log
import com.example.vinilos.model.Album

class AlbumService constructor(context:Context) {
    companion object{
        const val BASE_URL="https://backvynils-job.herokuapp.com/";
        var instance: AlbumService?=null;
        fun getInstance(context: Context)=instance ?: synchronized(this) {
            instance ?: AlbumService(context).also {
                instance = it
            }
        }
    }
    fun getAlbums(onComplete:(resp:List<Album>)->Unit,onError:(resp:List<Album>)->Unit){
        val images= listOf<String>("https://cms-assets.tutsplus.com/cdn-cgi/image/width=850/uploads/users/114/posts/34296/final_image/Final-image.jpg","https://mir-s3-cdn-cf.behance.net/project_modules/max_1200/602f4731226337.5646928c3633f.jpg");
        val list= mutableListOf<Album>()
        Log.d("Entro","AQUIII")
        for (i in 0 until 10){

            list.add(Album(albumId = i, name = "Album${i}", description = "ca", releaseDate = "c", cover = if (i%2==0)images[0]else images[1], genre = "C", recordLabel = "r"));
        }
        onComplete(list);
    }
}