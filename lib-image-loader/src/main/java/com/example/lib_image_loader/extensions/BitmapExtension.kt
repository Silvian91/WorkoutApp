package com.example.lib_image_loader.extensions

import android.graphics.Bitmap
import java.io.ByteArrayOutputStream

fun Bitmap.convertToByteArray(): ByteArray {
    val byteArrayOutputStream = ByteArrayOutputStream()
    this.compress(
        Bitmap.CompressFormat.PNG,
        DEFAULT_IMAGE_QUALITY, byteArrayOutputStream)
    val byteArray = byteArrayOutputStream.toByteArray()
    byteArrayOutputStream.flush()
    byteArrayOutputStream.close()
    return byteArray
}

private const val DEFAULT_IMAGE_QUALITY = 100
