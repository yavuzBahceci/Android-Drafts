package com.gardener.uitestbasics.ui.movie

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.Espresso.pressBack
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.gardener.uitestbasics.R
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
class MovieNavigationTest {

    @Test
    fun testMovieFragmentsNavigation() {

        // SETUP
        val activityScope = ActivityScenario.launch(MainActivity::class.java)

        // Nav to directors fragment
        onView(withId(R.id.movie_directors)).perform(click())

        // Verify
        onView(withId(R.id.fragment_movie_directors_parent)).check(matches(isDisplayed()))

        // Navigate back
        pressBack()

        // Verify
        onView(withId(R.id.fragment_movie_detail_parent)).check(matches(isDisplayed()))

        // Nav star actors fragment
        onView(withId(R.id.movie_star_actors)).perform(click())

        // Verify
        onView(withId(R.id.fragment_movie_star_actors_parent)).check(matches(isDisplayed()))
    }
}