package com.example.myapplication.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.models.Country;
import com.example.myapplication.models.Language;

import java.util.ArrayList;
import java.util.List;

public class LanguageAdapter extends RecyclerView.Adapter<LanguageAdapter.ViewHolder> {

    List<Language> languages;
    private LayoutInflater mInflater;
    private Context context;

    public LanguageAdapter(Context context, List<Language> languages){
        this.context = context;
        this.mInflater = LayoutInflater.from(context);
        this.languages= languages;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=mInflater.inflate(R.layout.language_card,parent,false);
        return new LanguageAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.tv_lname.setText("Name: " + languages.get(position).getName());
        holder.tv_ntname.setText("Native Name: " + languages.get(position).getNativeName());
        holder.tv_iso639_1.setText("ISO639_1: " + languages.get(position).getIso6391());
        holder.tv_iso639_2.setText("ISO639_2: " + languages.get(position).getIso6392());

    }

    @Override
    public int getItemCount() {
        return languages.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv_lname, tv_ntname, tv_iso639_1, tv_iso639_2;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tv_lname = itemView.findViewById(R.id.tv_lname);
            tv_ntname = itemView.findViewById(R.id.tv_ntname);
            tv_iso639_1 = itemView.findViewById(R.id.tv_iso639_1);
            tv_iso639_2 = itemView.findViewById(R.id.tv_iso639_2);
        }
    }
}
