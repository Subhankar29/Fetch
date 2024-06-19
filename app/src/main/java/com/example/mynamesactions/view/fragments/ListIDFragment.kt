package com.example.mynamesactions.view.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mynamesactions.R
import com.example.mynamesactions.view.adapter.ListIDAdapter
import com.example.mynamesactions.databinding.FragmentListBinding
import com.example.mynamesactions.viewmodel.MainActivityViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ListIDFragment : Fragment(R.layout.fragment_list) {

    private val viewModel: MainActivityViewModel by activityViewModels()

    companion object {
        private const val APP_NAME = "List Names"
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = FragmentListBinding.bind(view)

        val listIDAdapter = ListIDAdapter() { id ->
            viewModel.selectNewsArticle(id)
        }

        binding.apply {
            recyclerView.
            apply {
                adapter = listIDAdapter
                layoutManager = LinearLayoutManager(requireContext())
                setHasFixedSize(true)
            }

            swipeRefreshLayout.setOnRefreshListener {
                viewModel.fetchNames()
            }

            viewModel.isLoading.observe(viewLifecycleOwner) { isLoading ->
                swipeRefreshLayout.isRefreshing = isLoading
            }

            viewModel.isError.observe(viewLifecycleOwner) { isError ->
                if (isError)
                    showErrorPage(binding)
                else
                    showData(binding)
            }
        }

        viewModel.mapData.observe(viewLifecycleOwner) {
            showData(binding)
            listIDAdapter.submitList(it)
        }

        viewModel.fetchNames()
    }

    private fun showErrorPage(binding: FragmentListBinding) {
        binding.apply {
            swipeRefreshLayout.isRefreshing = false
            recyclerView.visibility = View.GONE
            textViewError.visibility = View.VISIBLE
            buttonRetry.visibility = View.VISIBLE
        }
    }

    private fun showData(binding: FragmentListBinding) {
        binding.apply {
            swipeRefreshLayout.isRefreshing = false
            recyclerView.visibility = View.VISIBLE
            textViewError.visibility = View.GONE
            buttonRetry.visibility = View.GONE
        }
    }


    override fun onResume() {
        super.onResume()
        requireActivity().title = APP_NAME
    }
}
