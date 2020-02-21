package com.devrim.selfemployed.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.devrim.selfemployed.R;
import com.devrim.selfemployed.model.ActivityType;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ActivityTypeAdapter extends RecyclerView.Adapter<ActivityTypeAdapter.ViewHolder> {
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull final ViewGroup parent, final int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_activity_type, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        holder.activityType.setText(ActivityType.values()[position].name());
    }

    @Override
    public int getItemCount() {
        return ActivityType.values().length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.activity_type)
        TextView activityType;

        public ViewHolder(@NonNull final View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
