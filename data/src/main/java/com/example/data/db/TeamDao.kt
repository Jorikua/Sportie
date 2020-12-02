package com.example.data.db

import androidx.room.Dao
import androidx.room.Query
import com.example.base.data.BaseDao
import com.example.data.db.model.TeamModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged

@Dao
abstract class TeamDao: BaseDao<TeamModel>() {

    @Query("SELECT * FROM teams")
    abstract fun getAllTeams(): Flow<List<TeamModel>>

    fun getTeamsDistinct() = getAllTeams().distinctUntilChanged()

    @Query("SELECT * FROM teams WHERE teams.id == :id")
    abstract fun getTeam(id: Int): Flow<TeamModel>

    fun getTeamDistinct(id: Int) = getTeam(id).distinctUntilChanged()
}