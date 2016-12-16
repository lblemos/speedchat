package com.weverson.speedchat.presentation.addChannel;


import android.graphics.Bitmap;

import com.weverson.speedchat.domain.channel.interactor.CreateChannelUseCase;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import rx.Observable;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class AddChannelPresenterTest {

    @Mock
    private AddChannelContract.View mAddChannelView;

    @Mock
    private CreateChannelUseCase mCreateChannelUseCase;

    private AddChannelPresenter mAddChannelPresenter;

    @Before
    public void setUp() {
        initMocks(this);
        mAddChannelPresenter = new AddChannelPresenter(mAddChannelView);
        mAddChannelPresenter.setupListeners();
    }

    @Test
    public void selectImage_showSelectImage(){
        mAddChannelPresenter.selectImage();
        verify(mAddChannelView).showSelectImage();
    }

    @Test
    public void createNewChanel_showChannelsUi(){

        when(mCreateChannelUseCase.execute(any(CreateChannelUseCase.Request.class)))
                .thenReturn(Observable.empty());

        mAddChannelPresenter.createNewChannel(anyString(), any(Bitmap.class));

        verify(mAddChannelView).openChannels();

    }

}
