package com.example.vinilos.services

import android.content.Context
import android.util.Log

import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import org.json.JSONArray
import org.json.JSONObject

class ArtistService constructor(context: Context) {

    var instance: RequestQueue = Volley.newRequestQueue(context.applicationContext)

    companion object {
        const val BASE_URL = "http://34.72.118.202:3000/musicians"
        var instance: ArtistService? = null
        fun getInstance(context: Context) = instance ?: synchronized(this) {
            instance ?: ArtistService(context).also {
                instance = it
            }
        }
    }

//    fun getUniqueCollectorRequest(
//        path: String, responseListener: Response.Listener<JSONObject>,
//        errorListener: Response.ErrorListener
//    ): JsonObjectRequest {
//
//        return JsonObjectRequest(
//            Request.Method.GET, CollectoService.BASE_URL + path, null,
//            responseListener, errorListener
//        )
//    }
//
//
//    fun getCollectorDetailLists(
//        path: String, responseListener: Response.Listener<JSONArray>,
//        errorListener: Response.ErrorListener
//    ): JsonArrayRequest {
//        return JsonArrayRequest(
//            Request.Method.GET,
//            CollectoService.BASE_URL + path,
//            null,
//            responseListener,
//            errorListener
//        )
//    }
    fun getUniqueArtist(    path:String,responseListener: Response.Listener<JSONObject>, errorListener: Response.ErrorListener):JsonObjectRequest{
    return JsonObjectRequest(
          Request.Method.GET, BASE_URL + path, null,
            responseListener, errorListener
     )
    }
    fun getArtistRequest(
        responseListener: Response.Listener<JSONArray>, errorListener: Response.ErrorListener
    ): JsonArrayRequest {
        return JsonArrayRequest(
            Request.Method.GET, BASE_URL, null,
            responseListener, errorListener
        )
    }

    fun udpateArtistAlbum(path: String, jsonArray: JSONArray,  responseListener: Response.Listener<JSONObject>,
                          errorListener: Response.ErrorListener
    ): JsonObjectCustomRequest {
        Log.d("Entro",jsonArray[0].toString())
    return  JsonObjectCustomRequest(Request.Method.PUT, BASE_URL+path,
        jsonArray,responseListener,errorListener)
    }


}