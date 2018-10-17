package com.example.android.androidlogingregisterapp.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.android.androidlogingregisterapp.R;
import com.example.android.androidlogingregisterapp.model.Country;
import com.example.android.androidlogingregisterapp.service.CountryServiceAPI;
import com.google.firebase.auth.FirebaseAuth;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * HomeActivity represents the home page of the application which contains
 * list of countries that come using Retrofit2.0(Type-safe REST client API for Android) library
 * and logout button that send you back to Login page again.
 */
public class HomeActivity extends AppCompatActivity {

    FirebaseAuth auth;

    private Spinner countriesList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        auth = FirebaseAuth.getInstance();

        countriesList = findViewById(R.id.countries_list);
        loadCountries();
    }

    /**
     * load list of countries using Retrofit API
     */
    private void loadCountries() {
        String LINK = "https://restcountries.eu/rest/v2/";
        Retrofit retrofit = new Retrofit.Builder().baseUrl(LINK)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        CountryServiceAPI serviceAPI = retrofit.create(CountryServiceAPI.class);

        Call<List<Country>> call = serviceAPI.getCountries();
        call.enqueue(new Callback<List<Country>>() {
            @Override
            public void onResponse(Call<List<Country>> call, retrofit2.Response<List<Country>> response) {
                List<Country> countries = response.body();

                ArrayAdapter adapter = new ArrayAdapter(getApplicationContext(),
                        android.R.layout.simple_spinner_item,
                        countries);
                countriesList.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<List<Country>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Oops, Failed getting Countries List !", Toast.LENGTH_LONG).show();
            }
        });
    }

    /**
     * Logout and go to Login Page
     *
     * @param view
     */
    public void logout(View view) {
        auth.signOut();
        startActivity(new Intent(HomeActivity.this, LoginActivity.class));
        finish();
    }
}
