package com.example.myapplication.controllers.createEvent

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.location.Criteria
import android.location.LocationManager
import android.os.Bundle
import androidx.core.app.ActivityCompat
import com.example.myapplication.PermissionProviders.getLocationFinePermissionStatus
import com.example.myapplication.R
import com.example.myapplication.models.EventModel
import com.example.myapplication.page.EditController
import com.google.android.gms.common.api.Status
import com.google.android.gms.maps.*
import com.google.android.gms.maps.model.CameraPosition
import kotlinx.android.synthetic.main.create_event_choose_place_controller.*
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.libraries.places.api.Places
import com.google.android.libraries.places.api.model.Place
import com.google.android.libraries.places.widget.AutocompleteSupportFragment
import com.google.android.libraries.places.widget.listener.PlaceSelectionListener

@Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
class CreateEventChoosePlaceController : EditController(), OnMapReadyCallback {
    private val LOCATION_REQUEST_CODE = 1312


    private var map: GoogleMap? = null
    private var locationManager: LocationManager? = null
    lateinit var complete: AutocompleteSupportFragment

    override fun onMapReady(googleMap: GoogleMap?) {
        val sydney = LatLng(55.7522, 37.6155)
        map = googleMap
        map?.moveCamera(CameraUpdateFactory.newLatLng(sydney))
        updateLocationUI()
    }


    @SuppressLint("MissingPermission")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.create_event_choose_place_controller)
        if (!Places.isInitialized()) {
            Places.initialize(applicationContext, "AIzaSyDcrOph1sg1RemEd-XkUavOdr1O5uf6WLg")
        }
        val mapFragment = supportFragmentManager
                .findFragmentById(R.id.map) as SupportMapFragment
        complete = supportFragmentManager
                .findFragmentById(R.id.complete) as AutocompleteSupportFragment
        complete.setPlaceFields(listOf(Place.Field.ID, Place.Field.NAME, Place.Field.ADDRESS,
                Place.Field.ADDRESS_COMPONENTS, Place.Field.LAT_LNG))
        mapFragment.getMapAsync(this)
        locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
    }

    override fun setListeners() {
        setSubmitListener()
        setCompleteListener()
    }

    private fun setCompleteListener() {
        complete.setOnPlaceSelectedListener(object : PlaceSelectionListener {
            override fun onPlaceSelected(p0: Place) {
                val latlng = p0.latLng
                if (latlng != null) {
                    val cameraPosition = CameraPosition.Builder()
                            .target(latlng)
                            .zoom(12.8f)
                            .build()
                    val cameraUpdate = CameraUpdateFactory.newCameraPosition(cameraPosition)
                    map?.moveCamera(cameraUpdate)
                    map?.clear()
                    map?.addMarker(MarkerOptions().position(latlng)
                            .title("Event place"))
                }
            }

            override fun onError(p0: Status) {
                makeToast(p0.toString())
            }
        })
    }

    private fun setSubmitListener() {
        submitButton.setOnClickListener {
            val eventBuilder = innerObject as EventModel.Builder
            startEditOrViewActivity(CreateEventAddMembersController::class.java, eventBuilder)
        }
    }

    private fun hasLocationPermission(): Boolean {
        return if (getLocationFinePermissionStatus(this)) {
            ActivityCompat.requestPermissions(this, arrayOf(
                    Manifest.permission.ACCESS_FINE_LOCATION
            ), LOCATION_REQUEST_CODE)
            false
        } else {
            return true
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        if (requestCode == LOCATION_REQUEST_CODE) {
            if (permissions.size == 1 &&
                    permissions[0] == Manifest.permission.ACCESS_FINE_LOCATION &&
                    grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                moveMapToMyLocation()
            } else {
                makeToast(resources.getString(R.string.location_error))
            }
        }
    }

    @SuppressLint("MissingPermission")
    private fun moveMapToMyLocation() {
        val crit = Criteria()
        if (locationManager != null) {
            val loc = locationManager?.getLastKnownLocation(
                    locationManager?.getBestProvider(crit, false))
            if (loc != null) {
                val latLng = LatLng(loc.latitude, loc.longitude)
                val cameraPosition = CameraPosition.Builder()
                        .target(latLng)
                        .zoom(12.8f)
                        .build()
                val cameraUpdate = CameraUpdateFactory.newCameraPosition(cameraPosition)
                map?.moveCamera(cameraUpdate)
                map?.addMarker(MarkerOptions().position(latLng)
                        .title("Event place"))
            }
        }
    }

    private fun updateLocationUI() {
        if (hasLocationPermission()) {
            map?.isMyLocationEnabled = true
            map?.uiSettings?.isMyLocationButtonEnabled = true
            moveMapToMyLocation()
        }
    }

    override fun onStart() {
        super.onStart()
        if (CreateEventNavigation.clearBackStack) {
            finish()
        }
    }

}
