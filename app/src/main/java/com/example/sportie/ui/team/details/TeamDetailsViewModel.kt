package com.example.sportie.ui.team.details

import androidx.lifecycle.viewModelScope
import com.example.domain.usecase.GetTeamDetailsUseCase
import com.example.domain.usecase.ObserveTeamDetailsUseCase
import com.example.sportie.base.BaseViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class TeamDetailsViewModel(
    private val id: Int,
    private val observeTeamDetailsUseCase: ObserveTeamDetailsUseCase,
    private val getTeamDetailsUseCase: GetTeamDetailsUseCase
) : BaseViewModel<TeamDetailsState>(TeamDetailsState()) {

    init {
        viewModelScope.launch {
            observeTeamDetailsUseCase.invoke(ObserveTeamDetailsUseCase.Params(id))
                .collect { result ->
                    result.handleSuccess {
                        state.value = currentState.copy(team = it)
                    }
                }
        }
    }

    fun getTeamDetails(id: Int) {
        viewModelScope.launch {
            getTeamDetailsUseCase.invoke(GetTeamDetailsUseCase.Params(id)).handleSuccess()
        }
    }
}