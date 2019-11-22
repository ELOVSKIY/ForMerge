package com.example.myapplication.page

import android.content.Intent
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.os.Parcelable
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.FrameLayout
import android.widget.TextView
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.example.myapplication.R
import com.example.myapplication.models.ToolbarButton
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import java.util.ArrayList
import kotlin.coroutines.CoroutineContext

open class PageViewController : AppCompatActivity(), CoroutineScope {
    private lateinit var job: Job
    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Main

    private val LOG_TAG = "CommonActivity"

    private var view: View? = null
    private var contentFrame: FrameLayout? = null
    private var toolbar: Toolbar? = null
    private var textViewSteping: TextView? = null
    private var textViewDescr: TextView? = null
    val toolbarButtonList = ArrayList<ToolbarButton>()

    override fun onStart() {
        super.onStart()
        Log.d(LOG_TAG, "onStart")
        init()
        setListeners()
    }

    override fun onPause() {
        super.onPause()
        Log.d(LOG_TAG, "onPause")
    }

    override fun onResume() {
        super.onResume()
        Log.d(LOG_TAG, "onResume")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(LOG_TAG, "onDestroy")
        job.cancel()
    }

    override fun onBackPressed() {
        super.onBackPressed()
        Log.d(LOG_TAG, "onBackPressed")
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        Log.d(LOG_TAG, "onActivityResult")
    }

    /**
     * Метод инициализации
     * В нем инициализируются все необходимые переменные(View,Model и т.д.)
     * Прописываются обработчики событий
     */
    open fun init() {}

    open fun setListeners() {}

    override fun onCreate(savedInstanceState: Bundle?) {

        super.setContentView(R.layout.controller_view_page)

        contentFrame = findViewById(R.id.contentFrameActivityWithToolbar)
        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        textViewSteping = findViewById(R.id.textViewStep)
        textViewDescr = findViewById(R.id.textViewStepDescription)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        toolbar!!.setNavigationIcon(R.drawable.ic_back)

        super.onCreate(savedInstanceState)
        Log.d(LOG_TAG, "onCreate")

        job = Job() // for coroutines
    }

    override fun setContentView(@LayoutRes layoutResID: Int) {
        view = layoutInflater.inflate(layoutResID, null)
        contentFrame?.addView(view)
    }

    fun addTextView(textView: String, textViewDesc: String) {
        textViewSteping?.visibility = View.VISIBLE
        textViewDescr?.visibility = View.VISIBLE
        textViewSteping?.text = textView
        textViewDescr?.text = textViewDesc
    }

    protected fun getView(): View? {
        return view
    }

    fun showToolbar() {
        toolbar!!.visibility = View.VISIBLE
    }

    fun hideToolbar() {
        toolbar!!.visibility = View.GONE
    }

    protected fun addToolbarButton(
        idButton: Int, textButton: String, iconButton: Drawable,
        clickListenerButton: MenuItem.OnMenuItemClickListener
    ) {
        toolbarButtonList.add(ToolbarButton(idButton, textButton, iconButton, clickListenerButton))
        invalidateOptionsMenu()
    }

    protected fun startEditOrViewActivity(activityClass: Class<*>, `object`: Parcelable) {
        val intent = Intent(this, activityClass)
        intent.putExtra(EditController.DATA_OBJECT_KEY, `object`)
        startActivity(intent)
    }

    protected fun startEditOrViewActivityForResult(activityClass: Class<*>, `object`: Parcelable, requestCode: Int) {
        val intent = Intent(this, activityClass)
        intent.putExtra(EditController.DATA_OBJECT_KEY, `object`)
        startActivityForResult(intent, requestCode)
    }

    protected fun startEditOrViewActivityPosition(
        activityClass: Class<*>,
        `object`: Parcelable,
        count: Int
    ) {
        val intent = Intent(this, activityClass)
        intent.putExtra(EditController.DATA_OBJECT_KEY, `object`)
        intent.putExtra("position", count)
        startActivity(intent)
    }

    protected fun startEditOrViewActivityBoolean(
        activityClass: Class<*>,
        `object`: Parcelable,
        check: Boolean
    ) {
        val intent = Intent(this, activityClass)
        intent.putExtra(EditController.DATA_OBJECT_KEY, `object`)
        intent.putExtra("checking", check)
        startActivity(intent)
    }

    protected fun getIntentForEditOrViewActivity(
        activityClass: Class<*>,
        `object`: Parcelable
    ): Intent {
        val intent = Intent(this, activityClass)
        intent.putExtra(EditController.DATA_OBJECT_KEY, `object`)
        return intent
    }


    protected fun startEditOrViewActivity(
        activityClass: Class<*>,
        `object`: ArrayList<Parcelable>
    ) {
        val intent = Intent(this, activityClass)
        intent.putExtra(EditController.DATA_OBJECT_LIST_KEY, `object`)
        startActivity(intent)
    }

    protected fun setTitle(text: String) {
        (toolbar!!.findViewById(R.id.toolbar_title) as TextView).text = text
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    override fun onPrepareOptionsMenu(menu: Menu): Boolean {
        if (toolbarButtonList.isNotEmpty()) {
            for (toolbarButton in toolbarButtonList) {
                val newItem = menu.add(
                    Menu.NONE,
                    toolbarButton.idButton,
                    Menu.NONE,
                    toolbarButton.textButton
                )
                newItem.icon = toolbarButton.iconButton
                newItem.setShowAsActionFlags(MenuItem.SHOW_AS_ACTION_ALWAYS)
                newItem.setOnMenuItemClickListener(toolbarButton.clickListenerButton)
            }
        }
        super.onPrepareOptionsMenu(menu)
        return true
    }

    fun action(value: Any, typeAction: Int) {}
}
