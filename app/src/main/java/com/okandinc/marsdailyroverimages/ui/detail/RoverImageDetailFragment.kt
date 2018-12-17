package com.okandinc.marsdailyroverimages.ui.detail

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.okandinc.marsdailyroverimages.R
import com.okandinc.marsdailyroverimages.model.RoverImage
import com.okandinc.marsdailyroverimages.viewmodel.RoverImageViewModel
import kotlinx.android.synthetic.main.fragment_rover_image_detail.*

class RoverImageDetailFragment : Fragment() {

    private lateinit var model: RoverImageViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_rover_image_detail,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        model = activity?.run {
            ViewModelProviders.of(this).get(RoverImageViewModel::class.java)
        } ?: throw Exception(getString(R.string.messageInvalidActivity))
        model.roverImageLiveData.observe(this, Observer<RoverImage> {roverImage ->
            initViews(roverImage!!)
        })
    }

    private fun initViews(roverImage: RoverImage){
        txtCameraAbbreviation.text = Html.fromHtml("<b>" + getString(R.string.abbreviation) + "</b>" + " " + roverImage.camera.name)
        txtEarthDate.text = Html.fromHtml("<b>" + getString(R.string.earthdate) + "</b>" + " " + roverImage.earthDate)
        txtRoverName.text = Html.fromHtml("<b>" + getString(R.string.rover) + "</b>" + " " + roverImage.rover.name)
        txtRoverLandingDate.text = Html.fromHtml("<b>" + getString(R.string.landingdate) + "</b>" + " " + roverImage.rover.landingDate)
        txtLaunchDate.text = Html.fromHtml("<b>" + getString(R.string.launchdate) + "</b>" + " " + roverImage.rover.launchDate)
        txtMaxDate.text = Html.fromHtml("<b>" + getString(R.string.maxdate) + "</b>" + " " + roverImage.rover.maxDate)
        txtStatus.text = Html.fromHtml("<b>" + getString(R.string.status) + "</b>" + " " + roverImage.rover.status)
        txtTotalPhotos.text = Html.fromHtml("<b>" + getString(R.string.totalPhotos) + "</b>" + " " + roverImage.rover.totalPhotos)

        if (roverImage.imageUrl.isNotBlank()) {
            Glide.with(this).asBitmap().load(roverImage.imageUrl).into(imgRover)
        }
    }
}