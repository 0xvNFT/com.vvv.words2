package com.vvv.words2.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.vvv.words2.Models.LevelModel;
import com.vvv.words2.R;

import java.util.List;

public class LevelAdapter extends RecyclerView.Adapter<LevelAdapter.MyViewHolder> {
    Context context;
    List<LevelModel> levelModelArrayList;

    public LevelAdapter(Context context, List<LevelModel> levelModelArrayList) {
        this.context = context;
        this.levelModelArrayList = levelModelArrayList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.level_view, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.level_name.setText(levelModelArrayList.get(position).getName());
        holder.challenge.setText(levelModelArrayList.get(position).getChallenge());

    }

    @Override
    public int getItemCount() {
        return levelModelArrayList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView level_name, challenge;
        CardView cardView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            cardView = itemView.findViewById(R.id.cardView);
            level_name = itemView.findViewById(R.id.Level_Name);
            challenge = itemView.findViewById(R.id.challengeVal);
        }
    }
}
