package com.sample.simpsonsviewer.di

import com.sample.simpsonsviewer.view.SimpsonsHomeFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class SimpsonsHomeFragmentModule {

    @ContributesAndroidInjector(
        modules = [
            SimpsonsHomeFragmentDependenciesModule::class
        ]
    )
    abstract fun provideHomeFragment(): SimpsonsHomeFragment
}