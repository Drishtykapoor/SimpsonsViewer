package com.sample.character.repository.viewstate

import com.sample.character.repository.pojo.Simpsons

sealed class SimpsonsDetailResponseState {
    object Empty : SimpsonsDetailResponseState()
    object Loading : SimpsonsDetailResponseState()
    data class Success(val simpsonsResponse: Simpsons) : SimpsonsDetailResponseState()
    data class Error(val message: String?) : SimpsonsDetailResponseState()
}
