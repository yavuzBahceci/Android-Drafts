package com.gardener.dagger_mvvm_rxjava.di;

import com.gardener.dagger_mvvm_rxjava.di.auth.AuthModule;
import com.gardener.dagger_mvvm_rxjava.di.auth.AuthScope;
import com.gardener.dagger_mvvm_rxjava.di.auth.AuthViewModelsModule;
import com.gardener.dagger_mvvm_rxjava.di.main.MainFragmentBuildersModule;
import com.gardener.dagger_mvvm_rxjava.di.main.MainModule;
import com.gardener.dagger_mvvm_rxjava.di.main.MainScope;
import com.gardener.dagger_mvvm_rxjava.di.main.MainViewModelsModule;
import com.gardener.dagger_mvvm_rxjava.ui.auth.AuthActivity;
import com.gardener.dagger_mvvm_rxjava.ui.main.MainActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBuildersModule {

    @AuthScope
    @ContributesAndroidInjector(
            modules = {
                    AuthViewModelsModule.class,
                    AuthModule.class
            }
    )
    abstract AuthActivity contributeAuthActivity();

    @MainScope
    @ContributesAndroidInjector(
            modules = {
                    MainFragmentBuildersModule.class,
                    MainViewModelsModule.class,
                    MainModule.class
            }
    )
    abstract MainActivity contributeMainActivity();

}
