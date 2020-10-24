package com.example.lib_image_loader

import android.content.SharedPreferences
import android.graphics.Bitmap
import android.util.Base64
import com.example.lib_image_loader.extensions.convertToByteArray
import io.reactivex.Completable

object ImageLoader {

    fun loadImage(path: String) {}

    fun storeImage(imageBitmap: Bitmap,
                   src: Source,
                   sharedPreferences: SharedPreferences,
                   imageKey: String): Completable {
        val imageValue = convertToString(imageBitmap)
        return Completable.fromCallable{
            // Might be putString().commit
            sharedPreferences.edit().putString(imageKey, imageValue).apply()
        }
    }

    private fun convertToString(imageBitmap: Bitmap): String {
        val byteArray = imageBitmap.convertToByteArray()
        return Base64.encodeToString(byteArray, Base64.DEFAULT)
    }

}