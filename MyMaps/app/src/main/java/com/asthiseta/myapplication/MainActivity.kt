package com.asthiseta.myapplication

import android.annotation.SuppressLint
import android.graphics.BitmapFactory
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.view.View
import android.view.animation.BounceInterpolator
import android.widget.Button
import android.widget.Toast
import com.mapbox.android.core.permissions.PermissionsListener
import com.mapbox.android.core.permissions.PermissionsManager
import com.mapbox.api.directions.v5.models.DirectionsResponse
import com.mapbox.api.directions.v5.models.DirectionsRoute
import com.mapbox.geojson.Point
import com.mapbox.mapboxsdk.Mapbox
import com.mapbox.mapboxsdk.camera.CameraUpdateFactory
import com.mapbox.mapboxsdk.geometry.LatLng
import com.mapbox.mapboxsdk.location.LocationComponent
import com.mapbox.mapboxsdk.location.LocationComponentActivationOptions
import com.mapbox.mapboxsdk.location.LocationComponentOptions
import com.mapbox.mapboxsdk.location.modes.CameraMode
import com.mapbox.mapboxsdk.location.modes.RenderMode
import com.mapbox.mapboxsdk.maps.MapView
import com.mapbox.mapboxsdk.maps.MapboxMap
import com.mapbox.mapboxsdk.maps.Style
import com.mapbox.mapboxsdk.plugins.annotation.SymbolManager
import com.mapbox.mapboxsdk.plugins.annotation.SymbolOptions
import com.mapbox.services.android.navigation.ui.v5.NavigationLauncher
import com.mapbox.services.android.navigation.ui.v5.NavigationLauncherOptions
import com.mapbox.services.android.navigation.ui.v5.route.NavigationMapRoute
import com.mapbox.services.android.navigation.v5.navigation.NavigationRoute
import java.net.NoRouteToHostException

class MainActivity : AppCompatActivity() {

    private lateinit var mapView : MapView
    private lateinit var btnNavigation : Button
    private lateinit var mapboxMap : MapboxMap
    private lateinit var symbolManager: SymbolManager
    private lateinit var myLocation : LatLng
    private lateinit var permissionsManager: PermissionsManager
    private lateinit var locationComponent : LocationComponent
    private lateinit var navigationMapRoute : NavigationMapRoute
    private var currentRoute: DirectionsRoute? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Mapbox.getInstance(applicationContext, getString(R.string.mapbox_acces_token))
        setContentView(R.layout.activity_main)

        mapView = findViewById(R.id.mapView)
        btnNavigation = findViewById(R.id.btnNavigation)

