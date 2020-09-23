package com.example.myapplication.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.CountryDetail;
import com.example.myapplication.R;
import com.example.myapplication.Utils;
import com.example.myapplication.models.Country;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class CountryAdapter extends RecyclerView.Adapter<CountryAdapter.ViewHolder> {

    ArrayList<Country> countryList;
    private LayoutInflater mInflater;
    private Context context;

    public CountryAdapter(Context context, ArrayList<Country> countryList){
        this.context = context;
        this.mInflater = LayoutInflater.from(context);
        this.countryList= countryList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=mInflater.inflate(R.layout.country_card,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        //Glide.with(context).load(countryList.get(position).getFlag()).into(holder.imageView);
        Utils.fetchSvg(context, countryList.get(position).getFlag(), holder.imageView);
        holder.tv_name.setText("Country Name: "+ countryList.get(position).getName());
        holder.tv_region.setText("Rigion: "+ countryList.get(position).getRegion());
        holder.tv_population.setText("Population: "+ String.valueOf(countryList.get(position).getPopulation()));

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Gson gson = new Gson();
                Intent intent = new Intent(context, CountryDetail.class);
                intent.putExtra("detail", gson.toJson(countryList.get(position)));
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return countryList.size();
    }

    public void filterList(ArrayList<Country> countries) {
        this.countryList = countries;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        CardView cardView;
        ImageView imageView;
        TextView tv_name, tv_region, tv_population;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            cardView = itemView.findViewById(R.id.card_view);
            imageView = itemView.findViewById(R.id.iv_country);
            tv_name = itemView.findViewById(R.id.tv_name);
            tv_region = itemView.findViewById(R.id.tv_region);
            tv_population = itemView.findViewById(R.id.tv_population);
        }
    }
}
