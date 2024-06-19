package com.example.mynamesactions

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.mynamesactions.databinding.ActivityMainBinding
import com.example.mynamesactions.view.fragments.NamesFragment
import com.example.mynamesactions.view.fragments.ListIDFragment
import com.example.mynamesactions.viewmodel.MainActivityViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel: MainActivityViewModel by viewModels()

    private lateinit var binding: ActivityMainBinding
    private lateinit var listFragment: ListIDFragment
    private lateinit var selectedNamesFragment: NamesFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        listFragment = ListIDFragment()
        selectedNamesFragment = NamesFragment()

        navigateToFragment(listFragment)

        viewModel.selectedNames.observe(this) { _ ->
            navigateToFragment(selectedNamesFragment)
        }
    }

    private fun navigateToFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction
            .replace(R.id.fragment_container, fragment, "names_list")
            .addToBackStack(null)
            .commit()
    }
}
