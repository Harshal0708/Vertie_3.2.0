package com.vertie.utils.imageUtils

import android.content.ContentResolver
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.ImageDecoder
import android.graphics.ImageDecoder.OnHeaderDecodedListener
import android.graphics.Matrix
import android.media.ExifInterface
import android.net.Uri
import android.os.Build
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileOutputStream
import java.io.IOException


/**
 * Created by sarahussien on 1/11/17.
 */

object ImageUploaderUtils {

    private val TAG = this.javaClass.simpleName

    private fun calculateInSampleSize(outWidth: Int, outHeight:Int,
                                      reqWidth: Int, reqHeight: Int): Int {
        // Raw height and width of image
        var inSampleSize = 1
        if (outHeight > reqHeight || outWidth > reqWidth) {
            val halfHeight = outHeight / 2
            val halfWidth = outWidth / 2
            while (halfHeight / inSampleSize > reqHeight && halfWidth / inSampleSize > reqWidth) {
                inSampleSize *= 2
            }
        }
        return inSampleSize
    }

    fun decodeSampledBitmapFromResource(strPath: String, imageUri: Uri, contentResolver: ContentResolver,
                                        reqWidth: Int, reqHeight: Int): Bitmap? {
        var bitmap: Bitmap?
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            val listener = OnHeaderDecodedListener {
                decoder, info, _ ->
                decoder.setTargetSampleSize(calculateInSampleSize(info.size.width, info.size.height, reqWidth, reqHeight))
            }
            bitmap = ImageDecoder.decodeBitmap(
                    ImageDecoder.createSource(
                            contentResolver,
                            imageUri), listener)

        } else {
            bitmap = contentResolver
                    .openInputStream(imageUri)?.use { inputStream ->
                        val options = BitmapFactory.Options()
                        options.inSampleSize = calculateInSampleSize(options.outWidth, options.outHeight, reqWidth,
                                reqHeight) // factor for size elimination
                        options.inJustDecodeBounds = false
                        BitmapFactory.decodeStream(inputStream, null, options)
                    }
        }
        //TODO: Fix ExifInterface
        try {
            // check of default phone rotation
            val ei: ExifInterface?
            ei = ExifInterface(strPath)
            when (ei.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_NORMAL)) {
                ExifInterface.ORIENTATION_ROTATE_90 -> bitmap = rotateImage(bitmap, 90)
                ExifInterface.ORIENTATION_ROTATE_180 -> bitmap = rotateImage(bitmap, 180)
                ExifInterface.ORIENTATION_ROTATE_270 -> bitmap = rotateImage(bitmap, 270)
                else -> {
                }
            }
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return bitmap
    }

    private fun rotateImage(img: Bitmap?, degree: Int): Bitmap {
        val matrix = Matrix()
        matrix.postRotate(degree.toFloat())
        val rotatedImg = Bitmap.createBitmap(img!!, 0, 0, img.width, img.height, matrix, true)
        img.recycle()
        return rotatedImg
    }

    private fun encodeAndCompressImage(source: Bitmap): ByteArray {
        val stream = ByteArrayOutputStream()
        source.compress(Bitmap.CompressFormat.JPEG, 70, stream)
        return stream.toByteArray()
    }

    private const val FILE = "file"
    private const val IMAGE_MEDIA_TYPE = "image/*"
    fun getImageFileFromPath(cashDir: File, path: String, reqWidth: Int, reqHeight: Int, imageUri: Uri, contentResolver: ContentResolver): MultipartBody.Part? {
        //create a file to write bitmap data
        val newFile = File(cashDir, "Img.JPG")
        newFile.createNewFile()
        //write the bytes in file
        val fos = FileOutputStream(newFile)
        fos.write(encodeAndCompressImage(decodeSampledBitmapFromResource(path, imageUri, contentResolver, reqWidth, reqHeight)!!))
        fos.flush()
        fos.close()
        return MultipartBody.Part.createFormData(
                FILE, newFile.name, RequestBody.create(
                MediaType.parse(IMAGE_MEDIA_TYPE), newFile
        )
        )
    }
}
