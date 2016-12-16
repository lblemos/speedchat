package com.weverson.speedchat.presentation.addChannel;


import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.weverson.speedchat.R;
import com.weverson.speedchat.presentation.channels.ChannelsActivity;
import com.weverson.speedchat.utils.FileCache;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static android.app.Activity.RESULT_OK;
import static android.widget.ImageView.ScaleType.CENTER_CROP;
import static com.google.common.base.Preconditions.checkNotNull;

public class AddChannelFragment extends Fragment implements AddChannelContract.View {

    static final int REQUEST_IMAGE_OPEN = 1;

    @BindView(R.id.image_add_photo)
    ImageButton mImageAddPhoto;

    @BindView(R.id.edit_name)
    EditText mTextName;

    @BindView(R.id.frame_add_image)
    FrameLayout frameAddImage;

    @BindView(R.id.relative_add_channel)
    RelativeLayout relativeAddChannel;

    @BindView(R.id.floating_add_channel)
    FloatingActionButton mFloatingAddChannel;

    private AddChannelContract.Presenter mAddChannelPresenter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_channel, container, false);
        ButterKnife.bind(this, view);
        initializeListeners();
        return view;
    }

    private void initializeListeners() {
        mImageAddPhoto.setOnTouchListener(new selectImageOnTouchListener());
    }

    public static AddChannelFragment getInstance() {
        return new AddChannelFragment();
    }

    @Override
    public void showSelectImage() {

        Intent galleryIntent = new Intent(Intent.ACTION_PICK);
        galleryIntent.setType("image/*");

        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        Intent chooser = Intent.createChooser(galleryIntent, getString(R.string.msg_select_image));
        Intent[] extraIntents = {cameraIntent};
        chooser.putExtra(Intent.EXTRA_INITIAL_INTENTS, extraIntents);

        startActivityForResult(chooser, REQUEST_IMAGE_OPEN);

    }

    @Override
    public void openChannels() {
        Intent it = new Intent(getContext(), ChannelsActivity.class);
        startActivity(it);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_OPEN && resultCode == RESULT_OK) {

            if (data.getData() == null) {
                Bundle extras = data.getExtras();
                Bitmap imageBitmap = (Bitmap) extras.get("data");
                mImageAddPhoto.setImageBitmap(imageBitmap);
                mImageAddPhoto.setScaleType(CENTER_CROP);
            } else {
                Glide.with(getContext())
                        .load(data.getData())
                        .asBitmap()
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .centerCrop()
                        .into(mImageAddPhoto);
            }

        }
    }

    @OnClick(R.id.image_add_photo)
    public void addPhoto() {
        mAddChannelPresenter.selectImage();
    }

    @OnClick(R.id.floating_add_channel)
    public void addChannel() {
        mAddChannelPresenter.createNewChannel(getName(), getImage());
    }


    private final class selectImageOnTouchListener implements View.OnTouchListener {

        @Override
        public boolean onTouch(View v, MotionEvent event) {
            v.setSelected(event.getAction() == MotionEvent.ACTION_DOWN);

            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                mAddChannelPresenter.selectImage();
            }

            return true;
        }
    }

    @Override
    public void setPresenter(@NonNull AddChannelContract.Presenter presenter) {
        mAddChannelPresenter = checkNotNull(presenter, "the presenter can not be null");
    }

    private String getName() {
        return mTextName.getText().toString();
    }

    private String getImage() {
        Bitmap bitmap = ((BitmapDrawable) mImageAddPhoto.getDrawable()).getBitmap();
        return FileCache.saveImageInTemp(bitmap).toString();
    }
}
