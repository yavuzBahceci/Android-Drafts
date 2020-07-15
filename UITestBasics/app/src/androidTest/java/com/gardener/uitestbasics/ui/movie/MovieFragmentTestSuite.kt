package com.gardener.uitestbasics.ui.movie

import org.junit.runner.RunWith
import org.junit.runners.Suite

@RunWith(Suite::class)
@Suite.SuiteClasses(
    MovieDetailFragmentTest::class,
    DirectorsFragmentTest::class,
    MoviesListFragmentTest::class
)
class MovieFragmentTestSuite