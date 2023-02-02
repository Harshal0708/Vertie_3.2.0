package com.vertie.modules.base

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import com.vertie.Constants
import com.vertie.R
import com.vertie.utils.LocaleHelper
import com.vertie.utils.SnackBarUtils
import dagger.android.support.DaggerAppCompatActivity
import java.util.*


abstract class BaseActivity : DaggerAppCompatActivity() {
    private val errorHandlingReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            showMessage(intent.getStringExtra(Constants.ERROR_BROADCAST_EXTRA_MESSAGE) ?: "")
        }
    }
    private val refreshReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            refresh()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        registerReceiver(
                refreshReceiver,
                IntentFilter(Constants.BROADCAST_REFRESH)
        )
        registerReceiver(
                errorHandlingReceiver,
                IntentFilter(Constants.ERROR_BROADCAST_KEY)
        )
    }

    override fun attachBaseContext(newBase: Context?) {
        var context = newBase
        if (newBase != null) {
            context = LocaleHelper.onAttach(newBase)
        }
        super.attachBaseContext(context)
    }
    override fun onDestroy() {
        unregisterReceiver(refreshReceiver)
        unregisterReceiver(errorHandlingReceiver)
        super.onDestroy()
    }



    fun getCurrentLocale(): Locale {
        val lang = LocaleHelper.getLanguage(this)
        return Locale(lang)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                return true
            }

            else -> return super.onOptionsItemSelected(item)
        }
    }

    open fun bundleToHashMap(extras: Bundle): HashMap<String, String> {
        val map: HashMap<String, String> = HashMap()
        val ks = extras.keySet()
        val iterator: Iterator<String> = ks.iterator()
        while (iterator.hasNext()) {
            val key = iterator.next()
            if (extras.getString(key) != null) {
                map[key] = extras.getString(key)!!
            }
        }
        return map
    }

    fun setToolBar(toolBar: Toolbar, backResource: Int? = null, menuResource: Int? = null) {
        if (backResource != null) {
            toolBar.setNavigationIcon(backResource)
        }
        toolBar.setNavigationOnClickListener {
            onBackPressed()
        }
        if (menuResource != null) {
            toolBar.inflateMenu(menuResource)
        }

    }


    fun setupToolbar(
        toolbar: Toolbar,
        title: Int?,
        stringTitle: String?,
        navIcon: Int? = null,
        navAction: () -> Unit = { onBackPressed() },
        menu: Int? = null
    ): Toolbar {

        if (stringTitle != null)
            try {
                toolbar.findViewById<TextView>(R.id.customTitle).text = stringTitle
            } catch (e: Exception) {
                Log.e("Base Activity", "${e.localizedMessage ?: ""}")
            }
//            toolbar.title = stringTitle
        if (title != null)
            try {
                toolbar.findViewById<TextView>(R.id.customTitle).text = getString(title)
            } catch (e: Exception) {
                Log.e("Base Activity", "${e.localizedMessage ?: ""}")
            }
//        toolbar.setTitle(title)
        if (navIcon != null) {
            toolbar.setNavigationIcon(navIcon)
            toolbar.setNavigationOnClickListener {
                navAction.invoke()
            }
        }
        if (menu != null) {
//                toolbar.popupTheme = R.style.customPopupMenuStyle
            toolbar.inflateMenu(menu)
        }

        return toolbar
    }

    protected fun hideKeyboardIfShown() {
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        // Hide The keyboard if showing
        val view = currentFocus
        if (view != null) {
            imm.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }

    protected fun showMessage(message: String) {
        val snackBarUtils = SnackBarUtils.instance
        snackBarUtils.showMessage(this, message)
    }

    abstract fun refresh()
}