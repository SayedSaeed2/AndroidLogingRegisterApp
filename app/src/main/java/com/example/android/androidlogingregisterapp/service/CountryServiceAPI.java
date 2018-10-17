package com.example.android.androidlogingregisterapp.service;

import com.example.android.androidlogingregisterapp.model.Country;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * An Interface for possible API calls
 */

public interface CountryServiceAPI {

    @GET("all")
    Call<List<Country>> getCountries();

}
