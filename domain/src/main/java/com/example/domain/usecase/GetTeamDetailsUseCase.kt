package com.example.domain.usecase

import com.example.base.coroutines.CoroutinesDispatcherProvider
import com.example.base.domain.UseCase
import com.example.base.network.ErrorHandler
import com.example.data.Repository
import kotlinx.coroutines.withContext

class GetTeamDetailsUseCase(
    errorHandler: ErrorHandler,
    private val dispatcherProvider: CoroutinesDispatcherProvider,
    private val repository: Repository
) : UseCase<GetTeamDetailsUseCase.Params, Unit>(errorHandler) {

    class Params(val id: Int)

    override suspend fun run(params: Params) {
        withContext(dispatcherProvider.io) {
            repository.getTeamDetails(params.id)
        }
    }
}