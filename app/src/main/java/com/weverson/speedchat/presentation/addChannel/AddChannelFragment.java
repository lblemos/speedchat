package com.weverson.speedchat.presentation.addChannel;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.weverson.speedchat.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AddChannelFragment extends Fragment {

    @BindView(R.id.image_add_photo)
    ImageView mImageAddPhoto;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_channel, container, false);
        ButterKnife.bind(this, view);
        initializeListeners();
        return view;
    }

    private void initializeListeners() {
        mImageAddPhoto.setOnTouchListener(new changeImageOnTouch());

    }

    public static AddChannelFragment getInstance() {
        return new AddChannelFragment();
    }

    private final class changeImageOnTouch implements View.OnTouchListener {

        @Override
        public boolean onTouch(View v, MotionEvent event) {
            v.setSelected(event.getAction() == MotionEvent.ACTION_DOWN);
            return true;
        }
    }
}
