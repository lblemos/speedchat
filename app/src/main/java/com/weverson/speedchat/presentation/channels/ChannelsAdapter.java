package com.weverson.speedchat.presentation.channels;

import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.weverson.speedchat.R;
import com.weverson.speedchat.domain.channel.Channel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.weverson.speedchat.utils.ImageGenerator.generateImage;
import static com.weverson.speedchat.utils.ImageGenerator.generateImageCircle;

public class ChannelsAdapter extends RecyclerView.Adapter<ChannelsAdapter.ViewHolder> {

    private List<Channel> mChannels;
    private OnClickChannel mOnClickChannel;

    public ChannelsAdapter(List<Channel> channels, OnClickChannel onClickChannel) {
        mChannels = channels;
        mOnClickChannel = onClickChannel;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View item = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_channel, parent, false);
        return new ViewHolder(item);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Channel channel = mChannels.get(position);
        holder.itemView.setOnClickListener(v -> mOnClickChannel.openChannel(channel));
        holder.mTextTitle.setText(channel.getName());

        if (channel.getLastMessage() != null) {
            holder.mTextDescription.setText(channel.getLastMessage());
        }

        Glide
                .with(holder.itemView.getContext())
                .load(channel.getImage())
                .asBitmap()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .centerCrop()
                .placeholder(generateImage(channel.getName().substring(0, 1)))
                .into(new BitmapImageViewTarget(holder.mImageChannel) {
                    @Override
                    protected void setResource(Bitmap image) {
                        holder.mImageChannel.setImageDrawable(
                                generateImageCircle(holder.itemView.getContext(), image));
                    }
                });

    }

    @Override
    public int getItemCount() {
        return mChannels.size();
    }

    public static final class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.text_title)
        TextView mTextTitle;

        @BindView(R.id.text_last_message)
        TextView mTextDescription;

        @BindView(R.id.image_channel)
        ImageView mImageChannel;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public interface OnClickChannel {

        void openChannel(Channel channel);

    }

}
