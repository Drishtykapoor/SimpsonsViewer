package com.sample.simpsonsviewer.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.NavController
import androidx.recyclerview.widget.RecyclerView
import com.sample.simpsonsviewer.R
import com.sample.simpsonsviewer.repository.pojo.RelatedTopic
import com.squareup.picasso.Picasso
import javax.inject.Inject

class SimpsonsListAdapter @Inject constructor(val navController: NavController) :
    RecyclerView.Adapter<SimpsonsListAdapter.ViewHolder>() {

    private val myData = mutableListOf<RelatedTopic>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.simpsons_row, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBind(myData[position])
    }

    override fun getItemCount(): Int {
        return myData.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val name: TextView = itemView.findViewById(R.id.title)
        private val image: ImageView = itemView.findViewById(R.id.image_view)

        fun onBind(topic: RelatedTopic) {
            name.text = topic.Text
            Picasso
                .with(image.context)
                .load(
                    topic.Icon?.URL ?: "https://www.freeiconspng.com/thumbs/duck-png/duck-png-19.png"
                )
                .into(image)
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setData(topics: List<RelatedTopic>) {
        myData.clear()
        myData.addAll(topics)
        notifyDataSetChanged()
    }

}