package com.gardener.dagger_mvvm_rxjava.network.auth;

import com.gardener.dagger_mvvm_rxjava.models.User;

import io.reactivex.Flowable;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface AuthApi {

    @GET("users/{id}")
    Flowable<User> getUser(
            @Path("id") int id
    );
}

