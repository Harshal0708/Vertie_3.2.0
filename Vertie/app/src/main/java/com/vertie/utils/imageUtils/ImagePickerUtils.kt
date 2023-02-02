//package com.vertie.utils.imageUtils
//
//import android.app.Activity
//import android.content.pm.ActivityInfo
//import android.content.res.Resources
//import android.util.Log
//import androidx.fragment.app.Fragment
//import com.vertie.BuildConfig
//import com.vertie.R
//import com.vertie.utils.extensions.checkImagePickerPermission
//import com.vertie.utils.extensions.requestImagePickerPermission
//import com.zhihu.matisse.Matisse
//import com.zhihu.matisse.MimeType
//import com.zhihu.matisse.SelectionCreator
//import com.zhihu.matisse.engine.impl.GlideEngine
//import com.zhihu.matisse.internal.entity.CaptureStrategy
//
//private val TAG = "ImagePickerUtils"
//
//fun Fragment?.showImagePicker(REQUEST_CODE_CHOOSE: Int,maxNumberOfImages:Int = 1): SelectionCreator? {
//    if (this != null) {
//        if (!this.context.checkImagePickerPermission()) {
//            this.requestImagePickerPermission()
//        } else {
//            return showMatissePicker(Matisse.from(this), this.resources, REQUEST_CODE_CHOOSE,maxNumberOfImages)
//        }
//    }
//    return null
//}
//
//fun Activity?.showImagePicker(REQUEST_PERMISSION: Int, REQUEST_CODE_CHOOSE: Int): SelectionCreator? {
//    if (this != null) {
//        if (!this.checkImagePickerPermission()) {
//            this.requestImagePickerPermission(REQUEST_PERMISSION)
//        } else {
//            return showMatissePicker(Matisse.from(this), this.resources, REQUEST_CODE_CHOOSE)
//        }
//    }
//    return null
//}
//
//private fun showMatissePicker(matisse: Matisse, resources: Resources, REQUEST_CODE_CHOOSE: Int,maxNumberOfImages:Int = 1): SelectionCreator {
//    val selectionCreator = matisse.choose(MimeType.ofImage(), false) // images only
//        .capture(true) // enable camera
//        .captureStrategy(
//            CaptureStrategy(true, "${BuildConfig.APPLICATION_ID}.fileprovider", "test"))
//        .countable(true) // enable multi selection
//        .maxSelectable(maxNumberOfImages) // only one image limit
//        .gridExpectedSize(
//            resources.getDimensionPixelSize(R.dimen.dp120))
//        .restrictOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED)
//        .thumbnailScale(1.0f)
//        .imageEngine(GlideEngine())
//        .setOnSelectedListener { uriList, pathList ->
//            Log.d(TAG, "onSelected: pathList=$pathList")
//            Log.d(TAG, "onSelected: uriList=$uriList")
//        }
//        .showSingleMediaType(true)
//        .originalEnable(true)
//        .maxOriginalSize(10) // Max 10MB
//        .autoHideToolbarOnSingleTap(true)
//        .setOnCheckedListener { isChecked ->
//            Log.d(TAG, "onCheck: isChecked=$isChecked")
//        }
//    selectionCreator.forResult(REQUEST_CODE_CHOOSE)
//    return selectionCreator
//}