package com.example.myapplication.Api;

import com.example.myapplication.models.Country;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {

    @GET("all")
    Call<ArrayList<Country>> getAllCountries();
}
