package com.okandinc.marsdailyroverimages.viewmodel

import android.arch.lifecycle.MutableLiveData
import com.okandinc.marsdailyroverimages.api.MarsRoverImageService
import com.okandinc.marsdailyroverimages.model.Images
import com.okandinc.marsdailyroverimages.model.LatestImages
import com.okandinc.marsdailyroverimages.model.RoverImage
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

class RoverImageViewModel: BaseViewModel(){

    @Inject
    lateinit var marsRoverImageService: MarsRoverImageService

    private val compositeDisposable = CompositeDisposable()
    val roverImageListLiveData = MutableLiveData<List<RoverImage>>()
    val roverImageLiveData = MutableLiveData<RoverImage>()
    val errorMessageLiveData = MutableLiveData<String>()

    init {
        loadImagesOfTheDay(getDateAsString())
    }

    /**
     * Loads Images of the day from Mars Rover Service
     * It loads empty list on some days
     * To prevent it use loadLatestImages()
     */
    fun loadImagesOfTheDay(date: String) {
        var subscribe = marsRoverImageService
                            .getImagesOfTheDay(date)
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe({images: Images ->
                                if (images.imagesOfTheDay.isNotEmpty()) {
                                    onRetrieveRoverImageListSuccess(images.imagesOfTheDay)
                                } else {
                                    loadLatestImages()
                                }
                            },{throwable ->
                                onRetrieveRoverImageListError(throwable.localizedMessage)
                            })
        compositeDisposable.add(subscribe)
    }

    /**
     * Loads latest Images taken from Rover
     */
    fun loadLatestImages() {
        var subscribe = marsRoverImageService
                            .getLatestImages()
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe({images: LatestImages ->
                                if(images.latestImages.isNotEmpty()) {
                                    onRetrieveRoverImageListSuccess(images.latestImages)
                                } else {
                                    onRetrieveRoverImageListEmpty()
                                }
                            },{throwable ->
                                onRetrieveRoverImageListError(throwable.localizedMessage)
                            })

        compositeDisposable.add(subscribe)
    }

    private fun onRetrieveRoverImageListSuccess (images: List<RoverImage>) {
        roverImageListLiveData.postValue(images)
    }

    private fun onRetrieveRoverImageListError(message: String) {
        errorMessageLiveData.postValue(message)
    }

    private fun onRetrieveRoverImageListEmpty() {
        errorMessageLiveData.postValue("Empty List")
    }

    fun showRoverImageDetail(roverImage: RoverImage) {
        roverImageLiveData.postValue(roverImage)
    }

    /**
     * Returns Today's date as String
     */
    private fun getDateAsString(): String {
        return SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH).format(Date())
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }

}