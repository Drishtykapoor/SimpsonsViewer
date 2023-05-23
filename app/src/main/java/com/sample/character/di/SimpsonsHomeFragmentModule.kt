package com.sample.character.di

import com.sample.character.view.SimpsonsHomeFragment
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