package com.gardener.dagger_mvvm_rxjava.di.main;

import com.gardener.dagger_mvvm_rxjava.ui.main.posts.PostsFragment;
import com.gardener.dagger_mvvm_rxjava.ui.main.profile.ProfileFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class MainFragmentBuildersModule {

    @ContributesAndroidInjector
    abstract ProfileFragment contributeProfileFragment();

    @ContributesAndroidInjector
    abstract PostsFragment contributePostsFragment();
}
