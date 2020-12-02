package com.example.domain.model

data class Team(
    val id: Int,
    val name: String,
    val isNational: Boolean,
    val gender: String,
    val desc: String,
    val badgeUrl: String,
)