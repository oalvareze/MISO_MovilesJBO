package com.example.vinilos.services

import android.content.Context

import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import org.json.JSONArray
import org.json.JSONObject

class CollectoService constructor(context: Context) {

    var instance: RequestQueue = Volley.newRequestQueue(context.applicationContext)

    companion object {
        const val BASE_URL = "http://104.155.128.71:3000/collectors"
        var instance: CollectoService? = null
        fun getInstance(context: Context) = instance ?: synchronized(this) {
            instance ?: CollectoService(context).also {
                instance = it
            }
        }
    }
    fun getUniqueCollectorRequest(
        path: String, responseListener: Response.Listener<JSONObject>,
        errorListener: Response.ErrorListener
    ): JsonObjectRequest {

        return JsonObjectRequest(
            Request.Method.GET, CollectoService.BASE_URL + path, null,
            responseListener, errorListener
        )
    }


    fun getCollectorDetailLists(path: String,responseListener: Response.Listener<JSONArray>,
                                   errorListener: Response.ErrorListener): JsonArrayRequest{
        return JsonArrayRequest(Request.Method.GET, CollectoService.BASE_URL+path,null,responseListener,errorListener)
    }
    fun getCollectorsRequest(
        responseListener: Response.Listener<JSONArray>, errorListener: Response.ErrorListener
    ): JsonArrayRequest {
        return JsonArrayRequest(
            Request.Method.GET, BASE_URL, null,
            responseListener, errorListener
        )
    }


}