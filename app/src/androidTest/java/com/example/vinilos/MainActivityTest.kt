package com.example.vinilos

import android.view.View
import androidx.lifecycle.Lifecycle
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.RootMatchers.withDecorView
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.example.vinilos.view.albumlist.AlbumListAdapter
import com.example.vinilos.view.collector.CollectorListAdapter
import org.hamcrest.CoreMatchers.*
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
@LargeTest
class MainActivityTest {

    private lateinit var scenario: ActivityScenario<MainActivity>

    @Before
    fun setUp() {
        scenario = ActivityScenario.launch(MainActivity::class.java)
        scenario.moveToState(Lifecycle.State.RESUMED)
    }

    @Test
    fun `validate_filter_for_gender`() {
        val gender = "Salsa"
        val album = "Buscando América"
        Thread.sleep(800)
        onView(withId(R.id.genresSpinner))
            .perform(click())
        Thread.sleep(800)
        onView(withText(gender)).perform(click())
        onView(withText(album)).check(matches(isDisplayed()))
        onView(withText(album)).perform(click())
        Thread.sleep(800)
        onView(withText(album)).check(matches(isDisplayed()))
    }
    @Test
    fun `validate_tracks`() {

        val album = "Buscando América"


        Thread.sleep(800)
        onView(withText(album)).check(matches(isDisplayed()))
        onView(withText(album)).perform(click())
        Thread.sleep(500)
        onView(withText(album)).check(matches(isDisplayed()))
        onView(withText("Decisiones")).check(matches(isDisplayed()))
        Thread.sleep(500)

        Espresso.pressBack()
        Thread.sleep(500);
        onView(withText("A Day at the Races")).check(matches(isDisplayed()))
        onView(withText("A Day at the Races")).perform(click())
        Thread.sleep(500)
        var textView= Espresso.onView(ViewMatchers.withId(R.id.tracksMensaje))
        textView.check(matches(isDisplayed()))

    }
    @Test
    fun `validate_comments`() {
        val gender = "Salsa"
        val album = "Buscando América"


        Thread.sleep(1000)
        onView(withText(album)).check(matches(isDisplayed()))
        onView(withText(album)).perform(click())
        Thread.sleep(500)
        onView(withText(album)).check(matches(isDisplayed()))
        onView(withText("COMENTARIOS")).perform(click())
        Thread.sleep(500)

        onView(withText("Descripcion")).check(matches(isDisplayed()))
        Espresso.pressBack()
        Thread.sleep(500)
        onView(withText("A Day at the Races")).check(matches(isDisplayed()))
        onView(withText("A Day at the Races")).perform(click())
        Thread.sleep(500)
        onView(withText("COMENTARIOS")).perform(click())
        Thread.sleep(500)
        var textView= Espresso.onView(ViewMatchers.withId(R.id.mensajeComentarios))
        textView.check(matches(isDisplayed()))

    }
    @Test
    fun `validate_collectors`(){
        Thread.sleep(1000)
        onView(withText("COLECCIONISTAS")).check(matches(isDisplayed()))
        onView(withText("COLECCIONISTAS")).perform(click())
        Thread.sleep(2000)
        onView(withText("Jaime Monsalve")).check(matches(isDisplayed()))

    }
    @Test
    fun `validate_collectors_search`(){
        Thread.sleep(1000)
        onView(withText("COLECCIONISTAS")).check(matches(isDisplayed()))
        onView(withText("COLECCIONISTAS")).perform(click())
        Thread.sleep(2000)
        onView(withText("Jaime Monsalve")).check(matches(isDisplayed()))
        onView(ViewMatchers.withId(R.id.inputSearch)).perform(click()).perform(typeText("Ma"))
        Thread.sleep(1000)
        onView(withText("Manolo Bellon")).check(matches(isDisplayed()))
    }
    @Test
    fun `validate_collectors_detail`(){
        Thread.sleep(1000)
        onView(withText("COLECCIONISTAS")).check(matches(isDisplayed()))
        onView(withText("COLECCIONISTAS")).perform(click())
        Thread.sleep(2000)
        onView(withText("Jaime Monsalve")).check(matches(isDisplayed()))
        onView(withText("Jaime Monsalve")).perform(click())
        Thread.sleep(2000)
        onView(withText("3012357936")).check(matches(isDisplayed()))

    }
    @Test
    fun `validate_collectors_favorite_performer`(){
        Thread.sleep(1000)
        onView(withText("COLECCIONISTAS")).check(matches(isDisplayed()))
        onView(withText("COLECCIONISTAS")).perform(click())
        Thread.sleep(2000)
        onView(withText("Jaime Monsalve")).check(matches(isDisplayed()))
        onView(withText("Jaime Monsalve")).perform(click())
        Thread.sleep(2000)
        onView(withText("Queen")).check(matches(isDisplayed()))

    }
    @Test
    fun `validate_collectors_album`(){
        Thread.sleep(1000)
        onView(withText("COLECCIONISTAS")).check(matches(isDisplayed()))
        onView(withText("COLECCIONISTAS")).perform(click())
        Thread.sleep(2000)
        onView(withText("Jaime Monsalve")).check(matches(isDisplayed()))
        onView(withText("Jaime Monsalve")).perform(click())
        Thread.sleep(2000)
        onView(withText("ALBUMS")).perform(click())
        Thread.sleep(1000)
        onView(withText("$25")).check(matches(isDisplayed()))
        onView(withId(R.id.albumCollectorRV)).perform(RecyclerViewActions.actionOnItemAtPosition<CollectorListAdapter.CollectionListViewHolder>(0,customAction(R.id.detailButtonCollector)))
        Thread.sleep(1000)
        onView(withText("Poeta del pueblo")).check(matches(isDisplayed()))
    }
    @Test
    fun `validate_create_album_with_empty_fields`(){
        Thread.sleep(1000)
        onView(withId(R.id.albumFloatingActionButton)).perform(click())
        Thread.sleep(1000)
        onView(withId(R.id.creatAlbumButton)).perform(click())
        Thread.sleep(500)
        onView(withText("No todos los campos han sido llenados")).check(matches(isDisplayed()))
    }
    @Test
    fun `validate_create_album_with_invalid_url`(){
        Thread.sleep(1000)
        onView(withId(R.id.albumFloatingActionButton)).perform(click())
        Thread.sleep(1000)
        onView(withId(R.id.editTextAlbumName)).perform(click()).perform(replaceText("Nombre"))
        Thread.sleep(500)
        onView(withId(R.id.editTextAlbumCover)).perform(click()).perform(typeText("Nombre"))
        Thread.sleep(500)
        onView(withId(R.id.editTextDescription)).perform(click()).perform(typeText("Descripcion"))
        Thread.sleep(500)

        onView(ViewMatchers.isRoot()).perform(ViewActions.closeSoftKeyboard())
        Thread.sleep(500)
        onView(withId(R.id.editTextGenre)).perform(click()).perform(typeText("1990-04-20"))
        Thread.sleep(100)

        onView(ViewMatchers.isRoot()).perform(ViewActions.closeSoftKeyboard())
        Thread.sleep(1000)
        onView(withId(R.id.creatAlbumButton)).perform(click())

        Thread.sleep(500)
        onView(withText("Link no valido")).check(matches(isDisplayed()))
    }
    @Test
    fun `validate_create_album_with_invalid_date`() {
        Thread.sleep(1000)
        onView(withId(R.id.albumFloatingActionButton)).perform(click())
        Thread.sleep(1000)
        onView(withId(R.id.editTextAlbumName)).perform(click()).perform(replaceText("Nombre"))
        Thread.sleep(500)
        onView(withId(R.id.editTextAlbumCover)).perform(click())
            .perform(replaceText("https://upload.wikimedia.org/wikipedia/en/thumb/e/e6/Stadiumarcadium.jpg/220px-Stadiumarcadium.jpg"))
        Thread.sleep(500)
        onView(withId(R.id.editTextDescription)).perform(click()).perform(typeText("Descripcion"))
        Thread.sleep(500)

        onView(ViewMatchers.isRoot()).perform(ViewActions.closeSoftKeyboard())
        Thread.sleep(500)
        onView(withId(R.id.editTextGenre)).perform(click()).perform(typeText("cdas/04-35"))
        Thread.sleep(500)
        onView(ViewMatchers.isRoot()).perform(ViewActions.closeSoftKeyboard())
        Thread.sleep(500)
        onView(withId(R.id.creatAlbumButton)).perform(click())

        Thread.sleep(500)
        onView(withText("Formato de fecha invalido")).check(matches(isDisplayed()))
        Thread.sleep(500)
        onView(withId(R.id.editTextGenre)).perform(click()).perform(clearText()).perform(typeText("1990-04-35"))
        Thread.sleep(1000)
        onView(ViewMatchers.isRoot()).perform(ViewActions.closeSoftKeyboard())
    Thread.sleep(1000)
        onView(withId(R.id.creatAlbumButton)).perform(click())

        Thread.sleep(500)
        onView(withText("Fecha invalida")).check(matches(isDisplayed()))

    }
    @Test
    fun `validate_create_album`() {
        Thread.sleep(2000)
        onView(withId(R.id.albumFloatingActionButton)).perform(click())
        Thread.sleep(1000)
        onView(withId(R.id.editTextAlbumName)).perform(replaceText("Stadium Arcadium"))
        Thread.sleep(500)
        onView(withId(R.id.editTextAlbumCover)).perform(click())
            .perform(replaceText("https://upload.wikimedia.org/wikipedia/en/thumb/e/e6/Stadiumarcadium.jpg/220px-Stadiumarcadium.jpg"))
        Thread.sleep(1000)
        onView(withId(R.id.editTextDescription)).perform(click()).perform(replaceText("Estadium Arcadium es el noveno álbum de estudio del grupo de rock estadounidense Red Hot Chili Peppers, lanzado durante el mes de mayo de 2006."))
        Thread.sleep(500)
        onView(ViewMatchers.isRoot()).perform(ViewActions.closeSoftKeyboard())
        Thread.sleep(500)

        onView(withId(R.id.editTextGenre)).perform(click()).perform(replaceText("2006-05-05"))
        Thread.sleep(500)
        onView(ViewMatchers.isRoot()).perform(ViewActions.closeSoftKeyboard())
        Thread.sleep(500)
        Thread.sleep(1000)
        onView(withText("Classical")).perform(click())

        onView(withText("Rock")).perform(click())

        onView(withId(R.id.creatAlbumButton)).perform(click())


        Thread.sleep(1000)
        onView(withId(R.id.genresSpinner))
            .perform(click())

        onView(withText("Rock")).perform(click())
        Thread.sleep(800)
        Thread.sleep(1000)
        onView(withId(R.id.albumListRV)).perform(
            RecyclerViewActions.scrollToLastPosition<AlbumListAdapter.AlbumListViewHolder>())
        Thread.sleep(1000)
        onView(allOf(withText("Stadium Arcadium"),isDisplayed()))
    }

    fun customAction(id: Int): ViewAction {
        return object : ViewAction {
            override fun getConstraints(): org.hamcrest.Matcher<View>? {
                return null
            }

            override fun getDescription(): String {
                return "Action Description"
            }

            override fun perform(uiController: UiController, view: View) {
                val v = view.findViewById<View>(id) as View
                v.performClick()
            }
        }
    }
}