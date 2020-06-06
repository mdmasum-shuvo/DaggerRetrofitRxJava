package com.masum.daggerretrofit.viewmodels;

import android.util.Log;

import androidx.lifecycle.ViewModel;

import com.masum.daggerretrofit.models.User;
import com.masum.daggerretrofit.network.AuthApiInterface;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class AuthViewModel extends ViewModel {
    private final AuthApiInterface apiInterface;

    @Inject
    public AuthViewModel(AuthApiInterface apiInterface) {
        this.apiInterface = apiInterface;

        if (apiInterface == null) {
            Log.e("data", "auth api is null");
        } else {
            Log.e("data", "auth api is not null");

            apiInterface.getUser(3)
                    .toObservable()
                    .subscribeOn(Schedulers.io())
                    .subscribe(new Observer<User>() {
                        @Override
                        public void onSubscribe(Disposable d) {

                        }

                        @Override
                        public void onNext(User user) {
                            Log.e("data", user.getEmail());
                        }

                        @Override
                        public void onError(Throwable e) {

                        }

                        @Override
                        public void onComplete() {

                        }
                    });

        }
    }
}
