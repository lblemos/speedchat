package com.example.weverson.speedchat.data.repository;


import com.example.weverson.speedchat.domain.abstraction.Authenticable;
import com.example.weverson.speedchat.domain.channel.Channel;

import java.util.List;

import rx.Observable;

public interface ChannelRepository {

    Observable<Channel> fetchChannelBy(String channel);

    Observable<List<String>> fetchChannelsBy(Authenticable authenticable);

}
