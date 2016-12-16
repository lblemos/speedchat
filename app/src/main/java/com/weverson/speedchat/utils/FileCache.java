package com.weverson.speedchat.utils;

import android.graphics.Bitmap;
import android.net.Uri;
import android.support.annotation.NonNull;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.Timestamp;

import static com.google.common.base.Preconditions.checkNotNull;

public class FileCache {

    public static Uri saveImageInTemp(@NonNull Bitmap image) {
        checkNotNull(image, "the image can not be null");
        OutputStream out = null;
        File file = null;
        String name = new Timestamp(System.currentTimeMillis()).toString();

        try {
            file = File.createTempFile(name, ".png", null);
            out = new FileOutputStream(file);
            image.compress(Bitmap.CompressFormat.JPEG, 100, out);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (out != null) {
                    out.flush();
                    out.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        if(file != null){
            return Uri.parse(file.getAbsolutePath());
        }
        return null;
    }

}
