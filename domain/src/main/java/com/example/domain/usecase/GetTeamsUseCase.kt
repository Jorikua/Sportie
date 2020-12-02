package com.example.domain.usecase

import com.example.base.coroutines.CoroutinesDispatcherProvider
import com.example.base.domain.UseCase
import com.example.base.network.ErrorHandler
import com.example.data.Repository
import kotlinx.coroutines.withContext

class GetTeamsUseCase(
    errorHandler: ErrorHandler,
    private val dispatcherProvider: CoroutinesDispatcherProvider,
    private val repository: Repository
) : UseCase<Unit, Unit>(errorHandler) {
    override suspend fun run(params: Unit) {
        withContext(dispatcherProvider.io) {
            repository.getTeams()
        }
    }
}