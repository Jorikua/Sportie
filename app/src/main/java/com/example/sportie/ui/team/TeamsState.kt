package com.example.sportie.ui.team

import com.example.domain.model.Team
import com.example.sportie.base.BaseState

data class TeamsState(val teams: List<Team> = emptyList()) : BaseState