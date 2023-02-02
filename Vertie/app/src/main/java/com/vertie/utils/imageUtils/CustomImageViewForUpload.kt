package com.vertie.utils.imageUtils

import android.content.Context
import android.util.AttributeSet
import android.view.ViewTreeObserver
import androidx.appcompat.widget.AppCompatImageView
import androidx.databinding.*

@InverseBindingMethods(InverseBindingMethod(type =
CustomImageViewForUpload::class,attribute = "measuredWidth1",event = "measuredWidth1AttrChanged",method = "getMeasuredWidth1"),
    InverseBindingMethod(type =
    CustomImageViewForUpload::class,attribute = "measuredHeight1",event = "measuredHeight1AttrChanged",method = "getMeasuredHeight1"))
class CustomImageViewForUpload : AppCompatImageView {

    var measuredHeight1: Int? = 0
    var measuredWidth1: Int? = 0

    constructor(context: Context) : this(context, null)
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
    }

    companion object {
        @JvmStatic
        @InverseBindingAdapter(attribute = "measuredWidth1")
        fun getMeasuredWidth1(view: CustomImageViewForUpload): Int? {
            return view.measuredWidth1
        }
        @JvmStatic
        @BindingAdapter("measuredWidth1")
        fun setMeasuredWidth1(view: CustomImageViewForUpload, newValue: Int?) {
            // Important to break potential infinite loops.
            if (view.measuredWidth1 != newValue) {
                view.measuredWidth1 = newValue
            }
        }
        @JvmStatic
        @BindingAdapter(value = ["measuredWidth1AttrChanged"])
        fun setWidthListener(view: CustomImageViewForUpload, measuredWidthAttrChanged: InverseBindingListener?) {
            if (measuredWidthAttrChanged != null) {
                view.viewTreeObserver.addOnPreDrawListener(object : ViewTreeObserver.OnPreDrawListener {
                    override fun onPreDraw(): Boolean {
                        view.viewTreeObserver.removeOnPreDrawListener(this)
                        view.measuredWidth1 = view.measuredWidth
                        measuredWidthAttrChanged.onChange()
                        return true
                    }
                })
            }
        }
        @JvmStatic
        @InverseBindingAdapter(attribute = "measuredHeight1")
        fun getMeasuredHeight1(view: CustomImageViewForUpload): Int? {
            return view.measuredHeight1
        }
        @JvmStatic
        @BindingAdapter("measuredHeight1")
        fun setMeasuredHeight1(view: CustomImageViewForUpload, newValue: Int?) {
            // Important to break potential infinite loops.
            if (view.measuredHeight1 != newValue) {
                view.measuredHeight1 = newValue
            }
        }
        @JvmStatic
        @BindingAdapter(value = ["measuredHeight1AttrChanged"])
        fun setMeasuredHeight1Listener(view: CustomImageViewForUpload, measuredHeight1AttrChanged: InverseBindingListener?) {
            if (measuredHeight1AttrChanged != null) {
                view.viewTreeObserver.addOnPreDrawListener(object : ViewTreeObserver.OnPreDrawListener {
                    override fun onPreDraw(): Boolean {
                        view.viewTreeObserver.removeOnPreDrawListener(this)
                        view.measuredHeight1 = view.measuredHeight
                        measuredHeight1AttrChanged.onChange()
                        return true
                    }
                })
            }
        }
    }
}