package com.skilbox.a18_data_time

import android.util.Log
import android.view.View
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.location.view.*
import org.threeten.bp.ZoneId
import org.threeten.bp.format.DateTimeFormatter

class LocatinViewHolder(
    view: View,
    private val onItemClick: (position: Int) -> Unit
) : RecyclerView.ViewHolder(view), LayoutContainer {

    private val imageLincView: ImageView = view.findViewById(R.id.imageLocation)
    private val formatter = DateTimeFormatter.ofPattern("HH:mm dd.MM.yyyy")
        .withZone(ZoneId.systemDefault())

    init {
        Log.e("BaseCarHolder1", "init")
        view.setOnClickListener {
            onItemClick(adapterPosition)
        }
    }

    fun bind(location: UserLocation) {
        itemView.lat.text = "latitude = " + location.latitude.toString()
        itemView.lng.text = "longitude = " + location.longitude.toString()
        itemView.alt.text = "altitude = " + location.altitude.toString()
        itemView.speed_text_view.text = "speed = " + location.speed.toString()
        itemView.accuracy.text = "accuracy = " + location.accuracy.toString()
        itemView.data_time.text = formatter.format(location.currenTime)

        Glide.with(itemView)
            .load(location.imageLinc)
            .placeholder(R.drawable.load_imege)
            .into(imageLincView)
            .view
    }

    override val containerView: View
        get() = itemView
}
