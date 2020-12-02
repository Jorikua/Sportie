package com.example.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.data.db.model.TeamModel

@Database(entities = [TeamModel::class], version = 1)
abstract class SportieDb: RoomDatabase() {
    abstract fun teamDao(): TeamDao
}