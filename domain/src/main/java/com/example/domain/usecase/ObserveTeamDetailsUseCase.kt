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

class ObserveTeamDetailsUseCase(
    errorHandler: ErrorHandler,
    private val dispatcherProvider: CoroutinesDispatcherProvider,
    private val repository: Repository
) : FlowUseCase<ObserveTeamDetailsUseCase.Params, Team>(errorHandler) {

    class Params(val id: Int)

    override fun run(params: Params): Flow<Team> {
        return repository.observeTeamDetails(params.id)
            .map { it.toTeam() }
            .flowOn(dispatcherProvider.io)
    }
}