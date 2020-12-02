package com.example.sportie.di

import com.example.domain.usecase.GetTeamDetailsUseCase
import com.example.domain.usecase.GetTeamsUseCase
import com.example.domain.usecase.ObserveTeamDetailsUseCase
import com.example.domain.usecase.ObserveTeamsUseCase
import org.koin.dsl.module

val useCaseModule = module {
    factory { GetTeamDetailsUseCase(get(), get(), get()) }
    factory { GetTeamsUseCase(get(), get(), get()) }
    factory { ObserveTeamDetailsUseCase(get(), get(), get()) }
    factory { ObserveTeamsUseCase(get(), get(), get()) }
}