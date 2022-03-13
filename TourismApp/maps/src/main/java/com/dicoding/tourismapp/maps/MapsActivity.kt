package com.dicoding.tourismapp.maps

import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Bundle
import android.os.PersistableBundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.dicoding.tourismapp.core.data.Resource
import com.dicoding.tourismapp.core.domain.model.Tourism
import com.dicoding.tourismapp.detail.DetailTourismActivity
import com.dicoding.tourismapp.maps.databinding.ActivityMapsBinding
import com.google.gson.Gson
import com.mapbox.mapboxsdk.Mapbox
import com.mapbox.mapboxsdk.camera.CameraUpdateFactory
import com.mapbox.mapboxsdk.geometry.LatLng
import com.mapbox.mapboxsdk.geometry.LatLngBounds
import com.mapbox.mapboxsdk.maps.MapboxMap
import com.mapbox.mapboxsdk.maps.Style
import com.mapbox.mapboxsdk.plugins.annotation.SymbolManager
import com.mapbox.mapboxsdk.plugins.annotation.SymbolOptions
import org.koin.android.ext.android.bind
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules


class MapsActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMapsBinding

    private val mapsViewModel : MapsViewModel by viewModel()
    private lateinit var mapboxMap : MapboxMap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Mapbox.getInstance(this, getString(R.string.mapbox_acces_token))
        binding = ActivityMapsBinding.inflate(layoutInflater)

        setContentView(binding.root)
        loadKoinModules(mapsModule) //load the Koin module

        supportActionBar?.title = "Tourism Map"

        binding.mapView.getMapAsync{ mapboxMap ->
            this.mapboxMap = mapboxMap
            getTourismData()
        }

    }
    override fun onStart() {
        super.onStart()
        binding.mapView.onStart()
    }
    override fun onResume() {
        super.onResume()
        binding.mapView.onResume()
    }
    override fun onPause() {
        super.onPause()
        binding.mapView.onPause()
    }
    override fun onStop() {
        super.onStop()
        binding.mapView.onStop()
    }

    override fun onSaveInstanceState(outState: Bundle, outPersistentState: PersistableBundle) {
        super.onSaveInstanceState(outState, outPersistentState)
        binding.mapView.onSaveInstanceState(outState)
    }
    override fun onDestroy() {
        super.onDestroy()
        binding.mapView.onDestroy()
    }
    override fun onLowMemory() {
        super.onLowMemory()
        binding.mapView.onLowMemory()
    }

    private fun getTourismData(){
        mapsViewModel.tourism.observe(this) { tourism ->
            if (tourism != null) {
                when (tourism) {
                    is Resource.Loading -> binding.progressBar.visibility = View.VISIBLE

                    is Resource.Success -> binding.apply {
                        progressBar.visibility = View.GONE
                        showMarker(tourism.data)
                        //tvMaps.text = "This is map of ${tourism.data?.get(0)?.name}"
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

    private fun showMarker(dataTourism: List<Tourism>?) {
        mapboxMap.setStyle(Style.MAPBOX_STREETS) { style ->
            style.addImage(
                ICON_ID,
                BitmapFactory.decodeResource(resources, R.drawable.mapbox_marker_icon_default)
            )
            val latLngBoundsBuilder = LatLngBounds.Builder()

            val symbolManager = SymbolManager(binding.mapView, mapboxMap, style)
            symbolManager.iconAllowOverlap = true

            val options = ArrayList<SymbolOptions>()
            dataTourism?.forEach { data ->
                latLngBoundsBuilder.include(LatLng(data.latitude, data.longitude))
                options.add(
                    SymbolOptions()
                        .withLatLng(LatLng(data.latitude, data.longitude))
                        .withIconImage(ICON_ID)
                        .withData(Gson().toJsonTree(data))
                )
            }
            symbolManager.create(options)
            val latLngBounds = latLngBoundsBuilder.build()
            mapboxMap.easeCamera(CameraUpdateFactory.newLatLngBounds(latLngBounds, 50), 5000)
            symbolManager.addClickListener { symbol ->
                val data = Gson().fromJson(symbol.data, Tourism::class.java)
                val intent = Intent(this, DetailTourismActivity::class.java)
                intent.putExtra(DetailTourismActivity.EXTRA_DATA, data)
                startActivity(intent)
            }
        }
    }
    companion object{
        private const val ICON_ID = "ICON_ID"
    }
}