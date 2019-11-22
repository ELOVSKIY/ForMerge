package com.example.myapplication.controllers.catalog

import android.content.Intent
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.adapters.GiftAdapter
import com.example.myapplication.adapters.SelectableArrayAdapter
import com.example.myapplication.page.EditController
import kotlinx.android.synthetic.main.activity_catalog_themes_controller.*

class CatalogThemesController : EditController(), SelectableArrayAdapter.OnChooseEventListener {
    override fun onChoose(eventName: String) {
        //startEditOrViewActivityForResult(CatalogDetailScreenController::class.java, )
        val intent = Intent(this, CatalogDetailScreenController::class.java)
        startActivity(intent)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_catalog_themes_controller)

    }

    override fun init() {
        showToolbar()
        val subThemeArray = resources.getStringArray(R.array.subThemeTest)
        subThemeRecycler.adapter = SelectableArrayAdapter( subThemeArray, this)
        subThemeRecycler.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        giftRecycler.adapter = GiftAdapter()
        giftRecycler.layoutManager =
            LinearLayoutManager(this)
    }
}
