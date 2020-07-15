package com.gardener.uitestbasics.ui.movie

import android.os.Bundle
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.*
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.gardener.uitestbasics.R
import com.gardener.uitestbasics.factory.MovieFragmentFactory
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
class StarActorsFragmentTest {

    // Old one
    /*@Test
    fun test_isDirectorsListVisible() {

        // SETUP
        val starActors = arrayListOf("Dwayne Johnson", "Seann William Scott", "Rosario Dawson", "Christopher Walken")
        val fragmentFactory = MovieFragmentFactory()
        val bundle = Bundle()
        bundle.putStringArrayList("args_actors", starActors)

        val scenario = launchFragmentInContainer<StarActorsFragment>(
            fragmentArgs = bundle,
            factory = fragmentFactory
        )

        // Verify
        Espresso.onView(ViewMatchers.withId(R.id.star_actors_text))
            .check(
                ViewAssertions.matches(
                    ViewMatchers.withText(
                        StarActorsFragment.stringBuilderForStarActors(starActors)
                    )
                )
            )
    }*/

    @Test
    fun test_isDirectorsListVisible() {

        // GIVEN
        val actors = arrayListOf("Dwayne Johnson", "Seann William Scott", "Rosario Dawson", "Christopher Walken")

        val fragmentFactory = MovieFragmentFactory(null, null)
        val bundle = Bundle()
        bundle.putStringArrayList("args_actors", actors)
        val scenario = launchFragmentInContainer<StarActorsFragment>(
            fragmentArgs = bundle,
            factory = fragmentFactory
        )

        // Verify
        onView(ViewMatchers.withId(R.id.star_actors_text))
            .check(
                ViewAssertions.matches(
                    ViewMatchers.withText(
                        StarActorsFragment.stringBuilderForStarActors(
                            actors
                        )
                    )
                )
            )

    }
}