package com.masum.daggerretrofit.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.LiveDataReactiveStreams;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import com.masum.daggerretrofit.models.User;
import com.masum.daggerretrofit.network.AuthApiInterface;
import javax.inject.Inject;
import io.reactivex.schedulers.Schedulers;

public class AuthViewModel extends ViewModel {
    private final AuthApiInterface apiInterface;

    private MediatorLiveData<User> authUser=new MediatorLiveData<>();
    @Inject
    public AuthViewModel(AuthApiInterface apiInterface) {
        this.apiInterface = apiInterface;

/*        if (apiInterface == null) {
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

        }*/
    }


    public void authenticateWithId(int id){
        final LiveData<User> source= LiveDataReactiveStreams.fromPublisher(
                apiInterface.getUser(id)
                        .subscribeOn(Schedulers.io())
        );

        authUser.addSource(source, new Observer<User>() {
            @Override
            public void onChanged(User user) {
                authUser.setValue(user);
                authUser.removeSource(source);
            }
        });
    }

    public LiveData<User> observerUser(){
        return authUser;
    }
}
