package com.example.lib_image_loader

import android.content.Context
import android.content.SharedPreferences
import android.graphics.Bitmap
import android.util.Base64
import com.example.lib_image_loader.Source.FILE_SYSTEM
import com.example.lib_image_loader.Source.KEY_VALUE_STORAGE
import com.example.lib_image_loader.extensions.convertToByteArray
import io.reactivex.Completable
import java.io.File
import java.io.FileOutputStream

object ImageLoader {

    fun loadImage(path: String) {}

    fun storeImage(
        imageBitmap: Bitmap,
        src: Source,
        sharedPreferences: SharedPreferences,
        imageKey: String,
        context: Context
    ): Completable {
        val imageValue = convertToString(imageBitmap)
        return when (src) {
            KEY_VALUE_STORAGE -> {
                Completable.fromCallable {
                    sharedPreferences.edit().putString(imageKey, imageValue).apply()
                }
            }
            FILE_SYSTEM -> {
                Completable.fromCallable {
                    context.openFileOutput(imageKey, Context.MODE_PRIVATE).use {
                        it.write(imageBitmap.convertToByteArray())
                    }
                }
            }
        }
    }

    private fun convertToString(imageBitmap: Bitmap): String {
        val byteArray = imageBitmap.convertToByteArray()
        return Base64.encodeToString(byteArray, Base64.DEFAULT)
    }

}