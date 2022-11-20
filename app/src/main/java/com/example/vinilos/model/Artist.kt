package com.example.vinilos.model
@kotlinx.serialization.Serializable
data class Artist(
    val id: Int,
    val name: String,
    val image: String,
    val description: String,
    val birthDate: String
)
