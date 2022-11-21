    package com.example.vinilos.viewmodel

import android.app.Application
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.android.volley.VolleyError
import com.example.vinilos.model.Album
import com.example.vinilos.model.Comentario
import com.example.vinilos.model.Track
import com.example.vinilos.repostories.AlbumRepository
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.*
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestWatcher
import org.junit.runner.Description

import org.w3c.dom.Comment

@ExperimentalCoroutinesApi
class MainCoroutineRule(private val dispatcher: TestDispatcher = StandardTestDispatcher()) :
    TestWatcher() {

    override fun starting(description: Description?) {
        super.starting(description)
        Dispatchers.setMain(dispatcher)
    }

    override fun finished(description: Description?) {
        super.finished(description)
        Dispatchers.resetMain()
    }
}
@OptIn(ExperimentalCoroutinesApi::class)
class AlbumDetailViewModelTest() {
    private lateinit var albumRepository: AlbumRepository

    @Before
    fun setUp() {
        albumRepository = mockk<AlbumRepository>()

    }
    @ExperimentalCoroutinesApi
    @get:Rule
    val mainCoroutineRule = MainCoroutineRule()
    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Test
    fun `Check album name`()  = runTest{

        val application = mockk<Application>()
        albumRepository = mockk<AlbumRepository>()
        coEvery { albumRepository.getUniqueAlbum(any()) } returns

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


        var albumDetailViewModel =AlbumDetailViewModel(application,2,albumRepository)
        launch {

        albumDetailViewModel.refreshDataFromNetwork() }

                advanceUntilIdle()


        assertEquals(albumDetailViewModel.album.value!!.name, "album")
        assertEquals(albumDetailViewModel.album.value!!.comentarios.size,1)
        assertEquals(albumDetailViewModel.album.value!!.tracks.size,1)


    }
    @Test
    fun `Album value must be null`() {
        val application = mockk<Application>()
        albumRepository = mockk<AlbumRepository>()
        coEvery { albumRepository.getUniqueAlbum(any()) } coAnswers  {

               throw VolleyError()

        }

        var albumDetailViewModel =AlbumDetailViewModel(application,2,albumRepository)

        assertEquals(albumDetailViewModel.album.value, null)


    }
}