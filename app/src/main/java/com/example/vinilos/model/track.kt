package com.example.vinilos.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Track(
    val nombre:String,
    val duracion:String
):Parcelable
