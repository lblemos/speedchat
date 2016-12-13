package com.example.weverson.speedchat.presentation.channels;


import com.example.weverson.speedchat.domain.channel.Channel;
import com.example.weverson.speedchat.domain.channel.interactor.ListChannelsUseCase;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

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

        Channel channel = new Channel("Title", "Description");

//        when(mListChannelsUseCase.execute(any(ListChannelsUseCase.Request.class)))
//                .thenReturn(Observable.just());
//
//        mChannelsPresenter.listChannels();
//
//        verify(mChannelsView).createChannel();
    }


}
