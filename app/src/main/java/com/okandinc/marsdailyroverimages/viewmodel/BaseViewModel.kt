package com.okandinc.marsdailyroverimages.viewmodel

import android.arch.lifecycle.ViewModel
import com.okandinc.marsdailyroverimages.di.component.DaggerViewModelInjector
import com.okandinc.marsdailyroverimages.di.component.ViewModelInjector
import com.okandinc.marsdailyroverimages.di.module.NetworkModule

abstract class BaseViewModel:ViewModel() {

    private val injector:ViewModelInjector = DaggerViewModelInjector.builder().networkModule(NetworkModule).build()

    init {
        inject()
    }

    private fun inject() {
        when(this) {
            is RoverImageViewModel -> injector.inject(this)
        }
    }
}