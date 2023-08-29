import android.app.DownloadManager;
import android.content.Context;
import android.net.Uri;
import android.os.Environment;
import android.util.Log;
import android.widget.ProgressBar;

public class DownloadFileFileAsyncTask {

    private static final String TAG = "DownloadFile";

    public static void downloadFile(Context context, String url, String fileName) {
        // Create a download request
        DownloadManager.Request request = new DownloadManager.Request(Uri.parse(url));

        // Set the download destination
        String destinationPath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS) + "/" + fileName;
        request.setDestinationUri(Uri.parse(destinationPath));

        // Set the download notification title
        request.setTitle("Downloading file");

        // Set the download notification description
        request.setDescription("Download in progress");

        // Set the visibility of the download notification
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE);

        // Enqueue the download
        DownloadManager downloadManager = (DownloadManager) context.getSystemService(Context.DOWNLOAD_SERVICE);
        long downloadId = downloadManager.enqueue(request);

        // Create a progress bar
        ProgressBar progressBar = new ProgressBar(context);
        progressBar.setMax(100);

        // Listen for download progress updates
        downloadManager.openDownloadedFile(new DownloadManager.OnDownloadListener() {
            @Override
            public void onDownloadProgress(DownloadManager downloadManager, long downloadId, int progress, long total) {
                Log.d(TAG, "Download progress: " + progress + "/" + total);
                progressBar.setProgress(progress);
            }

            @Override
            public void onDownloadCompleted(DownloadManager downloadManager, long downloadId, String path) {
                Log.d(TAG, "Download completed");
            }
        });
    }
}