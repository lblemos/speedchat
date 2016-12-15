package com.example.weverson.speedchat.domain.user.interactor;

import com.example.weverson.speedchat.data.repository.UserRepository;
import com.example.weverson.speedchat.domain.abstraction.UseCase;
import com.example.weverson.speedchat.domain.user.User;
import com.example.weverson.speedchat.utils.dagger.qualifier.Firebase;

import javax.inject.Inject;

import rx.Observable;


public class AutoSignInUseCase extends UseCase<Boolean, SignInUseCase.Request> {

    private UserRepository mRepository;

    @Inject
    public AutoSignInUseCase(@Firebase UserRepository repository) {
        mRepository = repository;
    }

    @Override
    public Observable<Boolean> createObservable(SignInUseCase.Request request) {

        return mRepository.fetchCurrentUser(new User())
                .map(authenticable -> authenticable.getUid() != null);

    }

    public static final class Request extends UseCase.Request {
    }


}
