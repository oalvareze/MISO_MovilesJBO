package com.example.vinilos.model

import kotlinx.serialization.SerialName

@kotlinx.serialization.Serializable
@SerialName("album")
data class AlbumCollector(
    val price:String,
    val status:String,
    val id:Int,

    val album:AlbumCreate)
