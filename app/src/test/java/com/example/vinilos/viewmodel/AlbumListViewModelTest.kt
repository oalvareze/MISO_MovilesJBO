package com.example.vinilos.viewmodel

import android.app.Activity
import android.app.Application
import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.vinilos.model.Album
import com.example.vinilos.repostories.AlbumRepository
import io.mockk.every
import io.mockk.mockk
import net.bytebuddy.matcher.ElementMatchers.any
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock
import org.mockito.junit.MockitoJUnitRunner



class AlbumListViewModelTest{
    private lateinit var albumListViewModel: AlbumListViewModel

    private  lateinit var albumRepository: AlbumRepository
    @Before
    fun setUp(){




    }

    @get:Rule
    val rule = InstantTaskExecutorRule()
    @Test
    fun `Should create a list`(){


        val application = mockk<Application>()
        albumRepository=mockk<AlbumRepository>()
        every{albumRepository.getAlbums(any(),any())} answers {             firstArg<(List<Album>) -> Unit>().invoke(getAlbums())
        }

        albumListViewModel= AlbumListViewModel(application,albumRepository)
        println(albumListViewModel.albumsFiltered.value)



    }
    fun getAlbums():List<Album> {
        return listOf<Album>((Album(1,"nombre","3","e","3","e","e", emptyList(), emptyList(),"3")))
    }


}