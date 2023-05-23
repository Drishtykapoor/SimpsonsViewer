package com.sample.character.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sample.character.repository.SimpsonsHomeRepository
import com.sample.character.repository.pojo.RelatedTopic
import com.sample.character.repository.viewstate.SimpsonsResponseState
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
                            val relatedTopicList = mutableListOf<RelatedTopic>()
                            for (i in 0 until it.RelatedTopics.size) {
                                relatedTopicList.add(
                                    RelatedTopic(
                                        it.RelatedTopics[i].FirstURL,
                                        it.RelatedTopics[i].Icon,
                                        it.RelatedTopics[i].Result,
                                        it.RelatedTopics[i].Text?.split("-")?.get(0) ?: "",
                                        it.RelatedTopics[i].Text
                                    )
                                )
                            }
                            val data = it.copy(RelatedTopics = relatedTopicList)
                            liveData.postValue(SimpsonsResponseState.Success(data))
                        }
                    } else
                        liveData.postValue(SimpsonsResponseState.Empty)
                } else
                    liveData.postValue(SimpsonsResponseState.Error(response.message()))
            }
        }
    }

    override fun getLiveData(): LiveData<SimpsonsResponseState> = liveData

}