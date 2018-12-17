package com.okandinc.marsdailyroverimages.model

import com.squareup.moshi.Json

data class Camera(

    val id: Int = 0,
    val name: String,
    @Json(name = "rover_id")
    val roverId: Int = 0,
    @Json(name = "full_name")
    val fullName: String
)