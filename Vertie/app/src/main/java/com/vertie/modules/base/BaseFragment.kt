package com.vertie.modules.base

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.Observer
import com.vertie.R
import com.vertie.utils.SnackBarUtils
import com.vertie.utils.viewUtils.showFragmentProgressBar
import dagger.android.support.DaggerFragment
import androidx.appcompat.widget.Toolbar


abstract class BaseFragment : DaggerFragment() {
    private val ACTION_WIFI_SETTINGS_FLAG = 0
    private var progressBar: ProgressBar? = null


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val viewModel = getViewModel()

        if (viewModel != null) {

            viewModel.toastMessage.observe(viewLifecycleOwner, Observer {
                showToastMessage(it)
            })
            viewModel.snackMessage.observe(viewLifecycleOwner, Observer {
                showMessage(it)
            })

            viewModel.loading.observe(viewLifecycleOwner, Observer { visible ->
                showProgressBar(visible)
            })
        }

    }

    protected fun showProgressBar(visible: Boolean) {
        progressBar = this.showFragmentProgressBar(progressBar, visible)
    }

    protected fun hideKeyboardIfShown() {
        if (activity != null) {
            val imm =
                    activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            // Hide The keyboard if showing
            val view = activity?.currentFocus
            if (view != null) {
                imm.hideSoftInputFromWindow(view.windowToken, 0)
            }
        }
    }

    @Suppress("DEPRECATION")
    protected fun isNetworkAvailable(): Boolean {
        if (activity != null) {
            val connectivityManager =
                    activity?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                val nw      = connectivityManager.activeNetwork ?: return false
                val actNw = connectivityManager.getNetworkCapabilities(nw) ?: return false
                return when {
                    actNw.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                    actNw.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                    //for other device how are able to connect with Ethernet
                    actNw.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
                    //for check internet over Bluetooth
                    actNw.hasTransport(NetworkCapabilities.TRANSPORT_BLUETOOTH) -> true
                    else -> false
                }
            } else {
                val networkInfo = connectivityManager.activeNetworkInfo
                return networkInfo != null && networkInfo.isConnected
            }
        }
        return false
    }

    protected fun showMessage(message: String) {
        val snackBarUtils = SnackBarUtils.instance
        snackBarUtils.showMessage(activity, message)
    }

    protected fun showMessage(message: Int) {
        val snackBarUtils = SnackBarUtils.instance
        val messageString = getString(message)
        snackBarUtils.showMessage(activity, messageString)
    }


    fun showToastMessage(message: String) {
        if (activity != null) {
            Toast.makeText(context, message, Toast.LENGTH_LONG).show()
        }
    }

    fun showToastMessage(message: Int) {
        if (activity != null) {
            Toast.makeText(context, getString(message), Toast.LENGTH_LONG).show()
        }
    }


    abstract fun refresh()

    abstract fun getViewModel(): BaseViewModel?

    fun setToolBar(toolBar: Toolbar, backResource: Int? = null, menuResource: Int? = null) {
        if (backResource != null) {
            toolBar.setNavigationIcon(backResource)
        }
        toolBar.setNavigationOnClickListener {
            activity?.onBackPressed()
        }
        if (menuResource != null) {
            toolBar.inflateMenu(menuResource)
        }
    }
}
