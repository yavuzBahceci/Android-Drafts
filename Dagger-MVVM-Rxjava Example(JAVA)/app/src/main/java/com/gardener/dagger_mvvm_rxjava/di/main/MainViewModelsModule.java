package com.gardener.dagger_mvvm_rxjava.di.main;

import androidx.lifecycle.ViewModel;

import com.gardener.dagger_mvvm_rxjava.di.ViewModelKey;
import com.gardener.dagger_mvvm_rxjava.ui.main.posts.PostsViewModel;
import com.gardener.dagger_mvvm_rxjava.ui.main.profile.ProfileViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class MainViewModelsModule {

    @Binds
    @IntoMap
    @ViewModelKey(ProfileViewModel.class)
    public abstract ViewModel bindProfileViewModel(ProfileViewModel viewModel);

    @Binds
    @IntoMap
    @ViewModelKey(PostsViewModel.class)
    public abstract ViewModel bindPostsViewModel(PostsViewModel viewModel);
}
