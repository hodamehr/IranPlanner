package com.iranplanner.tourism.iranplanner.ui.downloader;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Environment;
import android.util.Log;

import org.wlf.filedownloader.DownloadFileInfo;
import org.wlf.filedownloader.FileDownloadConfiguration;
import org.wlf.filedownloader.FileDownloader;
import org.wlf.filedownloader.listener.OnFileDownloadStatusListener;
import org.wlf.filedownloader.listener.simple.OnSimpleFileDownloadStatusListener;

import java.io.File;

/**
 * Created by MrCode on 9/19/17.
 */

public class FileDownloadHelper {

    private Context context;

    private int notificationId = 101;

    NotificationManager notificationManager;
    Notification.Builder notificationBuilder;


    private static final String TAG = FileDownloadHelper.class.getSimpleName();

    public FileDownloadHelper(Context context) {
        this.context = context;
        // 1.create FileDownloadConfiguration.Builder
        FileDownloadConfiguration.Builder builder = new FileDownloadConfiguration.Builder(context);

        // 2.config FileDownloadConfiguration.Builder
        builder.configFileDownloadDir(Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator +
                "FileDownloader"); // config the download path
        // allow 3 download tasks at the same time, if not config, default is 2
        builder.configDownloadTaskSize(2);
        // config retry download times when failed, if not config, default is 0
        builder.configRetryDownloadTimes(3);
        // enable debug mode, if not config, default is false
        builder.configDebugMode(true);
        // config connect timeout, if not config, default is 15s
        builder.configConnectTimeout(25000); // 25s

        // 3.init FileDownloader with the configuration
        FileDownloadConfiguration configuration = builder.build(); // build FileDownloadConfiguration with the builder
        FileDownloader.init(configuration);

        initNotificationManager();

        registerDownloaderListener();
    }

    private void initNotificationManager() {
        notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationBuilder = new Notification.Builder(context);

        Notification notification = notificationBuilder
                .setContentTitle("this is a title")
                .setContentText("this is content text in here dude").build();

        notificationManager.notify(notificationId, notification);

    }

    public void downloadFile(String url) {
        FileDownloader.start(url);
    }

    public void pauseDownload(String url) {
        FileDownloader.pause(url);
    }

    private void registerDownloaderListener() {
        FileDownloader.registerDownloadStatusListener(mOnFileDownloadStatusListener);
    }

    private OnFileDownloadStatusListener mOnFileDownloadStatusListener = new OnSimpleFileDownloadStatusListener() {
        @Override
        public void onFileDownloadStatusRetrying(DownloadFileInfo downloadFileInfo, int retryTimes) {
            // retrying download when failed once, the retryTimes is the current trying times
        }

        @Override
        public void onFileDownloadStatusWaiting(DownloadFileInfo downloadFileInfo) {
            // waiting for download(wait for other tasks paused, or FileDownloader is busy for other operations)
        }

        @Override
        public void onFileDownloadStatusPreparing(DownloadFileInfo downloadFileInfo) {
            // preparing(connecting)
        }

        @Override
        public void onFileDownloadStatusPrepared(DownloadFileInfo downloadFileInfo) {
            // prepared(connected)
        }

        @Override
        public void onFileDownloadStatusDownloading(DownloadFileInfo downloadFileInfo, float downloadSpeed, long
                remainingTime) {
            // downloading, the downloadSpeed with KB/s unit, the remainingTime with seconds unit
            Log.e(TAG, String.valueOf(downloadFileInfo.getDownloadedSizeLong()));
        }

        @Override
        public void onFileDownloadStatusPaused(DownloadFileInfo downloadFileInfo) {
            // download paused
        }

        @Override
        public void onFileDownloadStatusCompleted(DownloadFileInfo downloadFileInfo) {
            // download completed(the url file has been finished)
            Log.e(TAG, "Download Completed");
        }

        @Override
        public void onFileDownloadStatusFailed(String url, DownloadFileInfo downloadFileInfo, OnFileDownloadStatusListener.FileDownloadStatusFailReason failReason) {
            // error occur, see failReason for details, some of the failReason you must concern

            String failType = failReason.getType();
            String failUrl = failReason.getUrl();// or failUrl = url, both url and failReason.getUrl() are the same

            if (OnFileDownloadStatusListener.FileDownloadStatusFailReason.TYPE_URL_ILLEGAL.equals(failType)) {
                // the url error when downloading file with failUrl
            } else if (OnFileDownloadStatusListener.FileDownloadStatusFailReason.TYPE_STORAGE_SPACE_IS_FULL.equals(failType)) {
                // storage space is full when downloading file with failUrl
            } else if (OnFileDownloadStatusListener.FileDownloadStatusFailReason.TYPE_NETWORK_DENIED.equals(failType)) {
                // network access denied when downloading file with failUrl
            } else if (OnFileDownloadStatusListener.FileDownloadStatusFailReason.TYPE_NETWORK_TIMEOUT.equals(failType)) {
                // connect timeout when downloading file with failUrl
            } else {
                // more....
            }

            // exception details
            Throwable failCause = failReason.getCause();// or failReason.getOriginalCause()

            // also you can see the exception message
            String failMsg = failReason.getMessage();// or failReason.getOriginalCause().getMessage()
        }
    };

}
