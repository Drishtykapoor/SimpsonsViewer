package com.reachmobi.sports.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.sample.character.repository.SimpsonsHomeRepository
import com.sample.character.viewmodel.SimpsonsHomeViewModelImpl

class SimpsonsDetailViewModelFactory (private val homeRepository: SimpsonsHomeRepository): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return SimpsonsHomeViewModelImpl(homeRepository) as T
    }

}