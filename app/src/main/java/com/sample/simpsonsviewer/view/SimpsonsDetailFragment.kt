package com.sample.simpsonsviewer.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sample.simpsonsviewer.databinding.SimpsonsDetailFragmentBinding
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
        val data = arguments?.getString("name", "No Value passed")
        binding.teamDescription.text = data

//        Picasso.with(context)
//            .load(character[0].Icon?.URL ?: "")
//            .into(binding.image)

    }

}