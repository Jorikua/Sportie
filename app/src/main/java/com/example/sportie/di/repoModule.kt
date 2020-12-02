package com.example.sportie.di

import com.example.data.Repository
import org.koin.dsl.module

val repoModule = module {
    single { Repository(get(), get()) }
}