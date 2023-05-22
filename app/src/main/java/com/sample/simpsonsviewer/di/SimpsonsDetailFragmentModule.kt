package com.sample.simpsonsviewer.di

import com.sample.simpsonsviewer.view.SimpsonsDetailFragment
import com.sample.simpsonsviewer.view.SimpsonsHomeFragment
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