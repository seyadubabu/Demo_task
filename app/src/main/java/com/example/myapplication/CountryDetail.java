package com.example.myapplication;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.Adapters.LanguageAdapter;
import com.example.myapplication.models.Country;
import com.google.gson.Gson;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CountryDetail extends AppCompatActivity {

    @BindView(R.id.iv_flag)
    ImageView iv_flag;
    @BindView(R.id.tv_name)
    TextView tv_name;
    @BindView(R.id.tv_region)
    TextView tv_region;
    @BindView(R.id.tv_population)
    TextView tv_population;
    @BindView(R.id.tv_capital)
    TextView tv_capital;
    @BindView(R.id.tv_cioc)
    TextView tv_cioc;
    @BindView(R.id.rl_language)
    RecyclerView recyclerView;

    Country country;
    LanguageAdapter languageAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);

        Gson gson = new Gson();
        String detail = getIntent().getStringExtra("detail");
        country = gson.fromJson(detail, Country.class);

        Utils.fetchSvg(getApplicationContext(), country.getFlag(), iv_flag);
        tv_name.setText(country.getName());
        tv_region.setText(country.getRegion());
        tv_population.setText(String.valueOf(country.getPopulation()));
        tv_capital.setText(country.getCapital());
        tv_cioc.setText(country.getCioc());

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        languageAdapter = new LanguageAdapter(this, country.getLanguages());
        recyclerView.setAdapter(languageAdapter);
    }
}
