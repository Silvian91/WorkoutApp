package com.rosianu.lib_image_loader

import android.content.SharedPreferences
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Base64
import com.rosianu.lib_image_loader.Source.FILE_SYSTEM
import com.rosianu.lib_image_loader.Source.KEY_VALUE_STORAGE
import com.rosianu.lib_image_loader.extensions.convertToByteArray
import io.reactivex.Completable
import java.io.File
import java.nio.charset.Charset
import javax.inject.Inject

class ImageLoader @Inject constructor(
    private var sharedPreferences: SharedPreferences
) {

    fun loadImage(
        imageKey: String
    ): Bitmap? {
        when {
            sharedPreferences.contains(imageKey) -> {
                val byteArray = Base64.decode(
                    sharedPreferences.getString(imageKey, "null")!!,
                    Base64.DEFAULT
                )
                return BitmapFactory.decodeByteArray(
                    byteArray,
                    0,
                    byteArray.size
                )
            }
            File("$PROFILE_IMAGE_FILE$imageKey").exists() -> {
                val byteArray = Base64.decode(
                    File("$PROFILE_IMAGE_FILE$imageKey").readText(),
                    Base64.DEFAULT
                )
                return BitmapFactory.decodeByteArray(
                    byteArray,
                    0,
                    byteArray.size
                )
            }
            else -> {
                return null
            }
        }
    }

    fun storeImage(
        imageBitmap: Bitmap,
        source: Source,
        imageKey: String
    ): Completable {
        val imageValue = convertToString(imageBitmap)
        return when (source) {
            KEY_VALUE_STORAGE -> {
                Completable.fromCallable {
                    sharedPreferences.edit().putString(imageKey, imageValue).apply()
                }
            }
            FILE_SYSTEM -> {
                val charset = Charset.defaultCharset()
                Completable.fromCallable {
                    File("$PROFILE_IMAGE_FILE$imageKey").writeText(imageValue, charset)
                }
            }
        }
    }

    private fun convertToString(imageBitmap: Bitmap): String {
        val byteArray = imageBitmap.convertToByteArray()
        return Base64.encodeToString(byteArray, Base64.DEFAULT)
    }

    companion object {
        private const val PROFILE_IMAGE_FILE = "/data/data/com.example.workoutnotebook.debug/files"
    }

}