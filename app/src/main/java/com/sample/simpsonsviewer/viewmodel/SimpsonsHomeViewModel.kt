package com.sample.simpsonsviewer.viewmodel

import androidx.lifecycle.LiveData
import com.sample.simpsonsviewer.repository.viewstate.SimpsonsResponseState


interface SimpsonsHomeViewModel {
    fun getData()
    fun getLiveData(): LiveData<SimpsonsResponseState>
    fun refreshData()
}
