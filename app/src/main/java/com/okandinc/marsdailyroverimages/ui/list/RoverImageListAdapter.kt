package com.okandinc.marsdailyroverimages.ui.list

import android.support.v7.widget.RecyclerView
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.okandinc.marsdailyroverimages.R
import com.okandinc.marsdailyroverimages.model.RoverImage
import kotlinx.android.synthetic.main.view_rover_image_list_item.view.*

class RoverImageListAdapter(private val list: List<RoverImage>, private val itemClickListener: (RoverImage) -> Unit) : RecyclerView.Adapter<RoverImageListAdapter.RoverImageHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RoverImageHolder {
        val inflater = LayoutInflater.from(parent.context)
        val itemView = inflater.inflate(R.layout.view_rover_image_list_item,parent,false)
        return RoverImageHolder(itemView)
    }

    override fun onBindViewHolder(holder: RoverImageHolder, position: Int) {
        holder.bind(list[position])
        holder.itemView.setOnClickListener{itemClickListener(list[position])}
    }

    override fun getItemCount(): Int {
        return list.size
    }

    class RoverImageHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(item: RoverImage) {
            Glide.with(itemView.context).asBitmap().load(item.imageUrl).into(itemView.imgRoverSmall)
            itemView.txtRoverName.text = Html.fromHtml("<b>" + itemView.context.getString(R.string.rover) + "</b>" + " " + item.rover.name)
            itemView.txtCameraName.text = item.camera.fullName
            itemView.txtEarthDate.text = Html.fromHtml("<b>" + itemView.context.getString(R.string.earthdate) + "</b>" + " " + item.earthDate)
            itemView.txtMissionDate.text = Html.fromHtml("<b>" + itemView.context.getString(R.string.sol) + "</b>" + " " + item.sol)
        }
    }
}