package com.gardener.uitestbasics.ui.movie

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.Espresso.pressBack
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.gardener.uitestbasics.R
import com.gardener.uitestbasics.data.FakeMovieData
import com.gardener.uitestbasics.ui.movie.MoviesListAdapter.MovieViewHolder
import com.gardener.uitestbasics.util.EspressoIdlingResourceRule2
import org.hamcrest.core.IsNot.not
import org.junit.FixMethodOrder
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.MethodSorters

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(AndroidJUnit4ClassRunner::class)
class MoviesListFragmentTest {

    @get: Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

    @get: Rule
    val espressoIdlingResourceRule = EspressoIdlingResourceRule2()
    /*// Idling for possible time delays.
    Created with rull class
    @Before
    fun registerIdlingResource() {
        IdlingRegistry.getInstance().register(EspressoIdlingResource.countingIdlingResource)
    }

    @After
    fun unregisterIdlingResource() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.countingIdlingResource)
    }*/

    // to test scrolling choose the last one
    val LIST_ITEM_IN_TEST = 4
    val MOVIE_IN_TEST = FakeMovieData.movies[LIST_ITEM_IN_TEST]

    /*
    * Recycler view comes into view
    * */
    @Test
    fun a_test_isListFragmentVisible_onAppLaunch() {

        onView(withId(R.id.recycler_view)).check(matches(isDisplayed()))

        onView(withId(R.id.progress_bar)).check(matches(not(isDisplayed())))
    }

    /*
    * Select list item, nav to detail fragment.
    * Correct item is in view.
    * */
    @Test
    fun test_selectListItem_isDetailFragmentVisible() {

        onView(withId(R.id.recycler_view))
            .perform(actionOnItemAtPosition<MovieViewHolder>(LIST_ITEM_IN_TEST, click()))
        onView(withId(R.id.movie_title)).check(matches(withText(MOVIE_IN_TEST.title)))
    }

    /*
    * Select list item
    * Nav to detail fragment
    * press back
    * */
    @Test
    fun test_backNavigation_toMovieListFragment() {

        onView(withId(R.id.recycler_view))
            .perform(actionOnItemAtPosition<MovieViewHolder>(LIST_ITEM_IN_TEST, click()))
        onView(withId(R.id.movie_title)).check(matches(withText(MOVIE_IN_TEST.title)))
        pressBack()

        onView(withId(R.id.recycler_view)).check(matches(isDisplayed()))
    }

    /*
    * select list item
    * nav to detail
    * select directors textview, nav to directors fragment
    * */
    @Test
    fun test_navDirectorsFragment_validateDirectorsList() {

        val directors = MOVIE_IN_TEST.directors
        val verifyDirectorsValue =
            directors?.let { DirectorsFragment.stringBuilderForDirectors(it) }
        onView(withId(R.id.recycler_view))
            .perform(actionOnItemAtPosition<MovieViewHolder>(LIST_ITEM_IN_TEST, click()))
        onView(withId(R.id.movie_title)).check(matches(withText(MOVIE_IN_TEST.title)))

        onView(withId(R.id.movie_directors)).perform(click())

        onView(withId(R.id.directors_text))
            .check(matches(withText(verifyDirectorsValue)))

    }

    /*
    * select list item, nav to StarActorsFragment
    * select star actors textview, nav to starActors fragment
    * */
    @Test
    fun test_navStarActorsFragment_validateActorsList() {

        val actors = MOVIE_IN_TEST.star_actors
        val verifyActorsValue = actors?.let { StarActorsFragment.stringBuilderForStarActors(it) }
        onView(withId(R.id.recycler_view))
            .perform(actionOnItemAtPosition<MovieViewHolder>(LIST_ITEM_IN_TEST, click()))
        onView(withId(R.id.movie_title)).check(matches(withText(MOVIE_IN_TEST.title)))

        onView(withId(R.id.movie_star_actors)).perform(click())

        onView(withId(R.id.star_actors_text))
            .check(matches(withText(verifyActorsValue)))

    }


}