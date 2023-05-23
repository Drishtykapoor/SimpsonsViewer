package com.sample.character.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.sample.character.repository.SimpsonsHomeRepository

class SimpsonsHomeViewModelFactory(private val homeRepo: SimpsonsHomeRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return SimpsonsHomeViewModelImpl(homeRepo) as T
    }
}