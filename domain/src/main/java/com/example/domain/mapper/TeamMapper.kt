package com.example.domain.mapper

import com.example.data.db.model.TeamModel
import com.example.domain.model.Team

fun TeamModel.toTeam(): Team {
    return Team(
        id = id,
        name = name,
        isNational = isNational,
        gender = gender,
        desc = desc.orEmpty(),
        badgeUrl = badgeUrl.orEmpty()
    )
}