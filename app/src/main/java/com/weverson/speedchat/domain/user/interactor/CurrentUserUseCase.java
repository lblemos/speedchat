package com.weverson.speedchat.domain.user.interactor;


import com.weverson.speedchat.data.repository.UserRepository;
import com.weverson.speedchat.domain.abstraction.Authenticable;
import com.weverson.speedchat.domain.abstraction.UseCase;
import com.weverson.speedchat.domain.user.User;
import com.weverson.speedchat.utils.dagger.qualifier.Firebase;

import javax.inject.Inject;

import rx.Observable;

public class CurrentUserUseCase extends UseCase<Authenticable, CurrentUserUseCase.Request > {


    private UserRepository mUserRepository;

    @Inject
    public CurrentUserUseCase(@Firebase UserRepository repository) {
        mUserRepository = repository;
    }

    @Override
    public Observable<Authenticable> createObservable(Request request) {
        return mUserRepository.fetchCurrentUser(new User());
    }

    public static final class Request extends UseCase.Request{

    }
}
