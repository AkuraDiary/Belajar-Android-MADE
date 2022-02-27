package com.dicoding.tourismapp.maps

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.dicoding.tourismapp.core.data.Resource
import com.dicoding.tourismapp.maps.databinding.ActivityMapsBinding
import org.koin.android.viewmodel.ext.android.viewModel


class MapsActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMapsBinding

    private val mapsViewModel : MapsViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMapsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.title = "Tourism Map"

        getTourismData()
    }

    private fun getTourismData(){
        mapsViewModel.tourism.observe(this) { tourism ->
            if (tourism != null) {
                when (tourism) {
                    is Resource.Loading -> binding.progressBar.visibility = View.VISIBLE

                    is Resource.Success -> binding.apply {
                        progressBar.visibility = View.GONE
                        tvMaps.text = "This is map of ${tourism.data?.get(0)?.name}"
                    }

                    is Resource.Error -> binding.apply {
                        progressBar.visibility = View.GONE
                        tvError.visibility = View.VISIBLE
                        tvError.text = tourism.message
                    }
                }
            }

        }
    }
}