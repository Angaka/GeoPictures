package com.projects.tan.geopictures;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class MediaStoreManager {
    static final String PICTURE = "picture";
    static final String TAG = "MediaStoreManager";

    static List<Folder> getPictureFolders(Context context) {
        Uri uri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
        String projection[] = {
                MediaStore.Images.Media.DATA,
                MediaStore.Images.Media.BUCKET_DISPLAY_NAME,
                MediaStore.Images.Media.BUCKET_ID,
                MediaStore.Images.Media._ID
        };
        Cursor cursor = context.getContentResolver().query(uri, projection, null, null, null);

        List<Folder> folders = new ArrayList<>();
        List<String> ids = new ArrayList<>();
        while (cursor.moveToNext()) {
            Folder folder = new Folder();

            int columnIndex = cursor.getColumnIndex(MediaStore.Images.Media.BUCKET_ID);
            folder.setId(cursor.getString(columnIndex));

            if (!ids.contains(folder.getId())) {
                columnIndex = cursor.getColumnIndex(MediaStore.Images.Media.DATA);

                folder.setParentPath(new File(cursor.getString(columnIndex)).getParent());
                folder.setPath(cursor.getString(columnIndex));

                columnIndex = cursor.getColumnIndex(MediaStore.Images.Media.BUCKET_DISPLAY_NAME);
                folder.setName(cursor.getString(columnIndex));

                columnIndex = cursor.getColumnIndex(MediaStore.Images.Media._ID);
                folder.setCoverId(cursor.getLong(columnIndex));

                folders.add(folder);
                ids.add(folder.getId());
            }
        }
        cursor.close();

        for (Folder f : folders) {
            f.setPictures(getPicturesByFolderId(context, f.getId()));
            f.setCount(f.getPictures().size());
        }

        return folders;
    }

    private static List<Picture> getPicturesByFolderId(Context context, String folderId) {
        Uri uri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
        String projection[] = {
                MediaStore.Images.Media.DATA,
                MediaStore.Images.Media.BUCKET_DISPLAY_NAME,
                MediaStore.Images.Media.BUCKET_ID,
                MediaStore.Images.Media._ID,
                MediaStore.Images.Media.DATE_TAKEN
        };
        Cursor cursor = context.getContentResolver().query(uri, projection, null, null, null);

        List<Picture> pictures = new ArrayList<>();
        while (cursor.moveToNext()) {

            Picture picture = new Picture();

            int columnIndex = cursor.getColumnIndex(MediaStore.Images.Media.BUCKET_ID);

            picture.setId(cursor.getString(columnIndex));
            if (folderId.equals(cursor.getString(columnIndex))) {
                columnIndex = cursor.getColumnIndex(MediaStore.Images.Media.DATA);

                picture.setPath(cursor.getString(columnIndex));

                columnIndex = cursor.getColumnIndex(MediaStore.Images.Media.BUCKET_DISPLAY_NAME);
                picture.setName(cursor.getString(columnIndex));

                columnIndex = cursor.getColumnIndex(MediaStore.Images.Media.DATE_TAKEN);
                picture.setDate(cursor.getString(columnIndex));
                pictures.add(picture);
            }
        }
        cursor.close();

        return pictures;
    }
}
