package com.sample.character.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sample.character.R
import com.sample.character.databinding.SimpsonsDetailFragmentBinding
import com.squareup.picasso.Picasso
import dagger.android.support.DaggerFragment

class SimpsonsDetailFragment : DaggerFragment() {

    private lateinit var binding: SimpsonsDetailFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = SimpsonsDetailFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val titleData = arguments?.getString("name", "No Value passed")
        val descriptionData = arguments?.getString("description", "No value passed")
        val imageData = arguments?.getString("image", "No value passed")

        binding.title.text = titleData
        binding.description.text = descriptionData
        if (imageData != null) {
            if (imageData.isNotEmpty()) {
                Picasso.with(context)
                    .load(imageData)
                    .placeholder(R.drawable.duck)
                    .into(binding.image)
            }
        }

    }

}