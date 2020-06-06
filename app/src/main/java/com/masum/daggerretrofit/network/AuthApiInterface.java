package com.masum.daggerretrofit.network;

import com.masum.daggerretrofit.models.User;

import io.reactivex.Flowable;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface AuthApiInterface {

    @GET("users/{id}")
    Flowable<User> getUser(
            @Path("id") int id
    );

}
