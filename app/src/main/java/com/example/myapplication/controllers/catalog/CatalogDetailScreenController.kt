package com.example.myapplication.controllers.catalog

import android.os.Bundle
import com.example.myapplication.R
import com.example.myapplication.page.PageViewController

class CatalogDetailScreenController : PageViewController() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.catalog_detail_screen_controller)
    }
}
