package com.sample.simpsonsviewer.view

import android.os.Bundle

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.text.Editable
import android.text.TextWatcher
import androidx.core.content.res.ResourcesCompat
import androidx.core.view.isVisible
import androidx.fragment.app.FragmentContainerView
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.sample.simpsonsviewer.R
import com.sample.simpsonsviewer.adapter.SimpsonsListAdapter
import com.sample.simpsonsviewer.adapter.VerticalMarginDecorator
import com.sample.simpsonsviewer.databinding.SimpsonsHomeFragmentBinding
import com.sample.simpsonsviewer.repository.viewstate.SimpsonsResponseState
import com.sample.simpsonsviewer.viewmodel.SimpsonsHomeViewModelImpl
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class SimpsonsHomeFragment : DaggerFragment(), TextWatcher {

    private lateinit var binding: SimpsonsHomeFragmentBinding

    @Inject
    lateinit var viewModel: SimpsonsHomeViewModelImpl

    lateinit var adapter: SimpsonsListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = SimpsonsHomeFragmentBinding.inflate(inflater, container, false)
        binding.swiperefresh.setOnRefreshListener {
            viewModel.refreshData()
        }
        binding.recyclerview.layoutManager = LinearLayoutManager(context)
        val detailContainer =
            binding.root.findViewById<FragmentContainerView>(R.id.item_detail_nav_container)
        adapter =
            SimpsonsListAdapter(detailContainer?.findNavController() ?: findNavController())
        binding.recyclerview.adapter = adapter
        binding.recyclerview.addItemDecoration(
            VerticalMarginDecorator(resources.getDimensionPixelSize(R.dimen.margin_large))
        )

        binding.searchText?.addTextChangedListener(this)

        return binding.root
    }


    override fun onResume() {
        super.onResume()
        viewModel.getLiveData().observe(viewLifecycleOwner) {
            when (it) {
                SimpsonsResponseState.Empty -> showEmptyData()
                SimpsonsResponseState.Loading -> {
                    binding.progressBar.isVisible = true
                    hideError()
                }
                is SimpsonsResponseState.Error -> showError(it.message)
                is SimpsonsResponseState.Success -> {
                    adapter.setData(it.simpsonsResponse.RelatedTopics)
                    hideRefresh()
                }
            }
        }
        viewModel.getData()
    }

    private fun hideRefresh() {
        if (binding.swiperefresh.isRefreshing)
            binding.swiperefresh.isRefreshing = false
        binding.progressBar.isVisible = false
    }

    private fun showError(it: String?) {
        binding.errorImage.isVisible = true
        binding.errorImage.background =
            ResourcesCompat.getDrawable(resources, R.drawable.sad_cloud, null)
        binding.errorText.isVisible = true
        binding.errorText.text = it
        hideRefresh()
    }

    private fun hideError() {
        binding.errorImage.isVisible = false
        binding.errorText.isVisible = false
        hideRefresh()
    }

    private fun showEmptyData() {
        binding.progressBar.isVisible = false
        binding.errorText.isVisible = true
        binding.errorImage.isVisible = true
        binding.errorImage.background =
            ResourcesCompat.getDrawable(resources, R.mipmap.stunned, null)
        binding.errorText.text = context?.getString(R.string.empty_data)
        hideRefresh()
    }

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        adapter.filterData(s.toString())
    }

    override fun afterTextChanged(s: Editable?) {}
}