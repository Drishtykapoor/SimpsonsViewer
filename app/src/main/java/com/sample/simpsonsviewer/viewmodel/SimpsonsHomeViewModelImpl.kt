package com.sample.simpsonsviewer.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sample.simpsonsviewer.repository.viewstate.SimpsonsResponseState
import com.sample.simpsonsviewer.repository.SimpsonsHomeRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class SimpsonsHomeViewModelImpl @Inject constructor(
    private val homeRepository: SimpsonsHomeRepository,
) : ViewModel(), SimpsonsHomeViewModel {

    private var liveData = MutableLiveData<SimpsonsResponseState>(SimpsonsResponseState.Empty)


    override fun getData() {
        if (liveData.value !is SimpsonsResponseState.Success)
            fetchData()
    }

    override fun refreshData() {
        fetchData()
    }

    private fun fetchData() {
        viewModelScope.launch(Dispatchers.IO) {
            liveData.postValue(SimpsonsResponseState.Loading)
            val response = homeRepository.getData()
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    if (response.body() != null) {
                        response.body()?.let {
                            liveData.postValue(SimpsonsResponseState.Success(it))
                        }
                    }
                    else
                        liveData.postValue(SimpsonsResponseState.Empty)
                } else
                    liveData.postValue(SimpsonsResponseState.Error(response.message()))
            }
        }
    }

    override fun getLiveData(): LiveData<SimpsonsResponseState> = liveData

}