package com.gardener.uitestbasics.ui.movie

import android.os.Bundle
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.bumptech.glide.request.RequestOptions
import com.gardener.uitestbasics.R
import com.gardener.uitestbasics.data.DummyMovies.THE_RUNDOWN
import com.gardener.uitestbasics.data.Movie
import com.gardener.uitestbasics.data.source.MoviesRemoteDataSource
import com.gardener.uitestbasics.factory.MovieFragmentFactory
import io.mockk.every
import io.mockk.mockk
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
class MovieDetailFragmentTest {

    // Old one
    /*@Test
    fun test_isMovieDataVisible() {

        // SETUP
        val movie = THE_RUNDOWN
        val fragmentFactory = MovieFragmentFactory()
        val bundle = Bundle()
        bundle.putInt("movie_id", movie.id)

        val scenario = launchFragmentInContainer<MovieDetailFragment>(
            fragmentArgs = bundle,
            factory = fragmentFactory
        )

        onView(withId(R.id.movie_title)).check(matches(withText(movie.title)))
        onView(withId(R.id.movie_description)).check(matches(withText(movie.description)))

    }*/

    @Test
    fun test_isMovieDataVisible() {

        // SETUP
        val movieId = 1
        val title = "The Rundown"
        val description = "A tough aspiring chef is hired to bring home a mobster's son from the Amazon but " +
                "becomes involved in the fight against an oppressive town operator and the search " +
                "for a legendary treasure."
        val image = "https://nyc3.digitaloceanspaces.com/open-api-spaces/open-api-static/blog/1/The_Rundown-the_rundown.png"
        val movie = Movie(
            movieId,
            title,
            image,
            description,
            arrayListOf("R.J. Stewart", "James Vanderbilt"),
            arrayListOf("Dwayne Johnson", "Seann William Scott", "Rosario Dawson", "Christopher Walken")

        )

        // Mockito cannot directly mock final classes because of that use mock.io all . Kotlin classes are final as default
        val moviesDataSource = mockk<MoviesRemoteDataSource>()
        every {
            moviesDataSource.getMovie(movieId)
        } returns movie

        val requestOptions = RequestOptions()
            .placeholder(R.drawable.default_image)
            .error(R.drawable.default_image)

        // real movie fragment factory
        val fragmentFactory = MovieFragmentFactory(
            requestOptions,
            moviesDataSource
        )

        val bundle = Bundle()
        bundle.putInt("movie_id", movieId)

        val scenario = launchFragmentInContainer<MovieDetailFragment>(
            fragmentArgs = bundle,
            factory = fragmentFactory
        )

        // Verify
        onView(withId(R.id.movie_title)).check(matches(withText(title)))
        onView(withId(R.id.movie_description)).check(matches(withText(description)))

    }
}