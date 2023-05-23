package com.sample.character.viewmodel

import androidx.lifecycle.LiveData
import com.sample.character.repository.viewstate.SimpsonsDetailResponseState

interface SimpsonsDetailViewModel {
    fun getData(teamId: String)
    fun getSimpsonsLiveData(): LiveData<SimpsonsDetailResponseState>
}
