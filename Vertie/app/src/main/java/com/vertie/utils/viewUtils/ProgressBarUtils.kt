package com.vertie.utils.viewUtils

import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.RelativeLayout
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.github.ybq.android.spinkit.sprite.Sprite
import com.github.ybq.android.spinkit.style.Circle
import com.vertie.R

fun Fragment?.showFragmentProgressBar(optionalProgressBar: ProgressBar?, visible: Boolean): ProgressBar? {
    var progressBar = optionalProgressBar
    if (this == null) {
        return progressBar
    }
    if (progressBar == null) {
        progressBar = ProgressBar(this.activity, null, android.R.attr.progressBarStyleSmall)
        val circle: Sprite = Circle()
        circle.color =  ContextCompat.getColor(this.requireContext(), R.color.colorPrimary)
        progressBar.isIndeterminate = true
        progressBar.indeterminateDrawable = circle
        progressBar.progress
        val layout = RelativeLayout(this.activity)
        layout.layoutParams =
            RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.MATCH_PARENT,
                RelativeLayout.LayoutParams.MATCH_PARENT
            )
        val params = RelativeLayout.LayoutParams(60, 60)
        params.addRule(RelativeLayout.CENTER_IN_PARENT)
        progressBar.tag = 1000
        layout.addView(progressBar, params)
        (this.view?.parent as? ViewGroup)?.addView(layout)
    }
    if (visible) {
        progressBar.visibility = View.VISIBLE
    } else {
        progressBar.visibility = View.GONE
    }
    return progressBar
}