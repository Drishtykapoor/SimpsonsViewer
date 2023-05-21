package com.sample.simpsonsviewer.viewmodel

import androidx.lifecycle.LiveData
import com.sample.simpsonsviewer.repository.viewstate.SimpsonsDetailResponseState

interface SimpsonsDetailViewModel {
    fun getData(teamId: String)
    fun getSimpsonsLiveData(): LiveData<SimpsonsDetailResponseState>
}
