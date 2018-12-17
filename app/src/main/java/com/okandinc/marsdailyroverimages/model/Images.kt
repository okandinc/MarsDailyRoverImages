package com.okandinc.marsdailyroverimages.model

import com.squareup.moshi.Json

data class Images(
    @Json(name = "photos")
    val imagesOfTheDay: List<RoverImage>
)