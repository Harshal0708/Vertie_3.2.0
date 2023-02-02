package com.vertie.utils

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager

/**
 * Created by sarahussien on 2/22/19.
 */
object ActivityUtils {

    fun addFragmentToActivity(
            fragmentManager: FragmentManager,
            fragment: Fragment, frameId: Int, addToBackSack: Boolean,
            bundle: Bundle? = null
    ) {
        val transaction = fragmentManager.beginTransaction()

        transaction.add(frameId, fragment)
        if (bundle != null) {
            fragment.arguments = bundle
        }
        if (addToBackSack) {
            transaction.addToBackStack(null)
        }
        transaction.commit()
    }

    fun replaceFragmentToActivity(
            fragmentManager: FragmentManager,
            fragment: Fragment, frameId: Int, addToBackSack: Boolean,
            bundle: Bundle? = null
    ) {
        val transaction = fragmentManager.beginTransaction()
        transaction.replace(frameId, fragment)
        if (addToBackSack) {
            transaction.addToBackStack(null)
        }
        if (bundle != null) {
            fragment.arguments = bundle
        }
        transaction.commit()
    }

}
