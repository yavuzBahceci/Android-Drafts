package com.gardener.dagger_mvvm_rxjava.di.auth;

import androidx.lifecycle.ViewModel;

import com.gardener.dagger_mvvm_rxjava.di.ViewModelKey;
import com.gardener.dagger_mvvm_rxjava.ui.auth.AuthViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class AuthViewModelsModule {

    @Binds
    @IntoMap
    @ViewModelKey(AuthViewModel.class)
    public abstract ViewModel bindAuthViewModel(AuthViewModel viewModel);
}
