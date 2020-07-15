package com.gardener.dagger_mvvm_rxjava.di;

import androidx.lifecycle.ViewModelProvider;

import com.gardener.dagger_mvvm_rxjava.viewmodels.ViewModelProviderFactory;

import dagger.Binds;
import dagger.Module;

@Module
public abstract class ViewModelFactoryModule {

    @Binds
    public abstract ViewModelProvider.Factory bindViewModelFactory(ViewModelProviderFactory viewModelProviderFactory);


}
