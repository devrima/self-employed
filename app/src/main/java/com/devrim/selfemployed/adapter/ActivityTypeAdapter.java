package com.devrim.selfemployed.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.devrim.selfemployed.R;
import com.devrim.selfemployed.model.ActivityType;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ActivityTypeAdapter extends RecyclerView.Adapter<ActivityTypeAdapter.ViewHolder> {
    private int selectedPosition = -1;

    @Override
    public long getItemId(final int position) {
        return position;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull final ViewGroup parent, final int viewType) {
        final ViewHolder viewHolder = new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_activity_type, parent, false));
        viewHolder.activityType.setOnClickListener(v -> {
            final int previousPosition = selectedPosition;
            selectedPosition = viewHolder.getAdapterPosition();
            notifyItemChanged(viewHolder.getAdapterPosition());
            if (previousPosition != -1) {
                notifyItemChanged(previousPosition);
            }
        });
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        holder.activityType.setText(ActivityType.values()[position].name());
        holder.activityType.setActivated(selectedPosition == position);
    }

    @Override
    public int getItemCount() {
        return ActivityType.values().length;
    }

    @Nullable
    public ActivityType getSelectedActivityType() {
        if (selectedPosition != -1) {
            return ActivityType.values()[selectedPosition];
        }
        return null;
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
