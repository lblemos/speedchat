package com.weverson.speedchat.data.repository;

import com.weverson.speedchat.domain.channel.Channel;
import com.weverson.speedchat.domain.message.Message;

import java.util.List;

import rx.Observable;

public interface MessageRepository {

    Observable<List<Message>> fetchMessageBy(Channel channel);

}
