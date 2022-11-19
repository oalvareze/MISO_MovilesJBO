package com.example.vinilos.model

data class Collector ( val id: Int,
                       val name: String,
                       val telephone: String,
                       val email: String,
                       val comments: List<Comentario>?,
                       val favoritePerformers: List<Artist>?,
                       val collectorAlbums: List<AlbumCollector>?
)