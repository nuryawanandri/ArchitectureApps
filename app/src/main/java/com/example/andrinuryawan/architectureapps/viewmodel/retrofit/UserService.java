package com.example.andrinuryawan.architectureapps.viewmodel.retrofit;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface UserService {

    @GET("/users")
    Call<List<User>> getAllUser();

}
