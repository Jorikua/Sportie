package com.example.data.db.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "teams")
data class TeamModel(
    @PrimaryKey
    val id: Int,
    @ColumnInfo(name = "name")
    val name: String,
    @ColumnInfo(name = "is_national")
    val isNational: Boolean,
    @ColumnInfo(name = "gender")
    val gender: String,
    @ColumnInfo(name = "desc")
    val desc: String? = null,
    @ColumnInfo(name = "badge_url")
    val badgeUrl: String? = null
)