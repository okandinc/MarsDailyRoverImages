package com.okandinc.marsdailyroverimages.model

import com.squareup.moshi.Json

data class RoverImage(

    val id: Int,
    val sol: Int,
    val camera: Camera,
    @Json(name = "img_src")
    val imageUrl: String,
    @Json(name = "earth_date")
    val earthDate: String,
    val rover: Rover

)