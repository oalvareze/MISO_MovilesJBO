package com.example.vinilos

import androidx.lifecycle.Lifecycle
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.ViewInteraction
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
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
    fun `validate filter for gender`() {
        val gender = "Salsa"
        val album = "Buscando América"
        Thread.sleep(800)
        onView(withId(R.id.genresSpinner))
            .perform(click())
        Thread.sleep(800)
        onView(withText(gender)).perform(click())
        onView(withText(album)).check(matches(isDisplayed()))
        onView(withText(album)).perform(click())
        Thread.sleep(500)
        onView(withText(album)).check(matches(isDisplayed()))
    }
    @Test
    fun `validate tracks`() {
        val gender = "Salsa"
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
    fun `validate comments`() {
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
}