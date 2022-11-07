package com.example.vinilos.viewmodel

import android.app.Activity
import android.app.Application
import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.android.volley.NetworkResponse
import com.android.volley.VolleyError
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


class AlbumListViewModelTest {


    private lateinit var albumRepository: AlbumRepository

    @Before
    fun setUp() {
        albumRepository = mockk<AlbumRepository>()

    }

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Test
    fun `The first element should have the name firstAlbum`() {


        val application = mockk<Application>()
        albumRepository = mockk<AlbumRepository>()
        every { albumRepository.getAlbums(any(), any()) } answers {
            firstArg<(List<Album>) -> Unit>().invoke(getAlbums())
        }

       var  albumListViewModel = AlbumListViewModel(application, albumRepository)

        assertEquals(albumListViewModel.albumsFiltered.value!![0].name,"firstAlbum")



    }
    @Test
    fun `There should be 3 genres`(){
        val application = mockk<Application>()
        albumRepository = mockk<AlbumRepository>()
        every { albumRepository.getAlbums(any(), any()) } answers {
            firstArg<(List<Album>) -> Unit>().invoke(listOf<Album>(
                (Album(
                    1,
                    "firstAlbum",
                    "3",
                    "e",
                    "3",
                    "1",
                    "e",
                    emptyList(),
                    emptyList(),
                    "3"
                )),Album(
                    1,
                    "secondAlbum",
                    "3",
                    "e",
                    "3",
                    "2",
                    "e",
                    emptyList(),
                    emptyList(),
                    "3"
                )
            ))
        }

        var  albumListViewModel = AlbumListViewModel(application, albumRepository
        )
        println(albumListViewModel.albumsFiltered.value)
        albumListViewModel.fillGenres()
        assertEquals( albumListViewModel.genres.value!!.size,3)
        assertEquals(albumListViewModel.loading.value,false)
    }
    @Test
    fun `It should filter the albums`(){
        val application = mockk<Application>()
        albumRepository = mockk<AlbumRepository>()
        every { albumRepository.getAlbums(any(), any()) } answers {
            firstArg<(List<Album>) -> Unit>().invoke(listOf<Album>(
                (Album(
                    1,
                    "firstAlbum",
                    "3",
                    "e",
                    "3",
                    "1",
                    "e",
                    emptyList(),
                    emptyList(),
                    "3"
                )),Album(
                    1,
                    "secondAlbum",
                    "3",
                    "e",
                    "3",
                    "2",
                    "e",
                    emptyList(),
                    emptyList(),
                    "3"
                )
            ))
        }

        var  albumListViewModel = AlbumListViewModel(application, albumRepository
        )
        println(albumListViewModel.albumsFiltered.value)
        albumListViewModel.fillGenres()
        assertEquals(albumListViewModel.albumsFiltered.value!![0].name,"firstAlbum")
        albumListViewModel.getAlbumFiltered("2")
        assertEquals(albumListViewModel.albumsFiltered.value!![0].name,"secondAlbum")
        albumListViewModel.getAlbumFiltered("Generos")
        assertEquals(albumListViewModel.albumsFiltered.value!![0].name,"firstAlbum")

    }
    @Test
    fun `It shouldnt have more than 1 genre`(){

        val application = mockk<Application>()
        albumRepository = mockk<AlbumRepository>()
        every { albumRepository.getAlbums(any(), any()) } answers {
            secondArg<(VolleyError) -> Unit>().invoke(VolleyError())

        }

        var  albumListViewModel = AlbumListViewModel(application, albumRepository
        )
        var yo=AlbumListViewModel.Factory(application, albumRepository)
        albumListViewModel.fillGenres()
        assertEquals(albumListViewModel.genres.value!!.size,1)

    }
    fun getAlbums(): List<Album> {
        return listOf<Album>(
            (Album(
                1,
                "firstAlbum",
                "3",
                "e",
                "3",
                "e",
                "e",
                emptyList(),
                emptyList(),
                "3"
            ))
        )
    }


}