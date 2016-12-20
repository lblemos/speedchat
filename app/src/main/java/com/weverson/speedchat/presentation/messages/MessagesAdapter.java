package com.weverson.speedchat.presentation.messages;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.amulyakhare.textdrawable.TextDrawable;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.weverson.speedchat.R;
import com.weverson.speedchat.domain.message.Message;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.weverson.speedchat.utils.ImageGenerator.generateImage;
import static com.weverson.speedchat.utils.ImageGenerator.generateImageCircle;

public class MessagesAdapter extends RecyclerView.Adapter<MessagesAdapter.ViewHolder> {

    private List<Message> mMessages;

    public MessagesAdapter(List<Message> messages) {
        mMessages = messages;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View item = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_message, parent, false);
        return new ViewHolder(item);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Message message = mMessages.get(position);
        Context context = holder.itemView.getContext();
        TextDrawable placeholderImage = generateImage(message.getName().substring(0, 1));

        holder.mTextMessage.setText(message.getMessage());
        holder.mTextCratedAt.setText(message.getCreatedAt());

        Glide.with(context)
                .load("")
                .asBitmap()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .centerCrop()
                .placeholder(placeholderImage)
                .into(new BitmapImageViewTarget(holder.mImageProfile) {
                    @Override
                    protected void setResource(Bitmap resource) {
                        holder.mImageProfile.setImageDrawable(
                                generateImageCircle(context, resource));
                    }
                });

    }

    @Override
    public int getItemCount() {
        return mMessages.size();
    }

    public static final class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.text_message)
        TextView mTextMessage;

        @BindView(R.id.text_created_at)
        TextView mTextCratedAt;

        @BindView(R.id.image_profile)
        ImageView mImageProfile;

        @BindView(R.id.card_messages)
        CardView mCardMessage;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}
