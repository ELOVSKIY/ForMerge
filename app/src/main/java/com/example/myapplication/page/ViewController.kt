package com.example.myapplication.page

import android.os.Bundle
import androidx.annotation.Nullable

open class ViewController : PageViewController() {

    val DATA_OBJECT_KEY = "dataObject"
    val DATA_OBJECT_LIST_KEY = "dataObjectList"
    var dataObject = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dataObject = getIntent().getParcelableExtra(DATA_OBJECT_KEY)
    }

    override fun onPause() {
        super.onPause()
    }


    override fun onResume() {
        super.onResume()
        load()
    }

    /**
     * Метод получения объекта с которым работает форма
     *
     * @return экземпляр Object
     */
    fun getDataObject(): Any? {
        return dataObject
    }

    /**
     * Метод подгрузки данных
     * В этом методе UI форма заполняется из переменных
     */
    protected open fun load() {}
}
