package com.example.myapplication.utils

import android.content.Context
import android.location.Address
import android.location.Geocoder
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import java.io.IOException
import java.util.*

class FetchAddressIntentService(private val context: Context) {

    fun latLngToAddress(location: LatLng): String {
        var address = "Cannot define your address."
        val geocoder = Geocoder(context, Locale.getDefault())
        try {
            val addresses: List<Address> =
                geocoder.getFromLocation(location.latitude, location.longitude, 1)
            address = addresses[0].getAddressLine(0)
        } catch (ex: IOException) {
            ex.printStackTrace()
        }
        return address
    }

    fun addMarkerAndMoveCameraToIt(mMap: GoogleMap, location: LatLng, zoom: Float, markerIconId : Int) {
        mMap.addMarker(
            MarkerOptions()
                .position(location)
                .icon(BitmapDescriptorFactory.fromResource(markerIconId))
        )
        mMap.moveCamera(CameraUpdateFactory.newLatLng(location))
        mMap.animateCamera(CameraUpdateFactory.zoomTo(zoom))
    }

    fun getLocationFromAddress(context: Context, strAddress: String): LatLng? {
        val coder = Geocoder(context)
        val address: List<Address>?
        var p1: LatLng? = null

        try {
            address = coder.getFromLocationName(strAddress, 5)
            if (address == null) {
                return null
            }
            val location = address[0]
            location.latitude
            location.longitude

            p1 = LatLng(location.latitude, location.longitude)
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return p1
    }

}