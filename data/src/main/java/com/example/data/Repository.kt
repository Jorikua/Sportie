package com.example.data

import android.util.Log
import com.example.data.db.TeamDao
import com.example.data.db.model.TeamModel
import com.example.data.mapper.toTeam
import com.example.data.network.ApiInterface
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import java.io.IOException

class Repository(
    private val apiInterface: ApiInterface,
    private val teamDao: TeamDao
) {

    fun observeTeams(): Flow<List<TeamModel>> {
        return teamDao.getTeamsDistinct()
    }

    suspend fun getTeams() {
        val teams = retryIO { apiInterface.getTeams() }
        teamDao.upsert(teams.map { it.toTeam() })
    }

    fun observeTeamDetails(id: Int): Flow<TeamModel> {
        return teamDao.getTeamDistinct(id)
    }

    suspend fun getTeamDetails(id: Int) {
        val team = retryIO { apiInterface.getTeamDetails(id) }
        teamDao.upsert(team.toTeam())
    }

    private suspend fun <T> retryIO(
        times: Int = 3,
        initialDelay: Long = 5000, // 5 seconds
        maxDelay: Long = 15000,    // 15 second
        factor: Double = 2.0,
        block: suspend () -> T
    ): T {
        var currentDelay = initialDelay
        repeat(times - 1) {
            try {
                return block()
            } catch (e: IOException) {
                // you can log an error here and/or make a more finer-grained
                // analysis of the cause to see if retry is needed
                Log.d("TAGG", "Times $times, delay $currentDelay")
            }
            delay(currentDelay)
            currentDelay = (currentDelay * factor).toLong().coerceAtMost(maxDelay)
        }
        return block() // last attempt
    }
}