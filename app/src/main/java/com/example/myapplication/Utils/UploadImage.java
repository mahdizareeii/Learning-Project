package com.example.myapplication.Utils;

import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.io.File;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;


public class UploadImage {

    private Context context;

    public UploadImage(Context context) {
        this.context = context;
    }

    public void uploadImage(Intent data) {
        Toast.makeText(context, "choose", Toast.LENGTH_SHORT).show();
        File file = null;
        try {
            if (data != null) {
                //Log.i(TAG, Objects.requireNonNull(Tools.getFilePath(getActivity(), data.getData())));
                file = new File(Objects.requireNonNull(getFilePath(context, data.getData())));
            }
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        RequestBody requestBody = null;
        if (file != null) {
            requestBody = RequestBody.create(MediaType.parse("multipart/form-data"), file);
            MultipartBody.Part multipartBody = MultipartBody.Part.createFormData("file", file.getName(), requestBody);
            /*Observable<ItemResponse> call = RetrofitClient.getInstance().getApi().uploadFile(getAccessToken(), multipartBody, "banner");
            RetrofitClient.callRX(call, new RetrofitClient.RxAPICallback<ItemResponse>() {
                @Override
                public void onSuccess(ItemResponse t) {
                    if (t.getSuccess())
                        Toast.makeText(context, "uploaded", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onErrorResult(Map<String, List<String>> errors) {

                }

                @Override
                public void onFailed(Throwable throwable) {

                }
            });*/
        }
    }

    private String getFilePath(Context context, Uri uri) throws URISyntaxException {
        String selection = null;
        String[] selectionArgs = null;
        // Uri is different in versions after KITKAT (Android 4.4), we need to
        if (Build.VERSION.SDK_INT >= 19 && DocumentsContract.isDocumentUri(context, uri)) {
            if (isExternalStorageDocument(uri)) {
                final String docId = DocumentsContract.getDocumentId(uri);
                final String[] split = docId.split(":");
                return Environment.getExternalStorageDirectory() + "/" + split[1];
            } else if (isDownloadsDocument(uri)) {
                final String id = DocumentsContract.getDocumentId(uri);
                uri = ContentUris.withAppendedId(
                        Uri.parse("content://downloads/public_downloads"), Long.valueOf(id));
            } else if (isMediaDocument(uri)) {
                final String docId = DocumentsContract.getDocumentId(uri);
                final String[] split = docId.split(":");
                final String type = split[0];
                if ("image".equals(type)) {
                    uri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
                } else if ("video".equals(type)) {
                    uri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
                } else if ("audio".equals(type)) {
                    uri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
                }
                selection = "_id=?";
                selectionArgs = new String[]{
                        split[1]
                };
            }
        }
        if ("content".equalsIgnoreCase(uri.getScheme())) {
            String[] projection = {
                    MediaStore.Images.Media.DATA
            };
            Cursor cursor = null;
            try {
                cursor = context.getContentResolver()
                        .query(uri, projection, selection, selectionArgs, null);
                int column_index = 0;
                if (cursor != null) {
                    column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
                    if (cursor.moveToFirst()) {
                        return cursor.getString(column_index);
                    }
                }
            } catch (Exception e) {
                //handelError(context, e.getMessage());
            }
            if (cursor != null) {
                cursor.close();
            }
        } else if ("file".equalsIgnoreCase(uri.getScheme())) {
            return uri.getPath();
        }
        return null;
    }

    private boolean isExternalStorageDocument(Uri uri) {
        return "com.android.externalstorage.documents".equals(uri.getAuthority());
    }

    private boolean isDownloadsDocument(Uri uri) {
        return "com.android.providers.downloads.documents".equals(uri.getAuthority());
    }

    private boolean isMediaDocument(Uri uri) {
        return "com.android.providers.media.documents".equals(uri.getAuthority());
    }

//    private void uploadFileToServer() {
//        Intent intent = new Intent();
//        intent.setType("image/*");
//        intent.setAction(Intent.ACTION_GET_CONTENT);
//        startActivityForResult(Intent.createChooser(intent, "Select Pic"), 1);
//    }
//
//    @Override
//    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if (requestCode == 1) {
//            UploadImage uploadImage = new UploadImage(getActivity());
//            uploadImage.uploadImage(data);
//        }
//    }

   /* @Multipart
    @POST("upload")
    Observable<ItemResponse> uploadFile(@Header("Authorization") String authorization, @Part MultipartBody.Part file, @Query("type") String type);
*/


}

