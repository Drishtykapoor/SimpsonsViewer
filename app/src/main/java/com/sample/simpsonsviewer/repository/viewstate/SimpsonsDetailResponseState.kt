package com.sample.simpsonsviewer.repository.viewstate

import com.sample.simpsonsviewer.repository.pojo.Simpsons

sealed class SimpsonsDetailResponseState {
    object Empty : SimpsonsDetailResponseState()
    object Loading : SimpsonsDetailResponseState()
    data class Success(val simpsonsResponse: Simpsons) : SimpsonsDetailResponseState()
    data class Error(val message: String?) : SimpsonsDetailResponseState()
}
