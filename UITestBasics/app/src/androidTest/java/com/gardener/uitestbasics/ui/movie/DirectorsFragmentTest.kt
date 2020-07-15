package com.gardener.uitestbasics.ui.movie

import android.os.Bundle
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.gardener.uitestbasics.R
import com.gardener.uitestbasics.factory.MovieFragmentFactory
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
class DirectorsFragmentTest {

    // Old Test
    /*@Test
    fun test_isDirectorsListVisible() {

        // SETUP
        val directors = arrayListOf("R.J. Stewart", "James Vanderbilt")
        val fragmentFactory = MovieFragmentFactory()
        val bundle = Bundle()
        bundle.putStringArrayList("args_directors", directors)

        val scenario = launchFragmentInContainer<DirectorsFragment>(
            fragmentArgs = bundle,
            factory = fragmentFactory
        )

        // Verify
        onView(withId(R.id.directors_text))
            .check(matches(withText(
            DirectorsFragment.stringBuilderForDirectors(directors)
        )))
    }*/

    @Test
    fun test_isDirectorsListVisible() {

        // GIVEN
        val directors = arrayListOf("R.J. Stewart", "James Vanderbilt")
        val fragmentFactory = MovieFragmentFactory(null, null)
        val bundle = Bundle()
        bundle.putStringArrayList("args_directors", directors)
        val scenario = launchFragmentInContainer<DirectorsFragment>(
            fragmentArgs = bundle,
            factory = fragmentFactory
        )

        // Verify
        onView(withId(R.id.directors_text))
            .check(matches(withText(DirectorsFragment.stringBuilderForDirectors(directors))))

    }
}