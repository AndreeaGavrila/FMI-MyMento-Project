package com.example.mymentoapp;

import android.Manifest;
import android.app.DownloadManager;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.webkit.CookieManager;
import android.webkit.MimeTypeMap;
import android.webkit.URLUtil;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import org.jetbrains.annotations.NotNull;

public class DownloadActivity extends AppCompatActivity {

    private EditText url;
    private Button download;

    String path_url = "https://www.w3.org/WAI/ER/tests/xhtml/testfiles/resources/pdf/dummy.pdf";
    String keyword = "/";
    private static final int WRITE_PERMISSION = 1001;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_download);

        // Prima varianta cu DOWNLOAD FROM URL
// ------------------------------------------------------------------------
//        url = (EditText)findViewById(R.id.url);
//        download = (Button)findViewById(R.id.download);
//
//        download.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                String getUrl = url.getText().toString();
//
//                DownloadManager.Request request = new DownloadManager.Request(Uri.parse(getUrl));
//
//                String title = URLUtil.guessFileName(getUrl, null, null);
//                request.setTitle(title);
//                request.setDescription("Downloading file please wait...");
//
//                String cookie = CookieManager.getInstance().getCookie(getUrl);
//                request.addRequestHeader("cookie",cookie);
//
//                request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
//                request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, title);
//
//                DownloadManager downloadManager = (DownloadManager)getSystemService(DOWNLOAD_SERVICE);
//                downloadManager.enqueue(request);
//
//                Toast.makeText(DownloadActivity.this, "Downloading Started", Toast.LENGTH_SHORT).show();
//            }
//        });

        // A doua varianta cu DOWNLOAD from a link predefinit
        // ------------------------------------------------------------------------

        download = (Button)findViewById(R.id.download);
        download.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                    if ( ContextCompat.checkSelfPermission(DownloadActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                            == PackageManager.PERMISSION_GRANTED ){

                        String fileName = path_url.substring(path_url.indexOf(keyword) + keyword.length() );
                        downloadFile(fileName, path_url);
                    }
                    else{
                        requestPermissions(new String[] {Manifest.permission.WRITE_EXTERNAL_STORAGE}, WRITE_PERMISSION);
                    }
                }
                else{
                    String fileName = path_url.substring(path_url.indexOf(keyword) + keyword.length() );
                    downloadFile(fileName, path_url);
                }

            }
        });
    }


    private void downloadFile(String fileName, String path_url) {
        Uri downloadURI = Uri.parse(path_url);
        DownloadManager manager = (DownloadManager) getSystemService(DOWNLOAD_SERVICE);

        try{
            if(manager != null){
                DownloadManager.Request request = new DownloadManager.Request(downloadURI);

                request.setAllowedNetworkTypes( DownloadManager.Request.NETWORK_WIFI |
                                                DownloadManager.Request.NETWORK_MOBILE)
                        .setTitle(fileName)
                        .setDescription("Downloading " + fileName)
                        .setAllowedOverMetered(true)
                        .setAllowedOverRoaming(true)
                        .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
                        .setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, fileName)
                        .setMimeType(getMimeType(downloadURI));
                manager.enqueue(request);
                Toast.makeText(this, "Download Started!", Toast.LENGTH_SHORT).show();
            }

            else{
                Intent intent = new Intent(Intent.ACTION_VIEW, downloadURI);
                startActivity(intent);
            }
        }catch (Exception e){
            Toast.makeText(this, "Something went wrong!", Toast.LENGTH_SHORT).show();
            Log.e("Error:DownloadActivity", "E: " + e.getMessage());
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode == WRITE_PERMISSION){
            if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){

                String fileName = path_url.substring((path_url.indexOf(keyword) + keyword.length() ));
                downloadFile(fileName, path_url);
            }
            else {
                Toast.makeText(this, "Permission Denied!", Toast.LENGTH_SHORT).show();
            }
        }
    }


    private String getMimeType(Uri uri){
        ContentResolver resolver = getContentResolver();
        MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();
        return mimeTypeMap.getExtensionFromMimeType(resolver.getType(uri));
    }


}
