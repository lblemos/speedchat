package com.example.weverson.speedchat.presentation.channels;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.weverson.speedchat.R;
import com.example.weverson.speedchat.domain.channel.Channel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ChannelsAdapter extends RecyclerView.Adapter<ChannelsAdapter.ViewHolder> {

    private List<Channel> mChannels;

    public ChannelsAdapter(List<Channel> channels) {
        mChannels = channels;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View item = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_channel, parent, false);
        return new ViewHolder(item);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Channel channel = mChannels.get(position);
        holder.mTextTitle.setText(channel.getTitle());
        holder.mTextDescription.setText(channel.getDescription());
    }

    @Override
    public int getItemCount() {
        return mChannels.size();
    }

    public static final class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.text_title)
        TextView mTextTitle;

        @BindView(R.id.text_description)
        TextView mTextDescription;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}
