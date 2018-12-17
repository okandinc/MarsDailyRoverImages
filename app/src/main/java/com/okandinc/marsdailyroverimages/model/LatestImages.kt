package com.okandinc.marsdailyroverimages.model

import com.squareup.moshi.Json

data class LatestImages(
    @Json(name = "latest_photos")
    val latestImages: List<RoverImage>
)