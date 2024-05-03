package com.example.innowisepexelstestapp.repository

import android.content.ContentValues
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.provider.MediaStore
import android.webkit.URLUtil
import android.widget.Toast
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import java.io.File
import java.io.FileOutputStream
import java.io.InputStream
import java.net.HttpURLConnection
import java.net.URL

class ImageDownloaderImpl(private val context: Context) : ImageDownloader {

    override fun saveToGalleryImage(imageUrl: String): Single<Boolean> {
        //todo переделать этот бред
        return Single.fromCallable {
            try {
                val url = URL(imageUrl)
                val connection: HttpURLConnection = url.openConnection() as HttpURLConnection
                connection.doInput = true
                connection.connect()

                val inputStream: InputStream = connection.inputStream
                val bitmap = inputStream.use { stream ->
                    BitmapFactory.decodeStream(stream)
                }

                val displayName = URLUtil.guessFileName(imageUrl, null, null)
                val imageFile = File(context.getExternalFilesDir(null), displayName)

                FileOutputStream(imageFile).use { out ->
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 100, out)
                }

                // Save the image in the gallery
                val contentValues = ContentValues().apply {
                    put(MediaStore.Images.Media.DISPLAY_NAME, displayName)
                    put(MediaStore.Images.Media.MIME_TYPE, "image/jpeg")
                    put(MediaStore.Images.Media.RELATIVE_PATH, "Pictures")
                }

                val uri = context.contentResolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues)

                // Return the result of the download
                uri != null
            } catch (e: Exception) {
                e.printStackTrace()
                Toast.makeText(context, "false", Toast.LENGTH_SHORT).show()
                false
            }

        }.subscribeOn(Schedulers.io())
    }

}