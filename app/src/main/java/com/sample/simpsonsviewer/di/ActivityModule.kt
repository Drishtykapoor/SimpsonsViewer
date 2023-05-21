package com.sample.simpsonsviewer.di

import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.sample.simpsonsviewer.R
import com.sample.simpsonsviewer.view.MainActivity
import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityModule {

    @ContributesAndroidInjector(
        modules = [
            UtilModule::class,
            NavigationModule::class,
            SimpsonsHomeFragmentModule::class,
            ApiModule::class
        ]
    )
    abstract fun contributeMainActivity(): MainActivity

    @Module
    object NavigationModule {
        @Provides
        fun provideNavigationController(mainActivity: MainActivity): NavController {
            val navHostFragment =
                mainActivity.supportFragmentManager
                    .findFragmentById(R.id.nav_host_fragment) as NavHostFragment
            return navHostFragment.navController
        }
    }
}