package com.vault.calculator.util

import android.content.Context
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import java.io.File
import java.io.FileOutputStream

fun moveImageToVault(context: Context, uri: Uri): File? {
    return try {
        // 1. Copy to internal storage
        val inputStream = context.contentResolver.openInputStream(uri) ?: return null
        val file = File(context.filesDir, "${System.currentTimeMillis()}.jpg")
        val outputStream = FileOutputStream(file)
        inputStream.copyTo(outputStream)
        inputStream.close()
        outputStream.close()

        // 2. Delete the original from public storage
        deleteFromGallery(context, uri)

        file
    } catch (e: Exception) {
        println("DELETING")
        e.printStackTrace()
        null
    }
}

fun deleteFromGallery(context: Context, uri: Uri) {
    try {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            // Scoped storage delete
//            context.contentResolver.delete(uri, null, null)
            context.contentResolver.delete(uri, null)
        } else {
            // Legacy path delete
            val path = getRealPathFromUri(context, uri)
            path?.let {
                val file = File(it)
                if (file.exists()) {
                    file.delete()
                    context.contentResolver.notifyChange(uri, null)
                }
            }
        }
    } catch (e: Exception) {
        e.printStackTrace()
    }
}

fun getRealPathFromUri(context: Context, uri: Uri): String? {
    val projection = arrayOf(MediaStore.Images.Media.DATA)
    context.contentResolver.query(uri, projection, null, null, null)?.use { cursor ->
        val columnIndex = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA)
        if (cursor.moveToFirst()) {
            return cursor.getString(columnIndex)
        }
    }
    return null
}

// Load files (for display)
fun loadVaultImages(context: Context): List<File> {
    return context.filesDir.listFiles()?.toList() ?: emptyList()
}