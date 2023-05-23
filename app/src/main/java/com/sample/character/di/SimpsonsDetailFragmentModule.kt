package com.sample.character.di

import com.sample.character.view.SimpsonsDetailFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class SimpsonsDetailFragmentModule {

    @ContributesAndroidInjector(
        modules = [
        ]
    )
    abstract fun provideHomeFragment(): SimpsonsDetailFragment
}