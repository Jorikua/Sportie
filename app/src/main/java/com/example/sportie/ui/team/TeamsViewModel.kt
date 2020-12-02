package com.example.sportie.ui.team

import androidx.lifecycle.viewModelScope
import com.example.domain.usecase.GetTeamsUseCase
import com.example.domain.usecase.ObserveTeamsUseCase
import com.example.sportie.base.BaseViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class TeamsViewModel(
    private val getTeamsUseCase: GetTeamsUseCase,
    private val observeTeamsUseCase: ObserveTeamsUseCase
) : BaseViewModel<TeamsState>(TeamsState()) {

    init {
        viewModelScope.launch {
            observeTeamsUseCase.invoke(Unit).collect { result ->
                result.handleSuccess {
                    state.value = currentState.copy(
                        teams = it
                    )
                }
            }
        }
    }

    fun getTeams() {
        isLoading(true)
        viewModelScope.launch {
            getTeamsUseCase.invoke(Unit).handleSuccess()
        }
    }
}