package com.vertie.utils.bindingUtils

import android.content.ContentResolver
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BlendMode
import android.graphics.BlendModeColorFilter
import android.graphics.PorterDuff
import android.graphics.drawable.Drawable
import android.net.Uri
import android.os.Build
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.view.ViewTreeObserver
import android.view.animation.Animation
import android.view.animation.Animation.AnimationListener
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.DrawableRes
import androidx.appcompat.widget.AppCompatEditText
import androidx.core.content.ContextCompat
import androidx.core.view.children
import androidx.databinding.BindingAdapter
import androidx.databinding.InverseBindingAdapter
import androidx.databinding.InverseBindingListener
import com.vertie.BuildConfig
import com.vertie.R
import com.vertie.utils.imageUtils.CustomImageViewForUpload
import com.vertie.utils.imageUtils.ImageUploaderUtils
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.github.razir.progressbutton.hideProgress
import com.github.razir.progressbutton.showProgress
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputLayout
import com.hbb20.CountryCodePicker
import javax.inject.Inject


class BindingAdapters @Inject constructor(private val applicationContext: Context) {

    @BindingAdapter(
        value = ["imageUrl", "error", "placeholder"],
        requireAll = false
    )
    fun loadImage(view: ImageView, imageUrl: String?, error: Drawable?, placeholder: Drawable?) {
        val requestOptions = if (error != null && placeholder != null) {
            RequestOptions().error(error).placeholder(placeholder)
        } else if (error != null) {
            RequestOptions().error(error).placeholder(error)
        } else if (placeholder != null) {
            RequestOptions().placeholder(placeholder).error(placeholder)
        } else {
            RequestOptions()
        }
        if (imageUrl?.contains("http") == true) {
            Glide.with(view.context).load(imageUrl)
                .apply(requestOptions)
                .into(view)
        } else {
            Glide.with(view.context).load(BuildConfig.IMAGE_BASE_URL + imageUrl)
                .apply(requestOptions)
                .into(view)
        }
    }

    @BindingAdapter(
        value = ["bitmap"],
        requireAll = false
    )
    fun loadBitmap(view: ImageView, bitmap: Bitmap?) {
        view.setImageBitmap(bitmap)
    }

    @BindingAdapter(
        value = ["imagePath", "contentResolver", "imageUri"],
        requireAll = true
    )
    fun loadBitmapFromFilePath(
        view: CustomImageViewForUpload,
        imagePath: String?,
        contentResolver: ContentResolver?,
        imageUri: Uri?
    ) {
        if (imagePath == null || imageUri == null || contentResolver == null) {
            return
        }
        view.viewTreeObserver.addOnPreDrawListener(object : ViewTreeObserver.OnPreDrawListener {
            override fun onPreDraw(): Boolean {
                view.viewTreeObserver.removeOnPreDrawListener(this)
                view.setImageBitmap(
                    ImageUploaderUtils.decodeSampledBitmapFromResource(
                        imagePath,
                        imageUri,
                        contentResolver,
                        view.measuredWidth,
                        view.measuredHeight
                    )
                )
                return true
            }
        })
    }


    @BindingAdapter(
        value = ["loading", "loadingText", "defaultText", "defaultIcon", "loadingColor"],
        requireAll = false
    )
    fun showLoadingButton(
        button: MaterialButton, loading: Boolean?,
        loadingText: String?, defaultText: String?,
        defaultIcon: Drawable?, loadingColor: Int?
    ) {

        if (loading != null) {
            if (loading) {
                button.icon = null
                button.isEnabled = false
                button.showProgress {
                    buttonText = loadingText
                    progressColor = loadingColor
                        ?: ContextCompat.getColor(button.context, R.color.white)
                }
            } else {
                button.isEnabled = true
                if (defaultIcon != null)
                    button.icon = defaultIcon
                button.hideProgress(defaultText)
            }
        } else {
            button.icon = defaultIcon
            button.text = defaultText
            button.isEnabled = true
        }
    }

    private fun setImageResourceWithAnimation(view: ImageView, drawableResource: Int) {
        val animOut: Animation =
            AnimationUtils.loadAnimation(applicationContext, android.R.anim.fade_out)
        val animIn: Animation =
            AnimationUtils.loadAnimation(applicationContext, android.R.anim.fade_in)
        animOut.setAnimationListener(object : AnimationListener {
            override fun onAnimationStart(animation: Animation) {}
            override fun onAnimationRepeat(animation: Animation) {}
            override fun onAnimationEnd(animation: Animation) {
                view.setImageResource(drawableResource)
                animIn.setAnimationListener(object : AnimationListener {
                    override fun onAnimationStart(animation: Animation) {}
                    override fun onAnimationRepeat(animation: Animation) {}
                    override fun onAnimationEnd(animation: Animation) {}
                })
                view.startAnimation(animIn)
            }
        })
        view.startAnimation(animOut)
    }

    @Suppress("DEPRECATION")
    @BindingAdapter("errorText")
    fun setErrorMessage(view: TextInputLayout, errorMessage: String?) {
        if (errorMessage != null) {
            view.error = errorMessage
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                view.background?.colorFilter =
                    BlendModeColorFilter(
                        ContextCompat.getColor(applicationContext, R.color.white),
                        BlendMode.SRC_ATOP
                    )
            } else {
                view.background?.setColorFilter(
                    ContextCompat.getColor(applicationContext, R.color.white),
                    PorterDuff.Mode.SRC_ATOP
                )
            }
            for (child in view.children) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                    child.background?.colorFilter =
                        BlendModeColorFilter(
                            ContextCompat.getColor(applicationContext, R.color.white),
                            BlendMode.SRC_ATOP
                        )
                } else {
                    child.background?.setColorFilter(
                        ContextCompat.getColor(applicationContext, R.color.white),
                        PorterDuff.Mode.SRC_ATOP
                    )
                }
            }
        }
    }

    @BindingAdapter("attachNextView")
    fun bindTextWatcher(currentText: AppCompatEditText, nextText: AppCompatEditText) {
        currentText.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                if (currentText.text.toString().length == 1) {
                    nextText.requestFocus()
                }
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }
        })
    }

    @BindingAdapter("customImageSrc")
    fun setCustomImageSource(view: ImageView, @DrawableRes imageSrc: Int?) {
        if (imageSrc != null) {
            view.setImageResource(imageSrc)
        } else {
            view.setImageResource(0)
        }
    }


    @BindingAdapter("textColorResource")
    fun setCustomColor(view: TextView, textColor: Int?) {
        if (textColor != null && textColor > 0) {
            view.setTextColor(ContextCompat.getColor(view.context, textColor))
        }
    }

}

object CountryCodeAdapter {
    @BindingAdapter(
            value = ["selectedCountryCode", "selectedCountryCodeAttrChanged"],
            requireAll = false
    )
    @JvmStatic
    fun selectCountryByCode(
            countryCodePicker: CountryCodePicker,
            countryCode: String?,
            listener: InverseBindingListener?
    ) {
        countryCodePicker.setOnCountryChangeListener {
            listener?.onChange()
        }

        if (countryCode != null && countryCode != countryCodePicker.selectedCountryCode)
            countryCodePicker.setCountryForPhoneCode(countryCode.toInt())
    }


    @InverseBindingAdapter(
            attribute = "selectedCountryCode",
            event = "selectedCountryCodeAttrChanged"
    )
    @JvmStatic
    fun getCountryCode(countryCodePicker: CountryCodePicker): String {
        return countryCodePicker.selectedCountryCode
    }
}