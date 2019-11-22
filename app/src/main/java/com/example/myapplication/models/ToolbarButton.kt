package com.example.myapplication.models

import android.graphics.drawable.Drawable
import android.view.MenuItem

class ToolbarButton(
    val idButton: Int,
    val textButton: String,
    val iconButton: Drawable,
    val clickListenerButton: MenuItem.OnMenuItemClickListener
) {

}