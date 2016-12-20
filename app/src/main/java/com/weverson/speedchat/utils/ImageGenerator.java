package com.weverson.speedchat.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;

import com.amulyakhare.textdrawable.TextDrawable;
import com.amulyakhare.textdrawable.util.ColorGenerator;

public class ImageGenerator {

    public static TextDrawable generateImage(String name){
        ColorGenerator generator = ColorGenerator.MATERIAL;

        int color = generator.getRandomColor();

        return TextDrawable.builder()
                .buildRoundRect(name, color, 100);
    }

    public static RoundedBitmapDrawable generateImageCircle(Context context, Bitmap image){
        RoundedBitmapDrawable circularBitmapDrawable = RoundedBitmapDrawableFactory
                .create(context.getResources(), image);
        circularBitmapDrawable.setCircular(true);
        return circularBitmapDrawable;
    }

}
