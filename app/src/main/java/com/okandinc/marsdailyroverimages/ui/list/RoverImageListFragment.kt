package com.okandinc.marsdailyroverimages.ui.list

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.NavHostFragment
import com.okandinc.marsdailyroverimages.R
import com.okandinc.marsdailyroverimages.model.RoverImage
import com.okandinc.marsdailyroverimages.viewmodel.RoverImageViewModel
import kotlinx.android.synthetic.main.fragment_rover_image_list.*

class RoverImageListFragment: Fragment() {

    private lateinit var model: RoverImageViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_rover_image_list,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        model = activity?.run {
            ViewModelProviders.of(this).get(RoverImageViewModel::class.java)
        } ?: throw Exception(getString(R.string.messageInvalidActivity))
        recylerView.layoutManager = LinearLayoutManager(activity)
        model.roverImageListLiveData.observe(this, Observer<List<RoverImage>> {list ->
            val roverImageListAdapter = RoverImageListAdapter(list!!,this::onRoverImageClick)
            recylerView.adapter = roverImageListAdapter
            showProgress(false)
        })
        model.errorMessageLiveData.observe(this, Observer<String> {message ->
            Log.e("error",message)
            Toast.makeText(context,getString(R.string.errorMessage),Toast.LENGTH_SHORT).show()
            showProgress(false)
        })
    }

    fun showProgress(show: Boolean) {
        if (show) {
            progressBar.visibility = View.VISIBLE
        } else {
            progressBar.visibility = View.GONE
        }
    }

    private fun onRoverImageClick(roverImage:RoverImage) {
        model.showRoverImageDetail(roverImage)
        NavHostFragment.findNavController(this).navigate(R.id.action_dailyImageListFragment_to_dailyImageDetailFragment)
    }
}