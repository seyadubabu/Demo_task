package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.myapplication.Adapters.CountryAdapter;
import com.example.myapplication.Api.ApiClient;
import com.example.myapplication.Api.ApiInterface;
import com.example.myapplication.models.Country;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.et_search)
    EditText et_search;
    @BindView(R.id.recycler)
    RecyclerView recyclerView;
    @BindView(R.id.progress)
    ProgressBar progressBar;
    @BindView(R.id.tv_error)
    TextView tv_error;

    ApiInterface apiInterface;
    CountryAdapter adapter;
    ArrayList<Country> countryArrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        apiInterface = ApiClient.getApiClient().create(ApiInterface.class);

        et_search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                filter(s.toString());
            }
        });

        callApi();
    }

    private void filter(String s) {
        ArrayList<Country> filterCountries = new ArrayList<>();
        for (Country country : countryArrayList) {
            //if the existing elements contains the search input
            if (country.getName().toLowerCase().contains(s.toLowerCase())) {
                //adding the element to filtered list
                filterCountries.add(country);
            }
        }

        adapter.filterList(filterCountries);
    }

    private void callApi() {
        apiInterface.getAllCountries().enqueue(new Callback<ArrayList<Country>>() {
            @Override
            public void onResponse(Call<ArrayList<Country>> call, Response<ArrayList<Country>> response) {
                countryArrayList = response.body();
                progressBar.setVisibility(View.GONE);
                et_search.setVisibility(View.VISIBLE);
                recyclerView.setVisibility(View.VISIBLE);
                setupList();
            }

            @Override
            public void onFailure(Call<ArrayList<Country>> call, Throwable t) {
                progressBar.setVisibility(View.GONE);
                tv_error.setText(t.getMessage());
                tv_error.setVisibility(View.VISIBLE);
            }
        });
    }

    private void setupList() {
        Log.e("list-sice --->", String.valueOf(countryArrayList.size()));
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new CountryAdapter(this, countryArrayList);
        recyclerView.setAdapter(adapter);
    }
}