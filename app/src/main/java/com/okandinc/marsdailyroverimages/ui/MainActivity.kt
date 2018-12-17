package com.okandinc.marsdailyroverimages.ui

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.okandinc.marsdailyroverimages.R
import com.okandinc.marsdailyroverimages.viewmodel.RoverImageViewModel

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        ViewModelProviders.of(this).get(RoverImageViewModel::class.java)
    }
}
