package com.vertie.utils.bindingUtils

import android.view.View
import androidx.databinding.BindingConversion


/**
 * The view model visibility indicates by boolean while the visibility attribute takes an integer
 * (VISIBLE, GONE and INVISIBLE are 0, 4 and 8 respectively), so we use this converter.
 *
 * There is no need to specify that this converter should be used. [BindingConversion]s are
 * applied automatically.
 */

object BindingConverters {
    @BindingConversion
    @JvmStatic fun booleanToVisibility(isVisible: Boolean): Int {
        return if (isVisible) View.VISIBLE else View.GONE
    }
}