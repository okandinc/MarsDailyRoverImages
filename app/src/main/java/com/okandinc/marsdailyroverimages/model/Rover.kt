package com.okandinc.marsdailyroverimages.model

import com.squareup.moshi.Json

data class Rover(

    val id: Int,
    val name: String,
    @Json(name = "landing_date")
    val landingDate: String,
    @Json(name = "launch_date")
    val launchDate: String,
    val status: String,
    @Json(name = "max_sol")
    val maxSol: Int,
    @Json(name = "max_date")
    val maxDate: String,
    @Json(name = "total_photos")
    val totalPhotos: Int,
    @Json(name = "cameras")
    val cameraList: List<Camera>

)
