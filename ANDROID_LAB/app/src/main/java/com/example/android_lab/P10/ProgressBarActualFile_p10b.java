package com.example.android_lab.P10;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.DownloadManager;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.SystemClock;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.example.android_lab.R;

public class ProgressBarActualFile_p10b extends AppCompatActivity {

    private static final int PERMISSION_REQUEST_CODE = 1;
    private static final String FILE_URL = "https://drive.google.com/uc?id=1_OMKYQDOXluIhYxfjXXaB3T8Z0SsGhd4&export=download";
    private ProgressBar progressBar;
    private TextView progressText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progress_bar_actual_file_p10b);

        // Find the ProgressBar and the Download Button from the layout
        progressBar = findViewById(R.id.progressBar_p10b);
        Button downloadButton = findViewById(R.id.btn_p10b);
        progressText = findViewById(R.id.tv_p10b);

        // Set a click listener on the Download Button
        downloadButton.setOnClickListener(v -> {
            downloadButton.setEnabled(false);
            // Check if WRITE_EXTERNAL_STORAGE permission is granted
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    != PackageManager.PERMISSION_GRANTED) {
                // Request the permission if not granted
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                        PERMISSION_REQUEST_CODE);
            } else {
                // Start the download if permission is already granted
                startDownload();
            }
        });
    }

    @SuppressLint("Range")
    private void startDownload() {
        // Create a download request with the specified file URL
        String fileName = "JAVA.pdf";
        DownloadManager.Request request = new DownloadManager.Request(Uri.parse(FILE_URL));
        request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI | DownloadManager.Request.NETWORK_MOBILE)
                .setTitle("Downloading File")
                .setDescription("Downloading...")
                .setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, fileName);

        // Get a reference to the DownloadManager system service
        DownloadManager downloadManager = (DownloadManager) getSystemService(Activity.DOWNLOAD_SERVICE);

        // Enqueue the download request and get the download ID
        long downloadId = downloadManager.enqueue(request);

        // Create a query to retrieve download information using the download ID
        DownloadManager.Query query = new DownloadManager.Query();
        query.setFilterById(downloadId);
        progressBar.setVisibility(ProgressBar.VISIBLE);

        // Start a separate thread to track the download progress
        new Thread(() -> {
            boolean downloading = true;
            while (downloading) {
                SystemClock.sleep(1000);
                Cursor cursor = downloadManager.query(query);
                cursor.moveToFirst();

                // Calculate the download progress
                @SuppressLint("Range") int bytesDownloaded = cursor.getInt(cursor.getColumnIndex(DownloadManager.COLUMN_BYTES_DOWNLOADED_SO_FAR));
                @SuppressLint("Range") int bytesTotal = cursor.getInt(cursor.getColumnIndex(DownloadManager.COLUMN_TOTAL_SIZE_BYTES));
                final int progress = (bytesDownloaded * 100) / bytesTotal;

                // Update the progress bar on the UI thread
                runOnUiThread(() -> progressBar.setProgress(progress));
                runOnUiThread(() -> progressText.setText(progress + "%"));
                if(progress == 100){
                    runOnUiThread(() -> progressText.setText(fileName + " Downloaded"));
                    runOnUiThread(() -> findViewById(R.id.btn_p10b).setEnabled(true));
                }

                // Check if the download is successful
                if (cursor.getInt(cursor.getColumnIndex(DownloadManager.COLUMN_STATUS)) == DownloadManager.STATUS_SUCCESSFUL) {
                    downloading = false;
                }

                // Close the cursor after use
                cursor.close();
            }
            progressBar.setVisibility(ProgressBar.GONE);
        }).start();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == PERMISSION_REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permission granted, start the download
                startDownload();
            } else {
                // Permission denied, show a toast message
                Toast.makeText(this, "Permission denied. Cannot download the file.", Toast.LENGTH_SHORT).show();
            }
        }

    }
}