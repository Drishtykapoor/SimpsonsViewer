package com.sample.character.di

import androidx.lifecycle.ViewModelProvider
import com.sample.character.repository.SimpsonsHomeRepository
import com.sample.character.repository.SimpsonsHomeRepositoryImpl
import com.sample.character.view.SimpsonsHomeFragment
import com.sample.character.viewmodel.*
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module(includes = [SimpsonsHomeFragmentDependenciesModule.HomeFragViewModelModule::class])
interface SimpsonsHomeFragmentDependenciesModule {

    @Binds
    fun provideHomeRepository(homeRepositoryImpl: SimpsonsHomeRepositoryImpl): SimpsonsHomeRepository

    @Binds
    fun provideHomeViewModel(homeViewModelImpl: SimpsonsHomeViewModelImpl): SimpsonsHomeViewModel

    @Module
    object HomeFragViewModelModule {

        @Provides
        fun provideHomeViewModelFactory(homeRepository: SimpsonsHomeRepository): SimpsonsHomeViewModelFactory {
            return SimpsonsHomeViewModelFactory(homeRepository)
        }

        @Provides
        fun provideViewModel(
            homeFragment: SimpsonsHomeFragment,
            viewModelFactory: SimpsonsHomeViewModelFactory
        ): SimpsonsHomeViewModelImpl {
            val v = ViewModelProvider(
                homeFragment,
                viewModelFactory
            )
            return v.get(SimpsonsHomeViewModelImpl::class.java)
        }
    }
}

