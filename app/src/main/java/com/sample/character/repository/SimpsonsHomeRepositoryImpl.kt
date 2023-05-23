package com.sample.character.repository


import javax.inject.Inject

class SimpsonsHomeRepositoryImpl @Inject constructor(
    private val simpsonsService: SimpsonsService
) : SimpsonsHomeRepository {

    override suspend fun getData() = simpsonsService.getData()

}
