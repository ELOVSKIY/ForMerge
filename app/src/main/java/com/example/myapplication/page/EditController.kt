package com.example.myapplication.page

import android.os.Bundle
import android.os.Parcelable
import android.util.Log
import android.view.View
import android.widget.TextView
import android.widget.Toast
import com.example.myapplication.models.Error
import java.util.*

abstract class EditController : PageViewController() {

    private val LOG_TAG2: String = "EditController"

    var innerObject: Any? = null

    companion object {
        const val DATA_OBJECT_KEY = "dataObject"
        const val DATA_OBJECT_LIST_KEY = "dataObjectList"
        var dataObject: Any? = null
        private val dataObjectList: ArrayList<Parcelable>? = null
        protected var errorList: MutableList<Error> = ArrayList()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(LOG_TAG2, "onCreate")
       // dataObject = intent.getParcelableExtra(DATA_OBJECT_KEY)
        innerObject = intent.getParcelableExtra(DATA_OBJECT_KEY)
        // Log.d(LOG_TAG2, "data: "+ dataObject.toString())

    }

    override fun onPause() {
        super.onPause()
        save(false)
    }

    override fun onResume() {
        super.onResume()
        load()
    }

    /**
     * Метод подгрузки данных
     * В этом методе UI форма заполняется из переменных
     */
    open fun load() {}

    /**
     * Метод сохранения данных
     * В этом методе сохраняются значениями UI формы с валидацией или без
     * validate = true - данные сохраняются с валидацией
     * validate = false - данные сохраняются без валидации
     * @param validate переменная отвечающая за тип сохранения данных
     */
    protected open fun save(validate: Boolean) {}

    /**
     * Метод сохранения данных с валидацией и отправкой на сервер
     * В этом методе сохраняются значениями UI формы с валидацией и отправкой на сервер
     */
    protected fun save() {
        if (hasErrors()) {
            handleErrors()
            return
        }
        save(true)
    }

    /**
     * Метод валидации
     * Заполняет errorList
     *
     */
    protected open fun validate(): Boolean {
        return false
    }


    /**
     * Метод проверяет на наличие ошибок после валидации
     *
     */
    protected fun hasErrors(): Boolean {
        clearPreValidate()
        validate()
        return errorList.isNotEmpty()
    }

    private fun clearPreValidate() {
        for (error in errorList) {
            if (error.idElement == null) continue
            val errorLabel: TextView = findViewById(error.idElement)
            errorLabel.visibility = View.GONE
        }
        errorList.clear()
    }

    protected fun makeToast(msg: String){
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }

    /**
     * Метод обработки
     * Обрабатывает массив ошибок
     *
     */
    protected fun handleErrors() {
        //        StringBuilder text = new StringBuilder();
        //        int index = 0;
        //        for (Error error
        //                : errorList) {
        //            index++;
        //            if (index != errorList.size())
        //                text.append(error.errorMessage).append("\r\n");
        //            else text.append(error.errorMessage);
        //        }
        //        Toast.makeText(this, text.toString(),Toast.LENGTH_SHORT).show();
        for (error in errorList) {
            val errorLabel: TextView = findViewById(error.idElement)
            errorLabel.setText(error.errorMessage)
            errorLabel.setVisibility(View.VISIBLE)
        }
    }
}