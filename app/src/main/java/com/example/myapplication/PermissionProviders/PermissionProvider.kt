package com.example.myapplication.PermissionProviders

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import androidx.core.content.ContextCompat

fun getLocationCoarsePermissionStatus(context: Context) = ContextCompat.checkSelfPermission(
        context,
        Manifest.permission.ACCESS_COARSE_LOCATION
) != PackageManager.PERMISSION_GRANTED

fun getLocationFinePermissionStatus(context: Context) = ContextCompat.checkSelfPermission(
        context,
        Manifest.permission.ACCESS_FINE_LOCATION
) != PackageManager.PERMISSION_GRANTED