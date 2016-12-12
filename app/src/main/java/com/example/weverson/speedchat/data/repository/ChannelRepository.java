package com.example.weverson.speedchat.data.repository;


import com.example.weverson.speedchat.domain.channel.Channel;

import rx.Observable;

public interface ChannelRepository {

    Observable<Channel> getChannel(String channel);

}
