package com.example.weverson.speedchat.utils;

import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPropertyAnimatorCompat;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;

public class Animations {

    public static void animateForm(ViewGroup container){
        final int itemDelay = 300;

        for (int i = 0; i < container.getChildCount(); i++) {
            View v = container.getChildAt(i);
            ViewPropertyAnimatorCompat viewAnimator = ViewCompat.animate(v)
                    .scaleY(1).scaleX(1)
                    .setStartDelay((100 * i) + 500)
                    .setDuration(itemDelay);

            viewAnimator.setInterpolator(new DecelerateInterpolator()).start();
        }
    }

}
