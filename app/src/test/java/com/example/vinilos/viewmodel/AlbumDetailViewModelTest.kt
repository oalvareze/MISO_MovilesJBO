package com.example.vinilos.viewmodel

import android.app.Application
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.android.volley.VolleyError
import com.example.vinilos.model.Album
import com.example.vinilos.model.Comentario
import com.example.vinilos.model.Track
import com.example.vinilos.repostories.AlbumRepository
import io.mockk.every
import io.mockk.mockk
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.w3c.dom.Comment

class AlbumDetailViewModelTest() {
    private lateinit var albumRepository: AlbumRepository

    @Before
    fun setUp() {
        albumRepository = mockk<AlbumRepository>()

    }

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Test
    fun `There should have an album with comments and tracks`() {
        val application = mockk<Application>()
        albumRepository = mockk<AlbumRepository>()
        every { albumRepository.getUniqueAlbum(any(), any(), any()) } answers {
            secondArg<(Album) -> Unit>().invoke(
                Album(
                    1,
                    "album",
                    "c",
                    "3",
                    "e",
                    "e",
                    "e",
                    listOf<Track>(Track("song", "3")),
                    listOf<Comentario>(Comentario("c", "c", 2)),
                    "c"
                )
            )
        }

        var albumDetailViewModel =AlbumDetailViewModel(application,2,albumRepository)

        assertEquals(albumDetailViewModel.album.value!!.name, "album")


    }
    fun `There sh2ould have an album with comments and tracks`() {
        val application = mockk<Application>()
        albumRepository = mockk<AlbumRepository>()
        every { albumRepository.getUniqueAlbum(any(), any(), any()) } answers {
            thirdArg<(VolleyError) -> Unit>().invoke(
                VolleyError()
            )
        }

        var albumDetailViewModel =AlbumDetailViewModel(application,2,albumRepository)

        assertEquals(albumDetailViewModel.album.value, null)


    }
}