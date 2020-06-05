package com.masum.daggerretrofit.network;

import com.masum.daggerretrofit.models.User;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface AuthApiInterface {

    @GET("users/{id}")
    Call<User> getUser(
            @Path("id") int id
    );

}
