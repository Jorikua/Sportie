package com.example.sportie.di

import com.example.sportie.ui.team.TeamsViewModel
import com.example.sportie.ui.team.details.TeamDetailsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { TeamsViewModel(get(), get()) }
    viewModel { (id: Int) -> TeamDetailsViewModel(id, get(), get()) }
}