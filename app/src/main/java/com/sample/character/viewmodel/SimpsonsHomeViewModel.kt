package com.sample.character.viewmodel

import androidx.lifecycle.LiveData
import com.sample.character.repository.viewstate.SimpsonsResponseState


interface SimpsonsHomeViewModel {
    fun getData()
    fun getLiveData(): LiveData<SimpsonsResponseState>
    fun refreshData()
}
