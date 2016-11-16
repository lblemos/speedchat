package com.example.weverson.speedchat;

public interface BaseView<T extends BasePresenter> {

    void setPresenter(T presenter);

}
