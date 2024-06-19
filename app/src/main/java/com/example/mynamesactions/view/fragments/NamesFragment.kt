package com.example.mynamesactions.view.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mynamesactions.R
import com.example.mynamesactions.view.adapter.NamesAdapter
import com.example.mynamesactions.databinding.FragmentListBinding
import com.example.mynamesactions.viewmodel.MainActivityViewModel

class NamesFragment  : Fragment(R.layout.fragment_list) {

    private val viewModel: MainActivityViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = FragmentListBinding.bind(view)

        val namesAdapter = NamesAdapter()

        binding.apply {
            recyclerView.apply {
                adapter = namesAdapter
                layoutManager = LinearLayoutManager(requireContext())
                setHasFixedSize(true)
            }

            swipeRefreshLayout.isEnabled = false
        }

        viewModel.selectedNames.observe(viewLifecycleOwner) {
            namesAdapter.submitList(it)
        }
    }
}
