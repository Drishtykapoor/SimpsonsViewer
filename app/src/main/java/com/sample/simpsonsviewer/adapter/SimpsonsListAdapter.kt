package com.sample.simpsonsviewer.adapter

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.NavController
import androidx.recyclerview.widget.RecyclerView
import com.sample.simpsonsviewer.R
import com.sample.simpsonsviewer.repository.pojo.RelatedTopic
import com.sample.simpsonsviewer.viewmodel.SortOrder
import com.squareup.picasso.Picasso
import javax.inject.Inject

class SimpsonsListAdapter @Inject constructor(val navController: NavController) :
    RecyclerView.Adapter<SimpsonsListAdapter.ViewHolder>() {

    private val myData = mutableListOf<RelatedTopic>()
    private val originalDataSet = mutableListOf<RelatedTopic>()
    val imagePrefixUrl = "https://duckduckgo.com/"

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
        private val image: ImageView = itemView.findViewById(R.id.image_item)

        init {
            itemView.setOnClickListener {

                val bundle = Bundle()
                bundle.putString("name", myData[bindingAdapterPosition].Name)
                bundle.putString("description", myData[bindingAdapterPosition].Text)
                bundle.putString("image", imagePrefixUrl+myData[bindingAdapterPosition].Icon?.URL)

                navController.navigate(R.id.simpsonsDetailFragment, bundle)
            }
        }

        fun onBind(topic: RelatedTopic){
            name.text = topic.Text
            topic.Icon?.URL?.let {
                if(it.isNotEmpty())
                    Picasso
                        .with(itemView.context)
                        .load(imagePrefixUrl+it)
                        .into(image)
            }
        }
    }



    @SuppressLint("NotifyDataSetChanged")
    fun setData(topics: List<RelatedTopic>) {
        originalDataSet.clear()
        originalDataSet.addAll(topics)
        myData.clear()
        myData.addAll(topics)
        notifyDataSetChanged()
    }

    fun filterData(filterText: String) {
        val myList = originalDataSet.filter {
            it.Text?.contains(filterText) ?: false
        }
        myData.clear()
        myData.addAll(myList)
        notifyDataSetChanged()
    }

    fun sort(sortOrder: SortOrder) {
        when (sortOrder) {
            SortOrder.Ascending -> myData.sortBy { it.Text }
            SortOrder.Descending -> myData.sortByDescending { it.Text }
        }
        notifyDataSetChanged()
    }

}