package com.example.sportie.di

import androidx.room.Room
import com.example.data.db.SportieDb
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val dbModule = module {

    single {
        Room.databaseBuilder(
            androidApplication(), SportieDb::class.java,
            "sportie-db"
        ).build()
    }
    single { get<SportieDb>().teamDao() }
}