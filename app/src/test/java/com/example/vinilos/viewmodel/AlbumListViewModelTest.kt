package com.example.vinilos.viewmodel

import android.app.Activity
import android.app.Application
import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.vinilos.model.Album
import com.example.vinilos.repostories.AlbumRepository
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


        val application = Mockito.mock(Application::class.java)
        albumRepository=mock(AlbumRepository::class.java)
        print(albumRepository)
        Mockito.`when`(albumRepository.getAlbums()).thenReturn(getAlbums())
        albumListViewModel= AlbumListViewModel(application,albumRepository)
        assertEquals(getAlbums()[0].albumId,albumListViewModel.albumsFiltered.value!![0].albumId)



    }
    fun getAlbums():List<Album> {
        return listOf<Album>((Album(1,"nombre","3","e","3","e","e", emptyList(), emptyList(),"3")))
    }


}