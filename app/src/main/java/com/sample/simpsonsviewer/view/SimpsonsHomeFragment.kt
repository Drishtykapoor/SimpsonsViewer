package com.sample.simpsonsviewer.view

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.*
import androidx.appcompat.widget.PopupMenu
import androidx.core.content.res.ResourcesCompat
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.sample.simpsonsviewer.R
import com.sample.simpsonsviewer.adapter.SimpsonsListAdapter
import com.sample.simpsonsviewer.adapter.VerticalMarginDecorator
import com.sample.simpsonsviewer.databinding.SimpsonsHomeFragmentBinding
import com.sample.simpsonsviewer.repository.viewstate.SimpsonsResponseState
import com.sample.simpsonsviewer.viewmodel.SimpsonsHomeViewModelImpl
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class SimpsonsHomeFragment : DaggerFragment(),
    PopupMenu.OnMenuItemClickListener {

    private lateinit var binding: SimpsonsHomeFragmentBinding

    @Inject
    lateinit var viewModel: SimpsonsHomeViewModelImpl

    @Inject
    lateinit var adapter: SimpsonsListAdapter

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = SimpsonsHomeFragmentBinding.inflate(inflater, container, false)
        binding.swiperefresh.setOnRefreshListener {
            viewModel.refreshData()
        }
        binding.recyclerview.layoutManager = LinearLayoutManager(context)
        binding.recyclerview.adapter = adapter
        binding.recyclerview.addItemDecoration(
            VerticalMarginDecorator(resources.getDimensionPixelSize(R.dimen.margin_large))
        )
        binding.sort.setOnClickListener { showPopup(it) }
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
        binding.errorText.text = context?.getString(R.string.something_went_wrong)
        hideRefresh()
    }

    private fun hideError() {
        binding.errorImage.isVisible = false
        binding.errorText.isVisible = false
        binding.errorText.text = context?.getString(R.string.something_went_wrong)
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

    private fun showPopup(v: View) {
        context?.let {
            val popup = PopupMenu(it, v)
            val inflater: MenuInflater = popup.menuInflater
            inflater.inflate(R.menu.sort_menu, popup.menu)
            popup.setOnMenuItemClickListener(this)
            popup.show()
        }
    }

    override fun onMenuItemClick(item: MenuItem): Boolean {
        return when (item.itemId) {
//            R.id.ascending -> {
//                adapter.sort(SortOrder.Ascending)
//                true
//            }
//            R.id.descending -> {
//                adapter.sort(SortOrder.Descending)
//                true
//            }
            else -> false
        }
    }
}