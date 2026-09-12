package com.appbase.descargaota

import android.content.Context
import android.app.DownloadManager
import android.net.Uri
import android.os.Environment
import androidx.work.Worker
import androidx.work.WorkerParameters
import androidx.work.Data
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class OtaDownloadWorker @Inject constructor(
    private val context: Context,
    params: WorkerParameters
) : Worker(context, params) {

    override fun doWork(): Result {
        val appVersion = inputData.getString("app_version") ?: "1.0"
        val downloadUrl = inputData.getString("download_url") ?: return Result.failure()

        val request = android.app.DownloadManager.Request(Uri.parse(downloadUrl))
            .setTitle("AppBase OTA Update")
            .setDescription("Descargando versión $appVersion")
            .setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, "appbase-$appVersion.apk")
            .setNotificationVisibility(android.app.DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)

        val manager = context.getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
        val reference = enqueue(request)

        return Result.success(Data.Builder().putLong("download_id", reference).build())
    }
}
