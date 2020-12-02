package com.example.sportie

import android.app.Application
import com.airbnb.epoxy.EpoxyAsyncUtil
import com.airbnb.epoxy.EpoxyController
import com.example.sportie.di.appModule
import com.example.sportie.di.dbModule
import com.example.sportie.di.networkModule
import com.example.sportie.di.repoModule
import com.example.sportie.di.useCaseModule
import com.example.sportie.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.loadKoinModules
import org.koin.core.context.startKoin

class SportieApp: Application() {

    override fun onCreate() {
        super.onCreate()
        initKoin()
        initEpoxy()

    }

    private fun initKoin() {
        startKoin {
            androidContext(this@SportieApp)
            loadKoinModules(
                listOf(
                    appModule,
                    networkModule,
                    repoModule,
                    useCaseModule,
                    viewModelModule,
                    dbModule
                )
            )
        }
    }

    private fun initEpoxy() {
        with(EpoxyAsyncUtil.getAsyncBackgroundHandler()) {
            EpoxyController.defaultDiffingHandler = this
            EpoxyController.defaultModelBuildingHandler = this
        }
        EpoxyController.setGlobalDuplicateFilteringDefault(true)
    }
}