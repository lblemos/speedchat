package com.example.weverson.speedchat.presentation.channels;


import com.example.weverson.speedchat.domain.channel.Channel;
import com.example.weverson.speedchat.domain.channel.interactor.ListChannelsUseCase;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class ChannelsPresenterTest {

    @Mock
    private ChannelsContract.View mChannelsView;

    @Mock
    private ListChannelsUseCase mListChannelsUseCase;

    private ChannelsPresenter mChannelsPresenter;

    @Before
    public void setUp() {
        initMocks(this);

        mChannelsPresenter = new ChannelsPresenter(mChannelsView, mListChannelsUseCase);
        mChannelsPresenter.setupListeners();
    }

    @Test
    public void getChannels_listChannelsInUi() {

        List<Channel> lists = new ArrayList<>();
        lists.add(new Channel("Title1", "Description1"));
        lists.add(new Channel("Title2", "Description2"));

        when(mListChannelsUseCase.execute(any(ListChannelsUseCase.Request.class)))
                .thenReturn(Observable.just(lists));

        mChannelsPresenter.listChannels();

        verify(mChannelsView).displayChannels(lists);
    }


}
