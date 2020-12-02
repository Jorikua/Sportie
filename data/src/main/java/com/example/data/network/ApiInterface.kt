package com.example.data.network

import com.example.data.network.model.FullTeamResponse
import com.example.data.network.model.ShortTeamResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiInterface {

    @GET("/teams/teams.json")
    suspend fun getTeams(): List<ShortTeamResponse>

    @GET("/teams/{id}/team.json")
    suspend fun getTeamDetails(@Path("id") id: Int): FullTeamResponse
}