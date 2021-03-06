package com.weverson.speedchat.data.repository;


import com.weverson.speedchat.domain.abstraction.Authenticable;
import com.weverson.speedchat.domain.channel.Channel;

import java.util.List;

import rx.Observable;

public interface ChannelRepository {

    Observable<Channel> fetchChannelBy(String channelId);

    Observable<List<String>> fetchChannelsBy(Authenticable authenticable);

    Observable<Channel> createChannel(Channel channel);

}
