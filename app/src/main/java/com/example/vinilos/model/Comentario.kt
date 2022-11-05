package com.example.vinilos.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Comentario(    val description:String,
                          val rating:String,
                          val albumId:Int
):Parcelable
