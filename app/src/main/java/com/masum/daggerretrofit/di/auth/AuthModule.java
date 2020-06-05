package com.masum.daggerretrofit.di.auth;

import com.masum.daggerretrofit.network.AuthApiInterface;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

@Module
public class AuthModule {

    @Provides
    static AuthApiInterface provideAuthApiInterface(Retrofit retrofit){
        return retrofit.create(AuthApiInterface.class);
    }
}
