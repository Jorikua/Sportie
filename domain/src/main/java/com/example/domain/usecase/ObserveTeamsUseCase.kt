package com.example.domain.usecase

import com.example.base.coroutines.CoroutinesDispatcherProvider
import com.example.base.domain.FlowUseCase
import com.example.base.network.ErrorHandler
import com.example.data.Repository
import com.example.domain.mapper.toTeam
import com.example.domain.model.Team
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map

class ObserveTeamsUseCase(
    errorHandler: ErrorHandler,
    private val dispatcherProvider: CoroutinesDispatcherProvider,
    private val repository: Repository
) : FlowUseCase<Unit, List<Team>>(errorHandler) {

    override fun run(params: Unit): Flow<List<Team>> {
        return repository.observeTeams()
            .map { list -> list.map { it.toTeam() } }
            .flowOn(dispatcherProvider.io)
    }
}