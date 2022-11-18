package com.example.vinilos.services

import android.content.Context
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import org.json.JSONArray

class CollectoService constructor(context: Context) {

    var instance: RequestQueue = Volley.newRequestQueue(context.applicationContext)

    companion object {
        const val BASE_URL = "https://backvynils-job.herokuapp.com/collectors"
        var instance: CollectoService? = null
        fun getInstance(context: Context) = instance ?: synchronized(this) {
            instance ?: CollectoService(context).also {
                instance = it
            }
        }
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