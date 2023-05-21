package com.sample.simpsonsviewer.repository.viewstate

import com.sample.simpsonsviewer.repository.pojo.Simpsons

sealed class SimpsonsResponseState {
    object Empty : SimpsonsResponseState()
    object Loading : SimpsonsResponseState()
    data class Success(val simpsonsResponse: Simpsons) : SimpsonsResponseState()
    data class Error(val message: String?) : SimpsonsResponseState()
}
