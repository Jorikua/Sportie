package com.example.data.network.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class FullTeamResponse(
    @Json(name = "id")
    val id: Int,
    @Json(name = "name")
    val name: String,
    @Json(name = "national")
    val isNational: Boolean,
    @Json(name = "gender")
    val gender: String,
    @Json(name = "description")
    val desc: String,
    @Json(name = "badge_url")
    val badgeUrl: String
)