package com.sample.simpsonsviewer.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.sample.simpsonsviewer.repository.SimpsonsHomeRepository

class SimpsonsHomeViewModelFactory(private val homeRepo: SimpsonsHomeRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return SimpsonsHomeViewModelImpl(homeRepo) as T
    }
}