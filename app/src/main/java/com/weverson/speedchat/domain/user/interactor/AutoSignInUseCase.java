package com.weverson.speedchat.domain.user.interactor;

import com.weverson.speedchat.data.repository.UserRepository;
import com.weverson.speedchat.domain.abstraction.UseCase;
import com.weverson.speedchat.domain.user.User;
import com.weverson.speedchat.utils.dagger.qualifier.Firebase;

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
