package com.sample.character.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sample.character.repository.SimpsonsHomeRepository
import com.sample.character.repository.viewstate.SimpsonsDetailResponseState
import kotlinx.coroutines.*
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import kotlin.time.Duration.Companion.seconds

class SimpsonsDetailViewModelImpl(private val homeRepository: SimpsonsHomeRepository) : ViewModel(),
    SimpsonsDetailViewModel {

    private val simpsonsData = MutableLiveData<SimpsonsDetailResponseState>()

    override fun getData(teamId: String) {
        simpsonsData.value = SimpsonsDetailResponseState.Loading

        viewModelScope.launch(Dispatchers.IO) {
            val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
            var current = LocalDateTime.now().format(formatter)
            Log.d("time", "startedAt $current")
            delay(3.seconds)
            current = LocalDateTime.now().format(formatter)
            Log.d("time", "startedAt $current")

            try {
                val response = homeRepository.getData()
                withContext(Dispatchers.Main) {
                    if (response.isSuccessful) {
                        if (response.body() != null) {
                            val data = response.body()!!
                                simpsonsData.value =
                                    SimpsonsDetailResponseState.Success(response.body()!!)
                        } else simpsonsData.value = SimpsonsDetailResponseState.Empty
                    } else {
                        simpsonsData.value = SimpsonsDetailResponseState.Error(response.message())
                    }
                }
            } catch (e: Exception) {
                simpsonsData.value = SimpsonsDetailResponseState.Error(e.message)
            }
        }
    }

    override fun getSimpsonsLiveData(): LiveData<SimpsonsDetailResponseState> = simpsonsData
}