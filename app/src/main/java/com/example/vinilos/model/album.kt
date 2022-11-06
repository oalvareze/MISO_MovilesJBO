package com.example.vinilos.model



data class Album(      val albumId:Int,
                       val name:String,
                       val cover:String,
                       val releaseDate:String,
                       val description:String,
                       val genre:String,
                       val recordLabel:String,
                       val tracks:List<Track>,
                       val comentarios:List<Comentario>,
                       val artist:String,
                       )
