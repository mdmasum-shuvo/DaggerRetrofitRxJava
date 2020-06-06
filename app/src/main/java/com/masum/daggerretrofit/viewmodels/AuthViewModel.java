package com.masum.daggerretrofit.viewmodels;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.LiveDataReactiveStreams;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import com.masum.daggerretrofit.models.User;
import com.masum.daggerretrofit.network.AuthApiInterface;
import com.masum.daggerretrofit.ui.AuthResource;

import javax.inject.Inject;

import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class AuthViewModel extends ViewModel {
    private final AuthApiInterface apiInterface;

    private MediatorLiveData<AuthResource<User>> authUser=new MediatorLiveData<>();
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

        authUser.setValue(AuthResource.loading(null));
        final LiveData<AuthResource<User>>source= LiveDataReactiveStreams.fromPublisher(
                apiInterface.getUser(id)
                        .onErrorReturn(new Function<Throwable, User>() {
                            @Override
                            public User apply(Throwable throwable) throws Exception {
                                User user=new User();
                                user.setId(-1);
                                return user;
                            }
                        })
                        .map(new Function<User, AuthResource<User>>() {
                            @Override
                            public AuthResource<User> apply(User user) throws Exception {
                                if (user.getId()==-1){
                                    return AuthResource.error("could not authenticate",null);
                                }
                                return AuthResource.authenticated(user);
                            }
                        })
                        .subscribeOn(Schedulers.io())
        );

        authUser.addSource(source, new Observer<AuthResource<User>>() {
            @Override
            public void onChanged(AuthResource<User> userAuthResource) {
                authUser.setValue(userAuthResource);
                authUser.removeSource(source);
            }
        });
    }

    public LiveData<AuthResource<User>> observerUser(){
        return authUser;
    }
}
