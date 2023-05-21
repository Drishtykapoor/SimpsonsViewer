package com.sample.simpsonsviewer.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.sample.simpsonsviewer.databinding.SimpsonsDetailFragmentBinding
import com.sample.simpsonsviewer.repository.viewstate.SimpsonsDetailResponseState
import com.sample.simpsonsviewer.repository.viewstate.SimpsonsDetailResponseState.*
import com.sample.simpsonsviewer.repository.viewstate.SimpsonsResponseState
import com.sample.simpsonsviewer.viewmodel.SimpsonsHomeViewModel
import com.squareup.picasso.Picasso
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class SimpsonsDetailFragment : DaggerFragment() {

    private lateinit var binding: SimpsonsDetailFragmentBinding

    @Inject
    lateinit var viewModel: SimpsonsHomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = SimpsonsDetailFragmentBinding.inflate(inflater, container, false)
        return View(context)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getLiveData().observe(viewLifecycleOwner) {
//            when (it) {
//                Empty -> {
//                    binding.error.isVisible = true
//                }
//                is Error -> {
//                    binding.error.isVisible = true
//                }
//                Loading -> {
//                    binding.error.isVisible = false
//                }
//                is Success -> {
//                    binding.error.isVisible = false
//                    val player = it.allTeamsResponse.player[0]
//                    Picasso.with(context)
//                        .load(player.strThumb)
//                        .into(binding.image)
//                    binding.title.text = player.strPlayer
//                    binding.description.text = player.strDescriptionEN
//                }
//                SimpsonsResponseState.Empty -> binding.eventsErrorView.visibility = View.VISIBLE
//                is SimpsonsResponseState.Error -> binding.eventsErrorView.visibility = View.VISIBLE
//                SimpsonsResponseState.Loading -> binding.eventsErrorView.visibility = View.INVISIBLE
//                is SimpsonsResponseState.Success -> {
//                    binding.eventsErrorView.visibility = View.VISIBLE
//                    binding.description.visibility = View.VISIBLE
//                    val character = it.simpsonsResponse.RelatedTopics
//                    Picasso.with(context)
//                        .load(character[0].Icon?.URL ?: "")
//                        .into(binding.image)
//                }
//            }
        }
    }

//    override fun onResume() {
//        super.onResume()
//        viewModel.getData(args.name)
//    }
}