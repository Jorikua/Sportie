package com.example.data.mapper

import com.example.data.db.model.TeamModel
import com.example.data.network.model.FullTeamResponse
import com.example.data.network.model.ShortTeamResponse

fun ShortTeamResponse.toTeam(): TeamModel {
    return TeamModel(
        id = id,
        name = name,
        isNational = isNational,
        gender = gender
    )
}

fun FullTeamResponse.toTeam(): TeamModel {
    return TeamModel(
        id = id,
        name = name,
        isNational = isNational,
        gender = gender,
        desc = desc,
        badgeUrl = badgeUrl
    )
}