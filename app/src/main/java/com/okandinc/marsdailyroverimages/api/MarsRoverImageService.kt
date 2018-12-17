package com.okandinc.marsdailyroverimages.api

import com.okandinc.marsdailyroverimages.model.Images
import com.okandinc.marsdailyroverimages.model.LatestImages
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface MarsRoverImageService {

    @GET("latest_photos")
    fun getLatestImages():Observable<LatestImages>

    @GET("photos")
    fun getImagesOfTheDay(@Query("earth_date") date: String):Observable<Images>

}