        mapView.onCreate(savedInstanceState)
        mapView.getMapAsync {mapboxMap ->
            this.mapboxMap = mapboxMap
            mapboxMap.setStyle(Style.MAPBOX_STREETS){ style ->
                symbolManager = SymbolManager(mapView, mapboxMap, style)
                symbolManager.iconAllowOverlap = true

                style.addImage(
                    ICON_ID,
                    BitmapFactory.decodeResource(resources, com.mapbox.mapboxsdk.R.drawable.mapbox_marker_icon_default)
                )

                navigationMapRoute = NavigationMapRoute(
                    null,
                    mapView,
                    mapboxMap,
                    com.mapbox.services.android.navigation.ui.v5.R.style.NavigationMapRoute
                )

                showDicodingSpace()
                showMyLocation(style)
                addMarkerOnClick()
                showNavigation()
            }
        }
    }

    private fun requestRoute(origin: Point, destination: Point) {
        navigationMapRoute.updateRouteVisibilityTo(false)
        NavigationRoute.builder(this)
            .accessToken(getString(R.string.mapbox_acces_token))
            .origin(origin)
            .destination(destination)
            .build()
            .getRoute(object : retrofit2.Callback<DirectionsResponse> {
                override fun onResponse(
                    call: retrofit2.Call<DirectionsResponse>,
                    response: retrofit2.Response<DirectionsResponse>
                ) {
                    if (response.body() == null) {
                        Toast.makeText(
                            this@MainActivity,
                            "No routes found, make sure you set the right user and access token.",
                            Toast.LENGTH_SHORT
                        ).show()
                        return
                    } else if (response.body()?.routes()?.size == 0) {
                        Toast.makeText(this@MainActivity, "No routes found.", Toast.LENGTH_SHORT)
                            .show()
                        return
                    }

                    currentRoute = response.body()?.routes()?.get(0)

                    navigationMapRoute.addRoute(currentRoute)
                }

                override fun onFailure(call: retrofit2.Call<DirectionsResponse>, t: Throwable) {
                    Toast.makeText(this@MainActivity, "Error : $t", Toast.LENGTH_SHORT).show()
                }
            })
    }

    private fun showNavigation() {
        btnNavigation.setOnClickListener{
            val simulateRoute = true

            val options = NavigationLauncherOptions.builder()
                .directionsRoute(currentRoute)
                .shouldSimulateRoute(simulateRoute)
                .build()

            NavigationLauncher.startNavigation(this, options)
        }
    }

    private fun addMarkerOnClick() {
        mapboxMap.addOnMapClickListener {point ->
            symbolManager.deleteAll()
            symbolManager.create(
                SymbolOptions()
                    .withLatLng(LatLng(point.latitude, point.longitude))
                    .withIconImage(ICON_ID)
                    .withDraggable(true)
            )
            val destination = Point.fromLngLat(point.longitude, point.latitude)
            val origin = Point.fromLngLat(myLocation.longitude, myLocation.latitude)
            requestRoute(origin, destination)

            btnNavigation.visibility = View.VISIBLE
            true
        }
    }

    @SuppressLint("MissingPermission")
    private fun showMyLocation(style: Style) {
        if (PermissionsManager.areLocationPermissionsGranted(this)) {
            val locationComponentOptions = LocationComponentOptions.builder(this)
                .pulseEnabled(true)
                .pulseColor(Color.BLUE)
                .pulseAlpha(.4f)
                .pulseInterpolator(BounceInterpolator())
                .build()
            val locationComponentActivationOptions = LocationComponentActivationOptions
                .builder(this, style)
                .locationComponentOptions(locationComponentOptions)
                .build()
            locationComponent = mapboxMap.locationComponent
            locationComponent.activateLocationComponent(locationComponentActivationOptions)
            locationComponent.isLocationComponentEnabled = true
            locationComponent.cameraMode = CameraMode.TRACKING
            locationComponent.renderMode = RenderMode.COMPASS
            myLocation = LatLng(locationComponent.lastKnownLocation?.latitude as Double, locationComponent.lastKnownLocation?.longitude as Double)
            mapboxMap.animateCamera(CameraUpdateFactory.newLatLngZoom(myLocation, 12.0))
        } else {
            permissionsManager = PermissionsManager(object : PermissionsListener {
                override fun onExplanationNeeded(permissionsToExplain: MutableList<String>?) {
                    Toast.makeText(this@MainActivity, "Anda harus mengizinkan location permission untuk menggunakan aplikasi ini", Toast.LENGTH_SHORT).show()
                }
                override fun onPermissionResult(granted: Boolean) {
                    if (granted) {
                        mapboxMap.getStyle { style ->
                            showMyLocation(style)
                        }
                    } else {
                        finish()
                    }
                }
            })
            permissionsManager.requestLocationPermissions(this)
        }
    }


    private fun showDicodingSpace() {
        val dicodingSpace = LatLng(-6.8957643, 107.6338462)
        symbolManager.create(
            SymbolOptions()
                .withLatLng(LatLng(dicodingSpace.latitude, dicodingSpace.longitude))
                .withIconImage(ICON_ID)
                .withIconSize(1.5f)
                .withIconOffset(arrayOf(0f, -1.5f))
                .withTextField("Dicoding Space")
                .withTextHaloColor("rgba(255, 105, 255, 100)")
                .withTextHaloWidth(0.5f)
                .withTextAnchor("top")
                .withDraggable(true)
        )
        mapboxMap.moveCamera(CameraUpdateFactory.newLatLngZoom(dicodingSpace, 8.0))
    }

    override fun onStart() {
        super.onStart()
        mapView.onStart()
    }

    override fun onResume() {
        super.onResume()
        mapView.onResume()
    }

    override fun onPause() {
        super.onPause()
        mapView.onPause()
    }

    override fun onStop() {
        super.onStop()
        mapView.onStop()
    }

    override fun onSaveInstanceState(outState: Bundle, outPersistentState: PersistableBundle) {
        super.onSaveInstanceState(outState, outPersistentState)
        mapView.onSaveInstanceState(outState)
    }

    override fun onDestroy() {
        super.onDestroy()
        mapView.onDestroy()
    }

    override fun onLowMemory() {
        super.onLowMemory()
        mapView.onLowMemory()
    }

    companion object {
        private const val ICON_ID = "ICON_ID"
    }
}