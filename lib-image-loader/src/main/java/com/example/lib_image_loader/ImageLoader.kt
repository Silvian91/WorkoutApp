package com.example.lib_image_loader

import android.content.SharedPreferences
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Base64
import com.example.lib_image_loader.Source.FILE_SYSTEM
import com.example.lib_image_loader.Source.KEY_VALUE_STORAGE
import com.example.lib_image_loader.extensions.convertToByteArray
import io.reactivex.Completable
import java.io.File
import java.nio.charset.Charset

object ImageLoader {

    var imageBitmap: Bitmap? = null

    fun loadImage(
        sharedPreferences: SharedPreferences,
        imageKey: String
    ): Bitmap? {
        return when {
            sharedPreferences.contains(imageKey) -> {
                val byteArray = Base64.decode(
                    sharedPreferences.getString(imageKey, "null")!!,
                    Base64.DEFAULT
                )
                imageBitmap = BitmapFactory.decodeByteArray(
                    byteArray,
                    0,
                    byteArray.size
                )
                imageBitmap
            }
            File("$PROFILE_IMAGE_FILE$imageKey").exists() -> {
                val byteArray = Base64.decode(
                    File("$PROFILE_IMAGE_FILE$imageKey").readText(),
                    Base64.DEFAULT
                )
                imageBitmap = BitmapFactory.decodeByteArray(
                    byteArray,
                    0,
                    byteArray.size
                )
                imageBitmap
            }
            else -> {
                null
            }
        }
    }

    fun storeImage(
        imageBitmap: Bitmap,
        source: Source,
        sharedPreferences: SharedPreferences,
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

    private const val PROFILE_IMAGE_FILE = "/data/data/com.example.workoutapp.debug/files/"

}