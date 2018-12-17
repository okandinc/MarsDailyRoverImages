package com.okandinc.marsdailyroverimages.di.component

import com.okandinc.marsdailyroverimages.di.module.NetworkModule
import com.okandinc.marsdailyroverimages.viewmodel.RoverImageViewModel
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(NetworkModule::class))
interface ViewModelInjector {

    fun inject(roverImageViewModel: RoverImageViewModel)

    @Component.Builder
    interface Builder {
        fun build(): ViewModelInjector

        fun networkModule(networkModule: NetworkModule): Builder
    }
}