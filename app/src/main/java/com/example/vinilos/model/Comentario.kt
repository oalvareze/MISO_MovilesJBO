package com.example.vinilos.model


@kotlinx.serialization.Serializable
data class Comentario(    val description:String,
                          val rating:String,
                          val albumId:Int
)
