package com.devrim.selfemployed.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.devrim.selfemployed.R;
import com.devrim.selfemployed.database.Activity;

import java.util.ArrayList;
import java.util.List;

public class ActivityAdapter extends RecyclerView.Adapter<ActivityAdapter.ViewHolder> {
    private List<Activity> activities = new ArrayList<>();

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull final ViewGroup parent, final int viewType) {
       return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_activity, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
//TODO:
    }

    @Override
    public int getItemCount() {
        return activities.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(@NonNull final View itemView) {
            super(itemView);
        }
    }
}
